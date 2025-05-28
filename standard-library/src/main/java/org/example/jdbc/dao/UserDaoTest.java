package org.example.jdbc.dao;

import org.example.jdbc.dao.impl.UserDaoImpl;
import org.example.jdbc.pojo.User;
import org.example.jdbc.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDaoTest {
    public void testSelectAll() {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.selectAll().forEach(System.out::println);
    }

    public void testSelectOne() {
        UserDaoImpl userDao = new UserDaoImpl();
        User user = userDao.selectById(20L);
        System.out.println(user);
    }

    public void testInsert() {
        UserDaoImpl userDao = new UserDaoImpl();
        User user = new User(null, "zz", 33, 101.3);
        int rows = userDao.insert(user);
        if (rows == 1) {
            System.out.println("insert succeed " + rows);
        } else {
            System.out.println("insert failed");
        }
    }

    public void testUpdate() {
        UserDaoImpl userDao = new UserDaoImpl();
        User user = new User();
        user.setId(6L);
        user.setPoints(200.2);
        int rows = userDao.update(user);
        if (rows == 1) {
            System.out.println("update succeed " + rows);
        } else {
            System.out.println("update failed");
        }
    }

    public void testDelete() {
        UserDaoImpl userDao = new UserDaoImpl();
        int rows = userDao.delete(6L);
        if (rows == 1) {
            System.out.println("delete succeed " + rows);
        } else {
            System.out.println("delete failed");
        }
    }

    /**
     * 测试事务
     */
    public void testTransactions() {
        Connection connection = JDBCUtil.getConnection();
        UserDaoImpl userDao = new UserDaoImpl();
        try {
            connection.setAutoCommit(false);
            userDao.subPoints(1L, 100.0);
            // 模拟两个操作直接出现错误
            // int i = 1 / 0;
            userDao.addPoints(2L, 100.0);
            connection.commit();
            System.out.println("commit");
        } catch (Exception e) {
            try {
                connection.rollback();
                System.out.println("rollback");
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            JDBCUtil.release();
        }
    }
}
