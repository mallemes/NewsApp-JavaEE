package com.javaee.bitlab.database.service;

import com.javaee.bitlab.database.DBConnection;
import com.javaee.bitlab.database.models.Category;
import com.javaee.bitlab.database.models.Comment;
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
                            "values (?, ?, ?, ?, datetime('now', 'localtime'))");
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

    public static void deleteNews(News news) {
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "DELETE FROM news WHERE id = ?; ");
            statement.setLong(1, news.getId());
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

    public static ArrayList<Comment> getNewsComment(Long newsId) {
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT c.id, c.comment, c.created_at, c.user_id, u.full_name FROM comments c " +
                    "INNER JOIN users u ON u.id = c.user_id " +
                    "where c.news_id = ? " +
                    "ORDER BY c.created_at DESC");
            statement.setLong(1, newsId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setCreatedAt(resultSet.getTimestamp("created_at"));
                comment.setUser(new User(resultSet.getLong("user_id"),
                        resultSet.getString("full_name")));
                comments.add(comment);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }
    public static ArrayList<News> searchNews(String key) {
        ArrayList<News> newsList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT n.id, n.title, n.content, n.created_at, n.cat_id, n.user_id, " +
                            "cat.name, u.full_name FROM news n " +
                            "INNER JOIN users u ON u.id = n.user_id " +
                            "INNER JOIN categories cat ON cat.id = n.cat_id " +
                            "WHERE LOWER(n.title) LIKE LOWER(?) " +
                            "ORDER BY n.created_at DESC");
            statement.setString(1, key);
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
}
