package org.example.record;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class RecordTest {

    @Test
    public void test() {
        User user = new User("zs", 28);
        // 获取属性方法
        System.out.println(user.name());
        System.out.println(user.age());

        // 自动重写equals和hashCode方法
        HashSet<User> users = new HashSet<>();
        users.add(user);
        users.add(new User("zs", 28));
        System.out.println(users);
    }
}
