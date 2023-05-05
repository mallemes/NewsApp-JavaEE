package com.javaee.bitlab.database.service;

import com.javaee.bitlab.database.DBConnection;
import com.javaee.bitlab.database.models.Category;
import com.javaee.bitlab.database.models.News;
import com.javaee.bitlab.database.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AdminService extends DBConnection {

    public static ArrayList<User> getUserList() {
        ArrayList<User> userList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id, full_name, email, is_admin FROM users");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setAdmin(resultSet.getBoolean("is_admin"));
                user.setEmail(resultSet.getString("email"));
                user.setBanned(resultSet.getBoolean("is_banned"));
                userList.add(user);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }
    public static void banUser(Long id){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "from users update set is_banned = true where id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void unbanUser(Long id){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "from users update set is_banned = false where id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
