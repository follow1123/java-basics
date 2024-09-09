package cn.y.java.oop.inheritance;

/**
 * 学生
 */
public class Student extends Person{

    public Student() {
    }

    public Student(String name, int age){
        this.name = name;
        this.age = age;
    }


    public void study(){
        System.out.println("学习");
    }
}
