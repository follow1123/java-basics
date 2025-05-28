package org.example.oop.interfaceobj.newfeatures;

/**
 * 测试接口
 * 静态方法默认方法（jdk8添加）
 * 私有方法（jkd9添加）
 */
public class InterfaceTest {

    /**
     * 测试静态方法
     */
    public void testStaticMethod() {
        A.a1();
        // B.a1(); // 实现类无法调用接口的静态方法，编译不通过
    }

    /**
     * 测试默认方法
     */
    public void testDefaultMethod() {

        B b = new B();
        b.a2(); // 调用A接口内的默认方法

        /*
             B类实现了两个接口A、C
             这两个接口都实现了同名同参的默认方法common1
             实现类必须重写这个方法，否则会报错
         */
        b.common1();

        /*
             子类或实现类继承了父类并实现类了接口，父类和接口内都声明了同名同参的方法
             子类没重写这个方法的情况下默认调用的是父类的方法，类有优先则
         */
        b.common2();
    }
}
