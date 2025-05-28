package org.example.juc.locks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {
    private static final Logger log = LoggerFactory.getLogger("ReentrantReadWriteLockTest");

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private static int num = 5;

    public static void main(String[] args) {
        // 测试基础使用
        // test();

        // 测试读锁升级为写锁
        // testUpgrade();

        // 测试写锁降级为读锁
        // testDowngrade();
    }

    private static void testDowngrade() {
        new Thread(() -> {
            lock.writeLock().lock();
            try {
                num = num - 1;
                log.info("update num");
                lock.readLock().lock();
            } finally {
                lock.writeLock().unlock();
            }
            try {
                log.info("get readlock");
            } finally {
                lock.readLock().unlock();
            }
        }).start();
    }

    private static void testUpgrade() {
        new Thread(() -> {
            lock.readLock().lock();
            try {
                log.info("num: {}", num);
                lock.writeLock().lock();
            } finally {
                lock.readLock().unlock();
            }
            try {
                log.info("get write lock");
            } finally {
                lock.writeLock().unlock();
            }
        }).start();
    }

    private static void test() {
        int count = 10;
        for (int i = 0; i < count; i++) {
            boolean flag = i % 2 == 0;
            new Thread(() -> {
                if (flag) {
                    lock.readLock().lock();
                    try {
                        log.info("num: {}", num);
                    } finally {
                        lock.readLock().unlock();
                    }
                } else {
                    lock.writeLock().lock();
                    try {
                        num = num - 1;
                        log.info("update num");
                    } finally {
                        lock.writeLock().unlock();
                    }
                }
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("num: {}", num);
    }
}
