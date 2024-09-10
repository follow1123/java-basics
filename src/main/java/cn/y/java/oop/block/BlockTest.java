package cn.y.java.oop.block;

import org.junit.jupiter.api.Test;

public class BlockTest {
    @Test
    public void testBlock() {
        System.out.println(Person.info);

        Person zs = new Person("zs");
    }

    @Test
    public void testBlockWithExtends() {
        // 加载子类，会先执行父类的静态代码块再执行子类的静态代码块
        System.out.println(Man.a);

        System.out.println("---");

        // 创建子类会先执行父类的代码块，再执行父类构造方法，最后执行子类代码块
        Man zs = new Man("zs");

    }
}
