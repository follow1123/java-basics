package cn.y.java.oop.keywords.static_keyword;

import org.junit.jupiter.api.Test;

public class StaticTest {

    @Test
    public void testStaticField() {
        // 在对象创建之前就可以使用
        System.out.println(Chinese.nation);

        Chinese zs = new Chinese("zs", 11);
        Chinese ls = new Chinese("ls", 12);

        // 一个对象修改了实例变量，所有对象都生效
        zs.nation = "china";
        System.out.println(zs.nation); // china
        System.out.println(ls.nation); // china
    }

    @Test
    public void testStaticMethod() {
        Chinese.show_nation();

        Chinese zs = new Chinese("zs", 11);

        // 实例方法内调用静态方法
        zs.show_info();

    }
}
