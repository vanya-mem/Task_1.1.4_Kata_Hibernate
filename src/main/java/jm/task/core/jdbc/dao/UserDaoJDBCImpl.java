package main.java.jm.task.core.jdbc.dao;

import main.java.jm.task.core.jdbc.model.User;
import main.java.jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Statement statement;
    private String sqlCommand;
    private final Connection conn = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            sqlCommand = "CREATE TABLE `newdatabase`.`user` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastname` VARCHAR(45) NOT NULL,\n" +
                    "  `age` TINYINT(3) UNSIGNED NOT NULL,\n" +
                    "  PRIMARY KEY (`id`))\n" +
                    "ENGINE = InnoDB\n" +
                    "DEFAULT CHARACTER SET = utf8;";
            statement = conn.createStatement();
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {}

    }

    public void dropUsersTable() {
        try {
            sqlCommand = "DROP TABLE newdatabase.user;";
            statement = conn.createStatement();
            statement.executeUpdate(sqlCommand);
        }
        catch (SQLException e) {}
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            sqlCommand = String.format("INSERT INTO newdatabase.user (`name`, `lastName`, `age`) VALUES ('%s', '%s', '%s');\n",
                        name, lastName, age);
            statement = conn.createStatement();
            statement.executeUpdate(sqlCommand);
        }
        catch (SQLException e) {}
    }

    public void removeUserById(long id) {
        try {
            sqlCommand = String.format("DELETE FROM newdatabase.user WHERE (`id` = %s);", id);
            statement = conn.createStatement();
            statement.executeUpdate(sqlCommand);
        }
        catch (SQLException e) {}
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            sqlCommand = "SELECT id, name, lastname, age FROM newdatabase.user;";
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"), resultSet.getString("lastname"),
                            resultSet.getByte("age"));
                user.setId((long) resultSet.getInt("id"));
                userList.add(user);
            }
            resultSet.close();
        }
        catch (SQLException e) {}
        return userList;
    }

    public void cleanUsersTable() {
        try {
            sqlCommand = "DELETE FROM newdatabase.user;";
            statement = conn.createStatement();
            statement.executeUpdate(sqlCommand);
        }
        catch (SQLException e) {}
    }
}
