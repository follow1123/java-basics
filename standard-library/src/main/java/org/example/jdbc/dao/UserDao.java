package org.example.jdbc.dao;

import org.example.jdbc.pojo.User;

import java.util.List;

public interface UserDao {

    /**
     * 获取所有用户
     */
    List<User> selectAll();

    /**
     * 根据id获取单个用户
     */
    User selectById(Long id);

    /**
     * 添加用户
     */
    int insert(User user);

    /**
     * 删除用户
     */
    int update(User user);

    /**
     * 根据id删除用户
     */
    int delete(Long id);

    /**
     * 添加积分
     */
    int addPoints(Long id, Double points);

    /**
     * 减少积分
     */
    int subPoints(Long id, Double points);
}
