package com.javaee.bitlab.database.service;

import com.javaee.bitlab.database.DBConnection;
import com.javaee.bitlab.database.models.Category;
import com.javaee.bitlab.database.models.News;
import com.javaee.bitlab.database.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NewsService extends DBConnection {

    public static ArrayList<News> allNews() {
        ArrayList<News> newsList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT n.id, n.title, n.content, n.created_at, n.cat_id, n.user_id, " +
                    "cat.name, u.full_name FROM news n " +
                    "INNER JOIN users u ON u.id = n.user_id " +
                    "INNER JOIN categories cat ON cat.id = n.cat_id " +
                    "ORDER BY n.created_at DESC");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                News news = new News();
                news.setId(resultSet.getLong("id"));
                news.setTitle(resultSet.getString("title"));
                news.setContent(resultSet.getString("content"));
                news.setCreatedAt(resultSet.getTimestamp("created_at"));
                news.setCategory(new Category(resultSet.getLong("cat_id"),
                                              resultSet.getString("name")));
                news.setUser(new User(resultSet.getLong("user_id"),
                                      resultSet.getString("full_name")));
                newsList.add(news);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsList;
    }

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
}
