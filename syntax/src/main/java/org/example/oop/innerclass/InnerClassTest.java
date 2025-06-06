package org.example.oop.innerclass;

public class InnerClassTest {
    public void testInnerClass() {

        // 创建静态成员内部类
        Person.Dog dog = new Person.Dog();

        // 创建非静态成员内部类
        Person.Bird bird = new Person().new Bird();
        // 或
        Person person = new Person();
        Person.Bird bird1 = person.new Bird();
    }

    /**
     * 测试内部内调用外部类属性
     */
    public void testInnerClassFields() {
        Person.Bird bird = new Person().new Bird();
        bird.show("new bird");
    }

    public void testLocalInnerClass() {
        class Task implements Runnable {
            @Override
            public void run() {
                System.out.println("run");
            }
        }

        Task task = new Task();
        task.run();
    }
}
