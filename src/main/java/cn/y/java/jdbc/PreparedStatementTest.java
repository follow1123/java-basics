package cn.y.java.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class PreparedStatementTest {
    public static void main(String[] args) throws Exception{
        // 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        InputStream is = JDBCTest.class.getClassLoader().getResourceAsStream("jdbc_config.propreties");
        Properties props = new Properties();
        props.load(is);

        // 获取连接对象
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");
        Connection connection = DriverManager.getConnection(url, username, password);

        // 获取PreparedStatement
        String sql = "select id, name, age, points from t_user where name = ?";
        PreparedStatement statement = connection.prepareStatement(sql);

        // 设置参数
        statement.setString(1, "zhangsan");

        // 编写SQL语句并执行
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            // 通过列下标获取数据，下标从1开始，不推荐
            int id = resultSet.getInt(1);
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
