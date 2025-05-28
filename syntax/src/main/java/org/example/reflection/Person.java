package org.example.reflection;

public class Person {

    public String name;
    private int age;

    public Person() {
        System.out.println("Person init");
    }

    public Person(String name) {
        this.name = name;
    }

    private Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat(){
        System.out.println("吃饭");
    }

    private String sleep(int hours){
        return "睡了" + hours + "个小时";
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
