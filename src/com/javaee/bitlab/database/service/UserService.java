package com.javaee.bitlab.database.service;

import com.javaee.bitlab.database.DBConnection;
import com.javaee.bitlab.database.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService extends DBConnection {
//    public static ArrayList<Item> getItems() {
//        ArrayList<Item> items = new ArrayList<>();
//        try {
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM items");
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                Item item = new Item(resultSet.getLong("id"),
//                        resultSet.getString("name"),
//                        resultSet.getString("description"),
//                        resultSet.getInt("price"));
//                items.add(item);
//            }
//            statement.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return items;
//    }
//
//    public static Item getItem(Long id) {
//        Item item = null;
//        try {
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM items where id = ?");
//            statement.setLong(1, id);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                item = new Item(resultSet.getLong("id"),
//                        resultSet.getString("name"),
//                        resultSet.getString("description"),
//                        resultSet.getInt("price"));
//            }
//            statement.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return item;
//    }
//
//    public static void addItem(Item item) {
//        try {
//            PreparedStatement statement = connection.prepareStatement(
//                    "INSERT into items (name, description, price) " +
//                            "values (?, ?, ?)");
//            statement.setString(1, item.getName());
//            statement.setString(2, item.getDescription());
//            statement.setInt(3, item.getPrice());
//            statement.executeUpdate();
//            statement.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void deleteItem(Long id) {
//        try {
//            PreparedStatement statement = connection.prepareStatement("delete from items where id = ?");
//            statement.setLong(1, id);
//            statement.executeUpdate();
//            statement.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void updateItem(Item item) {
//        try {
//            PreparedStatement statement = connection.prepareStatement(
//                    "update items " +
//                            "set name = ?," +
//                            "description = ?, " +
//                            "price = ?" +
//                            "where id = ?");
//            statement.setString(1, item.getName());
//            statement.setString(2, item.getDescription());
//            statement.setInt(3, item.getPrice());
//            statement.setLong(4, item.getId());
//            statement.executeUpdate();
//            statement.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static User findUserThroughEmail(String email) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users where email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getBoolean("is_admin")
                );
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public static void RegisterUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT into users " +
                            "(email, password, full_name) " +
                            "values (?, ?, ?, ?)");
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setBoolean(4, user.isAdmin());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
