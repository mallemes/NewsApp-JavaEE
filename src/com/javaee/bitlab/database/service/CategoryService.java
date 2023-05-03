package com.javaee.bitlab.database.service;

import com.javaee.bitlab.database.DBConnection;
import com.javaee.bitlab.database.models.Category;
import com.javaee.bitlab.database.models.News;
import com.javaee.bitlab.database.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoryService extends DBConnection {

    public static ArrayList<Category> getCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from category");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                categories.add(new Category(
                        resultSet.getLong("id"),
                        resultSet.getString("name")));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    public static ArrayList<News> getCategoryNews(Long categoryId) {

        ArrayList<News> comments = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT n.id, n.comment, n.created_at, n.cat_id, n.user_id, u.full_name " +
                    "FROM news n " +
                    "INNER JOIN users u ON u.id = n.user_id " +
                    "WHERE n.cat_id = ? " +
                    "ORDER BY n.created_at DESC");

            statement.setLong(1, categoryId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                News news = new News();
                news.setId(resultSet.getLong("id"));
                news.setContent(resultSet.getString("content"));
                news.setCreatedAt(resultSet.getTimestamp("created_at"));
                news.setTitle(resultSet.getString("title"));
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                news.setUser(user);
                comments.add(news);
            }
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }

}
