package org.example.generics.classes;

/**
 * 继承泛型类时不指定父类泛型的类型
 */
public class A1 extends A {
    public A1() {
        // 在不指定泛型类型时，默认的泛型属性的类型是Object
        Object o = genericField;
    }
}
