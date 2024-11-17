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

    @Test
    public void testQuerySingleRow() throws Exception{
        String sql = "select id, name, age, points from t_user where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, 1);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            double points = resultSet.getDouble("points");
            System.out.println(id + "\t" + name + "\t" + age + "\t" + points);
        }
        // 释放资源
        resultSet.close();
        statement.close();
    }

    @Test
    public void testQueryMultiRows() throws Exception{
        String sql = "select id, name, age, points from t_user where age < ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, 30);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            double points = resultSet.getDouble("points");
            System.out.println(id + "\t" + name + "\t" + age + "\t" + points);
        }
        // 释放资源
        resultSet.close();
        statement.close();
    }

    @Test
    public void testInsert() throws Exception {
        String sql = "insert into t_user (name, age, points) values (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "bbb");
        statement.setInt(2, 38);
        statement.setDouble(3, 43.532);

        int rows = statement.executeUpdate();
        if (rows > 0){
            System.out.println("insert succeed " + rows);
        }else {
            System.out.println("insert failed");
        }
        // 释放资源
        statement.close();
    }

    @Test
    public void testUpdate() throws Exception{
        String sql = "update t_user set points = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDouble(1, 13.532);
        statement.setLong(2, 6);

        int rows = statement.executeUpdate();
        if (rows > 0){
            System.out.println("update succeed " + rows);
        }else {
            System.out.println("update failed");
        }
        // 释放资源
        statement.close();
    }

    @Test
    public void testDelete() throws Exception{
        String sql = "delete from t_user where id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, 6);

        int rows = statement.executeUpdate();
        if (rows > 0){
            System.out.println("delete succeed " + rows);
        }else {
            System.out.println("delete failed");
        }
        // 释放资源
        statement.close();
    }
}
