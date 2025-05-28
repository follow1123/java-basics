package org.example.juc.pool.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class MThreadPoolTest {
    private static final Logger log = LoggerFactory.getLogger("MThreadPoolTest");

    public static void main(String[] args) {
        // 测试使用线程池
        // testUse();

        // 测试默认拒绝策略，也就是抛出异常
        // testDefaultStrategy();

        // 测试丢弃任务策略
        // testDropStrategy();

        // 测试等待策略
        // testWaitStrategy();

        // 测试自己运行策略
        testRunItYourselfStrategy();
    }

    private static void testRunItYourselfStrategy() {
        MThreadPool pool = new MThreadPool(2, 3, TimeUnit.SECONDS, 2,
                new RejectStrategy.RunItYourselfStrategy());
        for (int i = 0; i < 8; i++) {
            int num = i;
            pool.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("execute {}", num);
            });
        }
    }

    private static void testWaitStrategy() {
        MThreadPool pool = new MThreadPool(2, 3, TimeUnit.SECONDS, 2,
                new RejectStrategy.WaitStrategy(1, TimeUnit.SECONDS));
        for (int i = 0; i < 8; i++) {
            int num = i;
            pool.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("execute {}", num);
            });
        }
    }

    private static void testDropStrategy() {
        MThreadPool pool = new MThreadPool(2, 3, TimeUnit.SECONDS, 2,
                new RejectStrategy.DropStrategy());
        for (int i = 0; i < 8; i++) {
            int num = i;
            pool.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("execute {}", num);
            });
        }
    }

    private static void testDefaultStrategy() {
        MThreadPool pool = new MThreadPool(2, 3, TimeUnit.SECONDS, 2);
        for (int i = 0; i < 8; i++) {
            int num = i;
            pool.submit(() -> {
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("execute {}", num);
            });
        }
    }

    private static void testUse() {
        MThreadPool pool = new MThreadPool(2, 3, TimeUnit.SECONDS, 2);
        for (int i = 0; i < 8; i++) {
            int num = i;
            pool.submit(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("execute {}", num);
            });
        }
    }
}
