package cn.y.java.juc.atomic.reference;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {
    public static void main(String[] args) {
        final AtomicReference<BigInteger> bi = new AtomicReference<>(BigInteger.valueOf(1000));
        int count = bi.get().intValue();
        Thread[] threads = new Thread[count];
        for (int i = 0; i <count; i++) {
            threads[i] = new Thread(() -> {
                bi.getAndUpdate(v -> v.subtract(BigInteger.ONE));
            });
        }

        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) {
            try {thread.join();} catch (InterruptedException e) {throw new RuntimeException(e);}
        }

        System.out.println(bi.get());
    }
}
