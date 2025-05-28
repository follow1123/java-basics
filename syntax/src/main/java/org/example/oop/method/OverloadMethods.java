package org.example.oop.method;

/**
 * 方法重载
 */
public class OverloadMethods {
    /**
     * 无返回值
     */
    public void add(int a, int b) {
        System.out.println(a + b);
    }

    /**
     * 虽然返回值不同，但形参相同，编译不通过
     */
    // public int add(int a, int b){
    //     return a + b;
    // }

    /**
     * 有返回值
     */
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    /**
     * 类型不同
     */
    public double add(double a, double b) {
        return a + b;
    }

    /**
     * 修饰符不同
     */
    private double add(double a, double b, double c) {
        return a + b + c;
    }
}
