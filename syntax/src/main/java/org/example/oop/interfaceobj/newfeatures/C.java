package org.example.oop.interfaceobj.newfeatures;

public interface C {
    default void common1() {
        System.out.println("common1 in c");
    }
}
