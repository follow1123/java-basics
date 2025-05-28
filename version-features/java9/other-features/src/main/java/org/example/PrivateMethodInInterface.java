package org.example;

public interface PrivateMethodInInterface {

    /**
     * 私有方法定义默认方法的内部通用逻辑
     */
    private void b() {
        System.out.println("b");
    }

    default void a() {
        System.out.println("a");
        b();
    }
}
