package org.example.jdbc.dao.impl;

import org.example.jdbc.dao.BaseDao;
import org.example.jdbc.dao.UserDao;
import org.example.jdbc.pojo.User;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public List<User> selectAll() {
        String sql = "select id, name, age, points from t_user";
        try {
            return executeQuery(sql, User.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User selectById(Long id) {
        String sql = "select id, name, age, points from t_user where id = ?";
        User user = null;
        try {
            user = executeQueryOne(sql, User.class, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public int insert(User user) {
        String sql =  "insert into t_user (name, age, points) values (?, ?, ?)";
        try {
            return executeUpdate(sql, user.getName(), user.getAge(), user.getPoints());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(User user) {
        String sql = "update t_user set points = ? where id = ?";
        try {
            return executeUpdate(sql, user.getPoints(), user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(Long id) {
        String sql = "delete from t_user where id = ?";
        try {
            return executeUpdate(sql, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int addPoints(Long id, Double points) {
        String sql = "update t_user set points = points + ? where id = ?";
        try {
            return executeUpdate(sql, points, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int subPoints(Long id, Double points) {
        String sql = "update t_user set points = points - ? where id = ?";
        try {
            return executeUpdate(sql, points, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
