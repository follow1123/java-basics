package org.example.jdbc.connpool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DruidTest {
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

    public void testUseDruidInCode() throws Exception {
        InputStream is = DruidTest.class.getClassLoader().getResourceAsStream("jdbc_config.properties");
        Properties props = new Properties();
        props.load(is);


        // 获取连接对象信息
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        // 创建Druid连接池对象
        DruidDataSource druidDataSource = new DruidDataSource();

        // 必填配置
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);

        // 非必填配置
        druidDataSource.setInitialSize(5); // 初始连接个数
        druidDataSource.setMaxActive(10); // 最大连接个数

        Connection connection = druidDataSource.getConnection();

        // 使用连接
        doQuery(connection);

        // 回收连接
        connection.close();
    }

    public void testUseDruidWithProperties() throws Exception {
        // 加载配置
        InputStream is = DruidTest.class.getClassLoader().getResourceAsStream("db.properties");
        Properties props = new Properties();
        props.load(is);

        // 使用配置获取Druid连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(props);

        // 获取连接
        Connection connection = dataSource.getConnection();

        // 使用连接
        doQuery(connection);

        // 回收连接
        connection.close();
    }
}
