package main.java.jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/newdatabase";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "1234";

    private static Connection connection;
    public static Connection getConnection() {
        connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        }
        catch (SQLException e) {}
    }
}
