package com.javaee.bitlab.database;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    protected static Connection connection;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
            Path currentPath = Paths.get(DBConnection.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            Path rootDir = currentPath.getParent().getParent().getParent().getParent().getParent();
            Path dbPath = rootDir.resolve("db.sqlite3");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath, "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    static {
//        try {
//            Class.forName("org.sqlite.JDBC");
//            final URL resource = DBConnection.class.getResource("db.sqlite3");
//            String path = new File(resource.toURI()).getAbsolutePath();
//            connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s",path), "", "");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
