package cn.y.java.oop.interface_test;

/**
 * 飞机
 */
public class Plane implements Flyable, Attackable{
    @Override
    public void attack() {
        System.out.println("飞机攻击");
    }

    @Override
    public void fly() {
        System.out.println("飞机起飞");
    }
}
