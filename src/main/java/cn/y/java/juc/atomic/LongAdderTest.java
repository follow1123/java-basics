package cn.y.java.juc.atomic;

import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderTest {

    public static void main(String[] args) {
        testLongAdder();
        testDoubleAdder();
    }

    private static void testDoubleAdder() {
        DoubleAdder doubleAdder = new DoubleAdder();
        int len = 10;
        Thread[] threads = new Thread[len];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < len * 10; j++) doubleAdder.add(1.1);
            });
        }
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) {
            try {thread.join();} catch (InterruptedException e) {throw new RuntimeException(e);}
        }
        System.out.println(doubleAdder.doubleValue());
    }


    private static void testLongAdder() {
        LongAdder longAdder = new LongAdder();
        int len = 10;
        Thread[] threads = new Thread[len];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < len * 10; j++) longAdder.increment();
            });
        }

        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) {
            try {thread.join();} catch (InterruptedException e) {throw new RuntimeException(e);}
        }
        System.out.println(longAdder.intValue());
    }
}
