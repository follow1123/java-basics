package org.example.jdbc.utils;


import java.sql.Connection;

public class JDBCUtilTest {
    public static void main(String[] args) {
        Connection connection1 = JDBCUtil.getConnection();
        Connection connection2 = JDBCUtil.getConnection();
        Connection connection3 = JDBCUtil.getConnection();

        System.out.println(connection1);
        System.out.println(connection2);
        System.out.println(connection3);
    }
}
