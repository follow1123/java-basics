package cn.y.java.juc.atomic;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    public static void main(String[] args) {
        // testAtomicInteger();
        testAtomicBool();
    }

    public static void testAtomicInteger(){
        AtomicInteger i = new AtomicInteger();
        System.out.println(i.get()); // 0
        i.set(2);
        System.out.println(i.getAndSet(4)); // 2
        int prev = i.get();
        int next = prev - 1;
        System.out.println(i.compareAndSet(prev, next)); // true
        prev = i.get();
        next = prev + 1;
        System.out.println(i.compareAndExchange(prev, next)); // 3
        System.out.println(i.getAndIncrement()); // 4
        System.out.println(i.incrementAndGet()); // 6
        System.out.println(i.getAndDecrement()); // 6
        System.out.println(i.decrementAndGet()); // 4
        System.out.println(i.addAndGet(6)); // 10
        System.out.println(i.getAndAdd(-6)); // 10
        System.out.println(i.getAndUpdate(v -> v * 10)); // 4
        System.out.println(i.updateAndGet(v -> v * 10)); // 400
    }

    public static void testAtomicBool(){
        AtomicBoolean b = new AtomicBoolean();
        System.out.println(b.get()); // false
        b.set(true);
        System.out.println(b.getAndSet(false)); // true
        boolean prev = b.get();
        boolean next = !prev;
        System.out.println(b.compareAndSet(prev, next)); // true
        prev = b.get();
        next = !prev;
        System.out.println(b.compareAndExchange(prev, next)); // true
    }

}
