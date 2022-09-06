package com.example.esapidemo.crawler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author ：zhangsw
 * @date ：2022/9/6
 */
public class DataConnection {

    public static Connection connection = null;


    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/testdb?characterEncoding=utf8&allowMultiQueries=true&serverTimezone=Asia/Shanghai";
        String username = "root";
        String pass = "123456";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, pass);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
