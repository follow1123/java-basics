package org.example.oop.keywords.superkeyword;

/**
 * 人
 */
public class Person {
    int id;

    String name;

    int age;

    public Person() {
        System.out.println("初始化Person");
    }

    public Person(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println("吃饭");
    }

}
