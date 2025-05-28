package org.example.juc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
    private static final Logger log = LoggerFactory.getLogger("CyclicBarrierTest");

    public static void main(String[] args) {
        int count = 5;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count, () -> log.info("发车"));
        ExecutorService pool = Executors.newFixedThreadPool(count);

        int person = 20;
        for (int i = 0; i < person; i++) {
            int personNo = i;
            pool.submit(() -> {
                log.info("person {} 上车", personNo);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                log.info("person {} 睡觉", personNo);
            });
        }
    }
}
