package cn.y.java.oop.object.equals;

import org.junit.jupiter.api.Test;

public class EqualsTest {

    @Test
    public void testEquals() {

        User user1 = new User("zs", 11);
        User user2 = new User("zs", 11);

        // 重写equals方法前，比较对象地址
        System.out.println(user1.equals(user2)); // false

        // 重写equals方法前后，比较对象内的属性
        System.out.println(user1.equals(user2)); // true
    }
}
