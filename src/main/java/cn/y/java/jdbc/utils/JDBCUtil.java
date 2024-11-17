package cn.y.java.jdbc.utils;

import cn.y.java.jdbc.druid.DruidTest;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class JDBCUtil {

    private static DataSource dataSource;

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    static {
        try {
            InputStream is = DruidTest.class.getClassLoader().getResourceAsStream("db.properties");
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
    @SneakyThrows
    public static Connection getConnection(){
        // ThreadLocal内有直接返回
        Connection connection = threadLocal.get();
        if (connection != null) return connection;
        // 没有就从数据库里面获取，并存入ThreadLocal内
        connection = dataSource.getConnection();
        threadLocal.set(connection);
        return connection;
    }

    /**
     * 释放连接
     */
    @SneakyThrows
    public static void release(){
        // ThreadLocal内没有就直接返回
        Connection connection = threadLocal.get();
        if (connection == null) return;
        // 关闭自动提交的连接在归还连接池时要还原成自动提交
        if (!connection.getAutoCommit()) {
            connection.setAutoCommit(true);
        }
        // 有就关闭连接，并从ThreadLocal里面移除
        connection.close();
        threadLocal.remove();
    }
}
