package cn.y.java.generics.genericsClass;

/**
 * 指定父类的泛型类型，但自己也有泛型参数
 */
public class A4<T> extends A<Integer>{

    // 自己的泛型属性
    T subGenericField;
}
