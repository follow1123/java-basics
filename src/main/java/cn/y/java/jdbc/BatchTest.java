package cn.y.java.jdbc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class BatchTest {

    private static Connection connection;

    @BeforeEach
    void initConnection(){
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
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void releaseConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void testNonBatchInsert() throws Exception{
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
    }

    @Test
    public void testBatchInsert() throws Exception{
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
    }
}
