package cn.y.java.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTest {
    public static void main(String[] args) throws Exception {
        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        InputStream is = JDBCTest.class.getClassLoader().getResourceAsStream("jdbc_config.properties");
        Properties props = new Properties();
        props.load(is);

        // 获取连接对象
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        Connection connection = DriverManager.getConnection(url, username, password);

        // 获取执行SQL的对象
        Statement statement = connection.createStatement();

        // 编写SQL语句并执行
        String sql = "select id, name, age, points from t_user";
        ResultSet resultSet = statement.executeQuery(sql);

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
        connection.close();
    }
}
