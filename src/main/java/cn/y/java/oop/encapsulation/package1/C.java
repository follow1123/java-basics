package cn.y.java.oop.encapsulation.package1;

import cn.y.java.oop.encapsulation.A;

/**
 * 和A类处在不同的包下，但是是A类的子类
 */
public class C extends A {
    public void cMethod(){
        // int privateField super.privateField; // 无法访问
        // int defaultField = super.defaultField; // 不在相同包内，无法访问
        int protectedField = super.protectedField;
        int publicField = super.publicField;
    }
}
