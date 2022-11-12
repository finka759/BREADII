package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserService user = new UserServiceImpl();

        user.createUsersTable();
        user.saveUser("Name1", "LastName1", (byte) 20);
        user.saveUser("Name2", "LastName2", (byte) 25);
        user.saveUser("Name3", "LastName3", (byte) 31);
        user.saveUser("Name4", "LastName4", (byte) 38);

        user.removeUserById(3);
        for (User allUser : user.getAllUsers()) {
            System.out.println(allUser);
        }
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
