package cn.y.java.jdbc;

import cn.y.java.jdbc.pojo.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ReturnPKTest {

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
    public void testReturnPK() throws Exception{
        String sql = "insert into t_user (name, age, points) values (?, ?, ?)";
        // 指定需要回显主键
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        // 使用对象传递参数
        User user = new User(null, "ccc", 28, 345.3);
        statement.setString(1, user.getName());
        statement.setInt(2, user.getAge());
        statement.setDouble(3, user.getPoints());

        ResultSet resultSet = null;
        int rows = statement.executeUpdate();
        if (rows > 0){
            System.out.println("insert succeed " + rows);
            // 获取回显的主键
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()){
                user.setId(resultSet.getLong(1));
            }
        }else {
            System.out.println("insert failed");
        }

        // 处理数据
        System.out.println(user);

        // 释放资源
        if (resultSet != null) resultSet.close();
        statement.close();
    }
}
