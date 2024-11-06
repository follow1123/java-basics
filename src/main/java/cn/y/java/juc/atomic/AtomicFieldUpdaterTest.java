package cn.y.java.juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicFieldUpdaterTest {
    public static void main(String[] args) {
        A zs = new A("zs", 18);
        // int属性
        AtomicIntegerFieldUpdater<A> age = AtomicIntegerFieldUpdater.newUpdater(A.class, "age");
        System.out.println(age.get(zs)); // 18
        System.out.println(age.incrementAndGet(zs)); // 19

        // 引用属性
        AtomicReferenceFieldUpdater<A, String> name = AtomicReferenceFieldUpdater.newUpdater(A.class, String.class, "name");
        System.out.println(name.get(zs)); // zs
        System.out.println(name.updateAndGet(zs, n -> "ls")); // ls
    }

    private static class A{
        private volatile String name;
        private volatile int age;

        public A(String name, int age) {this.name = name;this.age = age;}
    }
}
