package com.javaee.bitlab.database.service;

import com.javaee.bitlab.database.DBConnection;
import com.javaee.bitlab.database.models.Category;
import com.javaee.bitlab.database.models.News;
import com.javaee.bitlab.database.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NewsService extends DBConnection {

    public static ArrayList<News> getNewsList() {
        ArrayList<News> newsList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT n.id, n.title, n.content, n.created_at, n.cat_id, n.user_id, " +
                    "cat.name, u.full_name FROM news n " +
                    "INNER JOIN users u ON u.id = n.user_id " +
                    "INNER JOIN categories cat ON cat.id = n.cat_id " +
                    "ORDER BY n.created_at DESC");
            ResultSet resultSet = statement.executeQuery();
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


    public static News getNews(Long id) {
        News news = null;
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT n.id, n.title, n.content, n.created_at, n.cat_id, n.user_id, " +
                    "cat.name, u.full_name FROM news n " +
                    "INNER JOIN users u ON u.id = n.user_id " +
                    "INNER JOIN categories cat ON cat.id = n.cat_id " +
                    "where n.id = ?"
                    );
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                news = new News(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        new User(resultSet.getLong("user_id"),
                                resultSet.getString("full_name")),
                        new Category(resultSet.getLong("cat_id"),
                                resultSet.getString("name")),
                        resultSet.getTimestamp("created_at"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    public static void createNews(News news) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT into news (title, content, user_id, cat_id, created_at) " +
                            "values (?, ?, ?, ?, NOW())");
            statement.setString(1, news.getTitle());
            statement.setString(2, news.getContent());
            statement.setLong(3, news.getUser().getId());
            statement.setLong(4, news.getCategory().getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteNews(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("delete from news where id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateNews(News news) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "update news " +
                            "set title = ?," +
                            "content = ?, " +
                            "cat_id = ?" +
                            "where id = ?");
            statement.setString(1, news.getTitle());
            statement.setString(2, news.getContent());
            statement.setLong(3, news.getCategory().getId());
            statement.setLong(4, news.getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
