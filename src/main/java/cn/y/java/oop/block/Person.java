package cn.y.java.oop.block;

public class Person {
    String name;

    static String info = "123";

    public Person(String name) {
        System.out.println("构造器执行");
        this.name = name;
    }

    public void eat(){
        System.out.println("吃饭");
    }

    {
        System.out.println("代码块执行2");
    }
    {
        System.out.println("代码块执行1");
    }

    static {
        System.out.println("静态代码块执行1");
    }
    static {
        System.out.println("静态代码块执行2");
    }
}
