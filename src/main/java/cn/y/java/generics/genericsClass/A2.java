package cn.y.java.generics.genericsClass;

/**
 * 继承泛型类，指定父类的泛型类型
 */
public class A2 extends A<String>{

    public A2() {
        // 在指定父类泛型类型时，泛型属性就是指定的类型
        String str = genericField;
    }
}
