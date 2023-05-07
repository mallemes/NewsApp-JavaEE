package com.javaee.bitlab.database.service;

import com.javaee.bitlab.database.DBConnection;
import com.javaee.bitlab.database.models.Comment;

import java.sql.PreparedStatement;

public class CommentService extends DBConnection {

    public static void addComment(Comment comment) {
        try {
            String sql = "INSERT INTO comments (comment, user_id, news_id, created_at) VALUES (?, ?, ?,  datetime('now', 'localtime'))";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, comment.getComment());
            statement.setLong(2, comment.getUser().getId());
            statement.setLong(3, comment.getNews().getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteComment(Long id){
        try {
            PreparedStatement statement = connection.prepareStatement("delete from comments where id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

