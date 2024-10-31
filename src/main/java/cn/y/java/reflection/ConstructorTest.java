package cn.y.java.reflection;

import cn.y.java.reflection.data.User;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

public class ConstructorTest {

    @Test
    public void testNewInstance() throws Exception {
        Class<User> userClass = User.class;
        /*
            使用这种方式调用的是类的空参构造器，如果类没有空参构造器会报错
            空参构造器的权限必须是public的
         */
        User user = userClass.newInstance();
        System.out.println(user);
    }

    @Test
    public void testInvokeConstructor() throws Exception {
        Class<User> userClass = User.class;
        Constructor<User> c1 = userClass.getDeclaredConstructor(String.class, int.class);
        c1.setAccessible(true);
        User user1 = c1.newInstance("zs", 18);
        System.out.println(user1);

    }
}
