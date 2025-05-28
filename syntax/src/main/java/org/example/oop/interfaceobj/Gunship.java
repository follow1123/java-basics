package org.example.oop.interfaceobj;

/**
 * 武装直升机
 */
public class Gunship implements Hovering, Attackable {
    @Override
    public void hover() {
        System.out.println("悬停");

    }

    @Override
    public void attack() {
        System.out.println("攻击");
    }

    @Override
    public void fly() {
        System.out.println("飞");
    }
}
