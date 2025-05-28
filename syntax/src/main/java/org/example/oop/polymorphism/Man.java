package org.example.oop.polymorphism;

/**
 * 男人
 */
public class Man extends Person {
    int id = 2;

    @Override
    public void eat() {
        System.out.println("吃一大碗");
    }

    @Override
    public void doWork() {
        super.doWork();
    }

    /**
     * 模拟子类特殊操作
     */
    public void a() {
        System.out.println("a");
    }
}
