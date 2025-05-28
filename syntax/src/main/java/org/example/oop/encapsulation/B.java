package org.example.oop.encapsulation;

/**
 * 和A类处在相同的包下
 */
public class B {
    public static void main(String[] args) {
        A a = new A();
        // int privateField a.privateField; // 无法访问
        int defaultField = a.defaultField;
        int protectedField = a.protectedField;
        int publicField = a.publicField;
    }
}
