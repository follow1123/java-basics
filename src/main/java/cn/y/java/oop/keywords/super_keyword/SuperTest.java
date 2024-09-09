package cn.y.java.oop.keywords.super_keyword;

import org.junit.jupiter.api.Test;

public class SuperTest {
    @Test
    public void testMethodAndField() {

        Student zs = new Student(1, "zs", 11);

        // 子类重写方法后调用父类的方法
        zs.eat();

        // 子类打印属性后打印父类的同名属性
        zs.showId();
    }

    @Test
    public void testConstructor() {
        // 调用子类无参构造器会默认调用父类无参构造器，打印里面的信息
        Student student = new Student();


        // 使用super(形参列表)会初始化父类里面的属性
        Student zs = new Student(1, "zs", 11);
        // 由于Student有一个同名的属性，只初始化了父类的属性，两个id属性的值不同
        zs.showId();
    }
}
