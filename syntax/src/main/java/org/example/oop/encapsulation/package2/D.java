package org.example.oop.encapsulation.package2;

import org.example.oop.encapsulation.A;

/**
 * 和A类处在不同的包下，不是A类的子类
 */
public class D {
    public static void main(String[] args) {
        A a = new A();
        // int privateField a.privateField; // 无法访问
        // int defaultField = a.defaultField; // 不在相同包内，无法访问
        // int protectedField = a.protectedField; // 不在相同包内并且不是子类，无法访问
        int publicField = a.publicField;
    }
}
