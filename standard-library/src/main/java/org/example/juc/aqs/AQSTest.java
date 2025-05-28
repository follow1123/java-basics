package org.example.juc.aqs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AQSTest {

    private static final Logger log = LoggerFactory.getLogger("AQSTest");

    private static final MLock lock = new MLock();
    private static int num = 100;

    public static void main(String[] args) {

        // 测试不可重入
        // testNotReentrant();

        // 测试并发修改数据
        // test();

        // testLock();

        // 测试tryLock
        testTryLock();
    }

    public static void testLock() {
        new Thread(() -> {
            lock.lock();
            log.info("get lock");
            try {
                num = num + 1;
                boolean enter = false;
                while (!enter) {
                }
                log.info("update num");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
                log.info("release lock");
            }
        }).start();

        // try{Thread.sleep(100);}catch(InterruptedException e){e.printStackTrace();}

        new Thread(() -> {
            boolean enter = false;
            while (!enter) {
            }
            lock.lock();
            log.info("get lock");
            try {
                num = num + 1;
                log.info("update num");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
                log.info("release lock");
            }
        }).start();

        new Thread(() -> {
            boolean enter = false;
            while (!enter) {
            }
            lock.lock();
            log.info("get lock");
            try {
                num = num + 1;
                log.info("update num");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
                log.info("release lock");
            }
        }).start();
    }

    public static void testTryLock() {
        new Thread(() -> {
            lock.lock();
            log.info("get lock");
            try {
                num = num + 1;
                log.info("update num");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
                log.info("release lock");
            }
        }).start();


        new Thread(() -> {
            boolean succeed = lock.tryLock();
            if (!succeed) {
                log.info("get lock failed, quit");
                return;
            }
            log.info("execute");
        }).start();

    }

    // 测试不可重入
    private static void testNotReentrant() {
        lock.lock();
        try {
            log.info("get lock");
            num = num + 1;
            log.info("update num");
            lock.lock();
            log.info("lock again");
        } finally {
            lock.unlock();
            log.info("release lock");
        }
    }

    private static void test() {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < num; i++) {
            pool.submit(() -> {
                lock.lock();
                log.info("get lock");
                try {
                    num = num - 1;
                    log.info("update num: {}", num);
                } finally {
                    lock.unlock();
                    log.info("release lock");
                }
            });
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("num: {}", num);
    }
}
