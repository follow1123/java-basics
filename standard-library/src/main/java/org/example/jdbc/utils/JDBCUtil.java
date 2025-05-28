package org.example.jdbc.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
    private static final DataSource dataSource;

    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        try {
            InputStream is = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
            Properties props = new Properties();
            props.load(is);
            // 使用配置获取Druid连接池
            dataSource = DruidDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取连接
     */
    public static Connection getConnection() {
        // ThreadLocal内有直接返回
        Connection connection = threadLocal.get();
        if (connection != null) return connection;
        // 没有就从数据库里面获取，并存入ThreadLocal内
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        threadLocal.set(connection);
        return connection;
    }

    /**
     * 释放连接
     */
    public static void release() {
        // ThreadLocal内没有就直接返回
        Connection connection = threadLocal.get();
        if (connection == null) return;
        // 关闭自动提交的连接在归还连接池时要还原成自动提交
        try {
            if (!connection.getAutoCommit()) {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // 有就关闭连接，并从ThreadLocal里面移除
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        threadLocal.remove();
    }
}
