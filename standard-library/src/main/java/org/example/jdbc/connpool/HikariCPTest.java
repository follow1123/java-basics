package org.example.jdbc.connpool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class HikariCPTest {

    private void doQuery(Connection connection) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "select id, name, age, points from t_user where age < ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, 30);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double points = resultSet.getDouble("points");
                System.out.println(id + "\t" + name + "\t" + age + "\t" + points);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 释放资源
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void testUseHikariInCode() throws Exception {
        InputStream is = HikariCPTest.class.getClassLoader().getResourceAsStream("jdbc_config.properties");
        Properties props = new Properties();
        props.load(is);

        // 获取连接对象信息
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        // 创建Hikari连接池对象
        HikariDataSource hikariDataSource = new HikariDataSource();

        // 必填配置
        hikariDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariDataSource.setJdbcUrl(url);
        hikariDataSource.setUsername(username);
        hikariDataSource.setPassword(password);

        // 非必填配置
        hikariDataSource.setMinimumIdle(5); // 初始连接个数
        hikariDataSource.setMaximumPoolSize(10); // 最大连接个数

        Connection connection = hikariDataSource.getConnection();

        // 使用连接
        doQuery(connection);

        // 回收连接
        connection.close();
    }

    public void testUseHikariWithProperties() throws Exception {
        // 加载配置
        InputStream is = HikariCPTest.class.getClassLoader().getResourceAsStream("hikari.properties");
        Properties props = new Properties();
        props.load(is);

        // 创建配置类
        HikariConfig hikariConfig = new HikariConfig(props);

        // 使用配置类创建Hikari连接池
        HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);

        // 获取连接
        Connection connection = hikariDataSource.getConnection();

        // 使用连接
        doQuery(connection);

        // 回收连接
        connection.close();
    }
}
