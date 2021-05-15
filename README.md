# UserDao
Training project of user education database
# The second project in Java

Project architect: [*Borys Shvyryd*](https://github.com/BorysShvyryd)

Console program for working with user database. The program data is stored in an SQL database.

**Motivation.** Спроба створити свій другий проект і навчитися працювати з MySQL.

**How to use.** All you need is the `mysql` and` lang` library from the `Apache Commons` package (download the latest version [* here *] (https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 )):

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

## The program has the following functions:

	* search user by ID
	* update user by ID
	* delete user by ID
	* display a list of all users
	* search for a user by name
	* exit
