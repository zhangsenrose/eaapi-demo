package com.example.esapidemo.crawler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author ：zhangsw
 * @date ：2022/9/6
 */
public class TestDb {

    public static void main(String[] args) throws SQLException {
        Connection connection = DataConnection.getConnection();
        String sql = "insert into blog(title, content, url, category) values('xxx', 'yyyy', 'www.xxo.com', 'Java')";
        final PreparedStatement preparedStatement = connection.prepareStatement(sql);
        final boolean execute = preparedStatement.execute();
        System.out.println("--------------->" + execute);
    }
}
