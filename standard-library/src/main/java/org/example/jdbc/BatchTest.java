package org.example.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class BatchTest {
    private Connection initConnection() {
        InputStream is = BatchTest.class.getClassLoader().getResourceAsStream("jdbc_config.properties");
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

    public void testNonBatchInsert() throws Exception {
        Connection connection = initConnection();
        // 连接：jdbc:mysql://localhost:3307/mini_chat

        String sql = "insert into t_user (name, age, points) values (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            statement.setString(1, "bbb" + i);
            statement.setInt(2, 38 + i);
            statement.setDouble(3, 43.532 + i);
            statement.executeUpdate();
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);
        releaseConnection(connection);
    }

    public void testBatchInsert() throws Exception {
        Connection connection = initConnection();
        // 连接：jdbc:mysql://localhost:3307/mini_chat?rewriteBatchedStatements=true
        String sql = "insert into t_user (name, age, points) values (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            statement.setString(1, "bbb" + i);
            statement.setInt(2, 38 + i);
            statement.setDouble(3, 43.532 + i);
            statement.addBatch();
        }
        statement.executeBatch();
        long end = System.currentTimeMillis();

        System.out.println(end - start);
        releaseConnection(connection);
    }
}
