package com.javaee.bitlab.database.service;

import com.javaee.bitlab.database.DBConnection;
import com.javaee.bitlab.database.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AdminService extends DBConnection {

    public static ArrayList<User> getUserList() {
        ArrayList<User> userList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT id, full_name, email, is_admin, is_banned FROM users");
            ResultSet resultSet = statement.executeQuery();
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
    public static void toggleBanUser(Long id, boolean action){
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "update users set is_banned = ? where id = ?");
            statement.setBoolean(1, action);
            statement.setLong(2, id);
            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
