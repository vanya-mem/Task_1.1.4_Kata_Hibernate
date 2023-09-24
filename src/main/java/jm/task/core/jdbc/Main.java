package main.java.jm.task.core.jdbc;

import main.java.jm.task.core.jdbc.service.UserServiceImpl;
import main.java.jm.task.core.jdbc.util.Util;



public class Main {

    public static void main(String[] args) {
        Util.getConnection();
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Name1", "LastName1", (byte) 20);
        userService.saveUser("Name2", "LastName2", (byte) 25);
        userService.saveUser("Name3", "LastName3", (byte) 31);
        userService.saveUser("Name4", "LastName4", (byte) 38);

        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

        Util.closeConnection();
    }
}
