package org.example.oop.method;

public class RecursionTest {
    /**
     * 直接递归
     */
    public void a() {
        a();
    }

    /**
     * 间距递归
     */
    public void b() {
        c();
    }

    public void c() {
        d();

    }

    public void d() {
        b();
    }

    /**
     * 累加数字
     */
    public int getSum(int num) {
        if (num == 1) {
            return 1;
        }
        return num + getSum(num - 1);
    }


    public void testSum() {

        System.out.println(getSum(100));
    }
}
