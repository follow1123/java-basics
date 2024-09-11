package cn.y.java.oop.interface_test.new_methods;

public class B extends D implements A, C{

    public void b1(){
        System.out.println("b3");
    }

    @Override
    public void common1() {
        System.out.println("common1 in b");
    }


    public void b2(){
        common1(); // 调用自己的方法

        super.common2(); // 调用父类的同名同参方法

        common1(); // 调用自己类中的同名同参方法
        A.super.common1(); // 调用接口A的同名同参方法
        C.super.common1(); // 调用接口C的同名同参方法
    }

}
