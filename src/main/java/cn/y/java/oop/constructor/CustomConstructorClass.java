package cn.y.java.oop.constructor;

/**
 * 自定义构造器
 */
public class CustomConstructorClass {

    String name;

    int age;

    public CustomConstructorClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        CustomConstructorClass zs = new CustomConstructorClass("zs", 11);

        // CustomConstructorClass zs1 = new CustomConstructorClass() // 没有无参构造器，编译不通过

        // 给对象的属性赋值
        System.out.println(zs.name); // zs
        System.out.println(zs.age); // 11
    }
}
