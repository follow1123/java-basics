package cn.y.java.oop.constructor;

/**
 * 构造器重载
 */
public class OverloadConstructorClass {

    String name;

    int age;

    /**
     * 只有age的构造器
     */
    public OverloadConstructorClass(int age) {
        this.age = age;
    }

    /**
     * 全部参数构造器
     */
    public OverloadConstructorClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        OverloadConstructorClass zs = new OverloadConstructorClass("zs", 11);
        System.out.println(zs.name); // zs
        System.out.println(zs.age); // 11
        OverloadConstructorClass zs1 = new OverloadConstructorClass(11);
        System.out.println(zs1.name); // null
        System.out.println(zs1.age); // 11
    }
}
