package com.javaee.bitlab.database;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnection {
    protected static Connection connection;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            final URL resource = DBConnection.class.getResource("db.sqlite3");
            String path = new File(resource.toURI()).getAbsolutePath();
            connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s",path), "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
