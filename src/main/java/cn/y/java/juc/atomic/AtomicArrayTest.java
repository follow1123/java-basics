package cn.y.java.juc.atomic;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicArrayTest {

    public static void main(String[] args) {
        testNormalArray();
        testAtomicArray();
    }

    public static void testAtomicArray(){
        int len = 10;
        int max = 10000;
        AtomicIntegerArray ints = new AtomicIntegerArray(len);
        Thread[] threads = new Thread[len];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < max; j++) {
                    ints.incrementAndGet(j % len);
                }
            });
        }
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) {
            try {thread.join();} catch (InterruptedException e) {throw new RuntimeException(e);}
        }
        System.out.println(ints);
    }

    public static void testNormalArray() {
        int len = 10;
        int max = 10000;
        int[] ints = new int[len];
        Thread[] threads = new Thread[len];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < max; j++) {
                    ints[j % len]++;
                }
            });
        }
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) {
            try {thread.join();} catch (InterruptedException e) {throw new RuntimeException(e);}
        }
        System.out.println(Arrays.toString(ints));
    }

}
