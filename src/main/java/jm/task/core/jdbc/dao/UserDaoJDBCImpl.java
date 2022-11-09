//package jm.task.core.jdbc.dao;
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDaoJDBCImpl implements UserDao {
//
//    public UserDaoJDBCImpl() {
//    }
//
//    public void createUsersTable() throws SQLException {
//        Connection connection = Util.getConnection();
//        try (Statement statement = connection.createStatement()){
//            statement.execute("CREATE TABLE IF NOT EXISTS `mydbtest`.`users` (\n" +
//                    "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
//                    "  `name` VARCHAR(45) NULL,\n" +
//                    "  `lastName` VARCHAR(45) NULL,\n" +
//                    "  `age` TINYINT(3) NULL,\n" +
//                    "  PRIMARY KEY (`id`));");
//            System.out.println("Table created");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if (connection != null) {
//            connection.close();
//        }
//    }
//
//    public void dropUsersTable() throws SQLException {
//        Connection connection = Util.getConnection();
//        try (Statement statement = connection.createStatement()) {
//            statement.executeUpdate("drop table IF EXISTS users");
//            System.out.println("Table deleted");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if (connection != null) {
//            connection.close();
//        }
//    }
//
//    public void saveUser(String name, String lastName, byte age) throws SQLException {
//        Connection connection = Util.getConnection();
//        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, lastName);
//            preparedStatement.setByte(3, age);
//            preparedStatement.executeUpdate();
//            System.out.println("User – " + name + " добавлен в базу данных");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if (connection != null) {
//            connection.close();
//        }
//    }
//
//    public void removeUserById(long id) throws SQLException {
//        Connection connection = Util.getConnection();
//        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id=?")) {
//            preparedStatement.setLong(1, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if (connection != null) {
//            connection.close();
//        }
//    }
//
//    public List<User> getAllUsers() throws SQLException {
//        Connection connection = Util.getConnection();
//        UserDao userDao = new UserDaoJDBCImpl();
//        userDao.createUsersTable();
//        List<User> userList = new ArrayList<>();
//        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users")) {
//            ResultSet resultSet = preparedStatement.executeQuery("SELECT * FROM users");
//            while (resultSet.next()) {
//                User user = new User();
//                user.setId(resultSet.getLong("id"));
//                user.setName(resultSet.getString("name"));
//                user.setLastName(resultSet.getString("lastname"));
//                user.setAge(resultSet.getByte("age"));
//                userList.add(user);
//            }
//            if (resultSet != null) {
//                resultSet.close();
//            }
//            System.out.println("Пользователи получены :");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if (connection != null) {
//            connection.close();
//        }
//
//        return userList;
//    }
//
//    public void cleanUsersTable() throws SQLException {
//        Connection connection = Util.getConnection();
//        try (Statement statement = connection.createStatement()){
//            statement.executeUpdate("TRUNCATE TABLE users");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if (connection != null) {
//            connection.close();
//        }
//    }
//}