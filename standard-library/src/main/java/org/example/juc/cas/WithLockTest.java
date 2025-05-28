package org.example.juc.cas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WithLockTest {
    private static final Logger log = LoggerFactory.getLogger("WithLockTest");

    private int num = 1000;

    public int getNum() {
        return num;
    }

    public void subNum() {
        // log.info("before: {}", num);
        synchronized (this) {
            num = num - 1;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) test();
    }

    private static void test() {
        WithLockTest withLockTest = new WithLockTest();
        Thread[] threads = new Thread[1000];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(withLockTest::subNum);
        }
        long start = System.currentTimeMillis();
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        log.info("result: {}, time: {}ms", withLockTest.getNum(), System.currentTimeMillis() - start);
    }
}
