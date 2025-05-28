package org.example.oop.override;

/**
 * 学生
 */
public class Student extends Person {
    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public void eat() {
        System.out.println(name + "正在吃饭");
    }

    @Override
    public void read(String word) {
        System.out.println(name + "读" + word);
    }

    @Override
    public void sleep(int hour) {
        System.out.println(name + "准备睡" + hour + "小时");
    }

    @Override
    public String info() {
        return "姓名：" + name + "\t年龄" + age + "岁";
    }

    public void study() {
        System.out.println("学习");
    }
}
