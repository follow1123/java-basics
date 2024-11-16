package cn.y.java.jdbc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCOperationTest {

    private static Connection connection;

    @BeforeEach
    void initConnection(){
        InputStream is = JDBCTest.class.getClassLoader().getResourceAsStream("jdbc_config.propreties");
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

    /**
     * 查询单行单列
     */
    @Test
    public void testQuerySingleRowAndCol() throws Exception {
        String sql = "select count(*) from t_user";
        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();
        // 只有行，不需要遍历
        if (resultSet.next()) {
            // 只有一列，可以直接使用下标获取数据
            long count = resultSet.getLong(1);
            System.out.println(count);
        }
        // 释放资源
        resultSet.close();
        statement.close();
    }
}
