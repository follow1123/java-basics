package cn.y.java.oop.keywords;

/**
 * 测试this调用构造器
 */
public class ThisClass {

    private String name;

    private int age;

    public ThisClass(){
        System.out.println("初始化代码执行");
    }

    public ThisClass(String name) {
        this();
        this.name = name;
    }

    public ThisClass(String name, int age) {
        this();
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        // 下面三行都会输出 初始化代码执行
        ThisClass thisClass = new ThisClass();
        ThisClass thisClass1 = new ThisClass("zs");
        ThisClass thisClass2 = new ThisClass("zs", 11);
    }
}
