package org.example.oop.inheritance;

public class InheritanceTest {
    public void testInheritance() {
        Student student = new Student("zs", 11);

        // 调用父类中的属性
        System.out.println(student.name);
        System.out.println(student.age);

        // 调用父类中的方法
        student.eat();
        // 调用自己的方法
        student.study();
    }
}
