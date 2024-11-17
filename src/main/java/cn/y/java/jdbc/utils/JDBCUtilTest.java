package cn.y.java.jdbc.utils;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class JDBCUtilTest {

    @Test
    public void testGetConnections() {
        Connection connection1 = JDBCUtil.getConnection();
        Connection connection2 = JDBCUtil.getConnection();
        Connection connection3 = JDBCUtil.getConnection();

        System.out.println(connection1);
        System.out.println(connection2);
        System.out.println(connection3);
    }
}
