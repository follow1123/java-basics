package org.example.oop.object.finalize;

public class FinalizeTest {
    /**
     * 测试finalize方法，（jdk9被废弃了，不推荐使用）
     */
    public void testFinalize() {
        Person zs = new Person("zs");
        zs = null; // 将引用指向为null表示这个对象是垃圾了，等待被回收，但时间不确定

        System.gc(); // 强制释放空间
    }
}
