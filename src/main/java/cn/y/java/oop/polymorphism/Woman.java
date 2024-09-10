package cn.y.java.oop.polymorphism;

/**
 * 女人
 */
public class Woman extends Person {
    int id = 3;
    @Override
    public void eat() {
        System.out.println("吃一碗");
    }


    @Override
    public void doWork() {
        super.doWork();
    }
}
