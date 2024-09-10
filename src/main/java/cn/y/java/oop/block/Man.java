package cn.y.java.oop.block;

public class Man extends Person{

    {
        System.out.println("2 代码块执行");
    }

    static {
        System.out.println("2 静态代码块执行");
    }

    static int a;
    public Man(String name) {
        super(name);
    }
}
