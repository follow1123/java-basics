package cn.y.java.oop.keywords.super_keyword;


/**
 * 学生
 */
public class Student extends Person {

    int id;

    public Student() {
    }

    public Student(int id, String name, int age) {
        super(id, name, age);
    }

    public void showId(){
        System.out.println(this.id);
        System.out.println(super.id);
    }

    @Override
    public void eat() {
        System.out.println(name + "正在吃饭");
        super.eat();
    }


    public void study(){
        System.out.println("学习");
    }
}
