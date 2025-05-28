package org.example.juc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {
    private static final Logger log = LoggerFactory.getLogger("CountDownLatchTest");

    public static void main(String[] args) {
        // 测试使用
        // test();

        // 所有提交的任务完成后关闭线程池
        // testShutdownAfterAllTaskDone();
    }

    private static void testShutdownAfterAllTaskDone() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        int count = 10;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            pool.submit(() -> {
                log.info("run task");
                countDownLatch.countDown();
                log.info("task done");
            });
        }

        // 等待所有任务执行完成后关闭线程池
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        pool.shutdown();
    }

    private static void test() {
        int count = 10;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(random.nextInt(0, 10) * 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("execute");
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("other thing");
    }
}
