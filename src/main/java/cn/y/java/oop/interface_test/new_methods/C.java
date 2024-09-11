package cn.y.java.oop.interface_test.new_methods;

public interface C {
    default void common1(){
        System.out.println("common1 in c");
    }
}
