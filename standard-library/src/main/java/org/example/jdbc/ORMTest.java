package org.example.jdbc;

import org.example.jdbc.pojo.User;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class ORMTest {
    private static Connection initConnection() {
        InputStream is = JDBCTest.class.getClassLoader().getResourceAsStream("jdbc_config.properties");
        Properties props = new Properties();
        try {
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 获取连接对象
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 单个对象
     */
    public void testOneObj() throws Exception {
        Connection connection = initConnection();
        String sql = "select id, name, age, points from t_user where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, 1);

        ResultSet resultSet = statement.executeQuery();
        User user = null;
        // 只有行，不需要遍历
        if (resultSet.next()) {
            user = new User();
            // 只有一列，可以直接使用下标获取数据
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            double points = resultSet.getDouble("points");
            user.setId(id);
            user.setName(name);
            user.setAge(age);
            user.setPoints(points);
        }

        // 处理数据
        System.out.println(user);

        // 释放资源
        resultSet.close();
        statement.close();
        releaseConnection(connection);
    }

    /**
     * 对象集合
     */
    public void testOjbList() throws Exception {
        Connection connection = initConnection();
        String sql = "select id, name, age, points from t_user where age < ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, 30);

        ResultSet resultSet = statement.executeQuery();
        ArrayList<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            double points = resultSet.getDouble("points");
            user.setId(id);
            user.setName(name);
            user.setAge(age);
            user.setPoints(points);
            users.add(user);
        }
        // 处理数据
        users.forEach(System.out::println);

        // 释放资源
        resultSet.close();
        statement.close();
        releaseConnection(connection);
    }
}
