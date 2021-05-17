# UserDao
User database training project
# The second project in Java

Project architect: [*Borys Shvyryd*](https://github.com/BorysShvyryd)

Console program for working with user database. The program data is stored in an SQL database.

**Motivation.** Trying to create your second project and learn how to work with JAVA and MySQL.

**How to use.** All you need is the `mysql`, `jbcrypt` and` lang` library. (Download the latest version ` lang` library from the `Apache Commons` package [*here*](https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 )):

**Maven:**
```xml
<dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.20</version>
</dependency>
```

```xml
<dependency>
	<groupId>org.apache.commons</groupId>
        <artifactId>commons-lang3</artifactId>
        <version>3.12.0</version>
</dependency>
```

```xml
<dependency>
            <groupId>org.mindrot</groupId>
            <artifactId>jbcrypt</artifactId>
            <version>0.4</version>
</dependency>
```

## The program has the following functions:

	* search user by ID
	* update user by ID
	* delete user by ID
	* display a list of all users
	* search for a user by name
	* exit
