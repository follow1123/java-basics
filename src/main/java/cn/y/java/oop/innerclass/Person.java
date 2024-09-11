package cn.y.java.oop.innerclass;

public class Person {

    String name = "person";
    int age;

    static class Dog{

    }

    class Bird{

        String name = "bird";
        
        public void show(String name){
            System.out.println("age = " + age); // 调用外部内的属性
            System.out.println("name = " + name); // 调用形参
            System.out.println("this.name = " + this.name); // 调用内部类的成员变量
            System.out.println("Person.this.name = " + Person.this.name); // 调用外部类的成员变量

        }

    }

    public void method(){
        class InnerClass1{

        }
    }

    public Person(){
        class InnerClass1{

        }
    }

    {
        class InnerClass1{

        }
    }
}
