package org.example.oop.override;

public class OverrideTest {
    public void testOverride() {
        // 调用父类的方法
        Person person = new Person();
        person.name = "人";
        person.age = 11;
        person.eat();
        person.read("123");
        person.sleep(21);
        System.out.println(person.info());

        System.out.println("---");
        // 调用子类重写后的方法
        Student student = new Student("sz", 12);
        student.eat();
        student.read("书");
        student.sleep(6);
        System.out.println(student.info());
    }
}
