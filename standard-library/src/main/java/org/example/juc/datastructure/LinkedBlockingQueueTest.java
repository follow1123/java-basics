package org.example.juc.datastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueTest {
    private static final Logger log = LoggerFactory.getLogger("LinkedBlockingQueueTest");

    public static void main(String[] args) {
        // 测试阻塞方法
        // testBlockingMethod();

        // 测试非阻塞方法
        testNonBlockingMethod();
    }

    private static void testNonBlockingMethod() {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(1);

        pool.submit(() -> {
            log.info("start take");
            Integer val = queue.poll();
            // 也可以指定等待的时间
            // queue.poll(1, TimeUnit.SECONDS)
            log.info("take value: {}", val);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.submit(() -> {
            log.info("start put");
            queue.offer(1);
            // 也可以指定等待的时间
            // queue.offer(1, 1, TimeUnit.SECONDS);
            log.info("put value: {}", 1);
        });
    }

    private static void testBlockingMethod() {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(1);

        pool.submit(() -> {
            try {
                log.info("start take");
                Integer val = queue.take();
                log.info("take value: {}", val);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.submit(() -> {
            try {
                log.info("start put");
                queue.put(1);
                log.info("put value: {}", 1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
