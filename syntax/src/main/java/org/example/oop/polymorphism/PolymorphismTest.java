package org.example.oop.polymorphism;

public class PolymorphismTest {

    public void testPolymorphism1() {
        Person person = new Person();

        Person woman = new Woman();

        Person man = new Man();


        person.eat();

        // 多态，实际调用子类方法，这种方式称为虚方法
        woman.eat();
        man.eat();

        // 属性不适用与多态

        System.out.println(person.id); // 1
        System.out.println(woman.id); // 1
        System.out.println(man.id); // 1
    }

    /**
     * 模拟招聘
     */
    private void recruitment(Person person) {
        person.doWork();
    }

    /**
     * 多态的好处
     */
    public void testPolymorphism2() {
        // 男人女人都能用
        recruitment(new Woman());
        recruitment(new Man());
    }

    /**
     * 多态的弊端
     */
    public void testPolymorphism3() {
        Person man = new Man();
        // man.a(); // 无法调用子类的特殊操作
    }
}
