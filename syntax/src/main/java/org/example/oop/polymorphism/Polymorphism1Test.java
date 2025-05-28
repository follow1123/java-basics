package org.example.oop.polymorphism;

public class Polymorphism1Test {
    public void testClassCast() {

        Person man = new Man();

        // 使用强转操作调用子类的特殊方法
        Man m = (Man) man;
        m.a();
    }

    public void testInstanceof() {
        Person man = new Man();

        // 错误的强转会报错
        Woman woman = (Woman) man;

        // 使用instanceof判断
        if (man instanceof Man) {
            Man m = (Man) man;
        }

    }
}
