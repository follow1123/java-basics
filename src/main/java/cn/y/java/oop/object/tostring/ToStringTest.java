package cn.y.java.oop.object.tostring;

import org.junit.jupiter.api.Test;

public class ToStringTest {

    @Test
    public void testToString() {
        User zs = new User("zs", 11);
        System.out.println(zs);
    }
}
