package com.Borman;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class UserDao {
    public static final String RED = "\033[0;31m";
    public static final String RESET = "\033[0m";
    public static final String PURPLE = "\033[0;35m";

    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(username, email, password)\n" +
                    "VALUES (?, ?, ?)";

    private static final String USER_SEARCH_BY_ID_QUERY =
            "SELECT *\n" +
                    "FROM users\n" +
                    "WHERE id = ?";
    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET username = ?,\n" +
                    "email = ?,\n" +
                    "password = ? " +
                    "WHERE id = ?";
    private static final String DELETE_USER_BY_ID_QUERY =
            "DELETE \n" +
                    "FROM users\n" +
                    "WHERE id = ?";
    private static final String SELECT_ALL_USER_QUERY =
            "SELECT *\n" +
                    "FROM users";

    public static void createUserInDb(Connection conn, User user) {
        try (PreparedStatement statement = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, DbUtil.hashPassword(user.getPassword()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static User createUserInClass(Scanner scann) {
        System.out.println("Creating a user...");
        return new User(strEnteredFromTheConsole(scann, "Please enter username"),
                strEnteredFromTheConsole(scann, "Please enter the user email"),
                strEnteredFromTheConsole(scann, "Please enter the user password"));
    }

    public static String strEnteredFromTheConsole(Scanner scanner, String questionToEnter) {
        String str;
        System.out.print(questionToEnter + ": ");
        str = scanner.nextLine();
        return str;
    }

    public static void workWithTheDatabase(Connection conn, Scanner scann) {

        String str;
        while (true) {
            str = strEnteredFromTheConsole(scann, "Please enter the transaction number:\n" +
                    "   1 - create user\n" +
                    "   2 - search user by ID\n" +
                    "   3 - update user by ID\n" +
                    "   4 - delete user by ID\n" +
                    "   5 - display a list of all users\n" +
                    "   0 - exit\n" +
                    "? ");

            switch (str) {
                case "0" -> System.exit(0);
                case "1" -> createUserInDb(conn, createUserInClass(scann));
                case "2" -> {
                    DbUtil.printUser(DbUtil.getUser(conn,
                            USER_SEARCH_BY_ID_QUERY,
                            Integer.parseInt(strEnteredFromTheConsole(scann, "Please enter User ID"))));
                }
                case "3" -> {
                    DbUtil.runUpdateQueryByID(conn,
                            UPDATE_USER_QUERY,
                            Integer.parseInt(strEnteredFromTheConsole(scann, "Please enter User ID")),
                            "update",
                            scann);
                }
                case "4" -> {
                    DbUtil.runUpdateQueryByID(conn,
                            DELETE_USER_BY_ID_QUERY,
                            Integer.parseInt(strEnteredFromTheConsole(scann, "Please enter User ID")),
                            "delete",
                            scann);
                }
                case "5" -> {
                    DbUtil.printAllUsers(Objects.requireNonNull(DbUtil.getAllUsers(SELECT_ALL_USER_QUERY)));
                }
                default -> System.out.println(RED + "Please enter correct number" + RESET);
            }
        }
    }

    public static void main(String[] args) {
        try (Connection conn = DbUtil.getConnection()) {
            Scanner scann = new Scanner(System.in);

            if (!DbUtil.createDB(conn, scann)) {
                System.out.println("Failed to create database");
                System.exit(0);
            }

            workWithTheDatabase(conn, scann);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Failed connection to SQL");
        }
    }
}
