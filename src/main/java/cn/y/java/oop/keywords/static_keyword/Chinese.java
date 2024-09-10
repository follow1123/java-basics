package cn.y.java.oop.keywords.static_keyword;

public class Chinese {
    String name;

    int age;

    public static String nation = "中国";

    public Chinese(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat(){
        System.out.println("吃饭");
    }

    public static void show_nation(){
        System.out.println("我的国际是：" + nation);

        // eat(); // 无法调用实例方法，编译不通过
    }

    public void show_info(){
        System.out.println("我的名字是：" + name);
        show_nation(); // 可以调用静态方法
    }

}