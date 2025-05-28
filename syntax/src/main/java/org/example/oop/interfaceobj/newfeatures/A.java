package org.example.oop.interfaceobj.newfeatures;

public interface A {
    public static void a1() {
        System.out.println("a1");
    }

    default void a2() {
        System.out.println("a2");
    }

    default void common1() {
        System.out.println("common1 in a");
    }

    default void common2() {
        System.out.println("common2 in a");
    }

    private void privateMethod() {
        System.out.println("private method");
    }
}
