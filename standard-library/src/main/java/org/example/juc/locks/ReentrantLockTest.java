package org.example.juc.locks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    private static final Logger log = LoggerFactory.getLogger("ReentrantLock");

    private static final ReentrantLock lock = new ReentrantLock();
    private static final ReentrantLock fairLock = new ReentrantLock(true);

    public static void main(String[] args) {
        // 测试可重入
        // testReentrant();

        // 测试可打断
        // testInterrupt();

        // 测试超时打断
        // testTimeout();

        // 测试公平、非公平锁
        // testUnfair();
        // testFair();

        // 测试多条件变量
        testMultiCondition();
    }

    public static void testMultiCondition() {
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();

        for (int i = 0; i < 10; i++) {
            boolean flag = i % 2 == 0;
            Thread thread = new Thread(() -> {
                lock.lock();
                log.info("enter");
                try {
                    if (flag) {
                        try {
                            c1.await();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        try {
                            c2.await();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    log.info("done");
                } finally {
                    lock.unlock();
                }
            });
            thread.setName(String.format("thread %d %s", i, flag ? "c1" : "c2"));
            thread.start();
        }

        // 两秒后唤醒第一批线程
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("notify c2");
        lock.lock();
        c2.signalAll();
        lock.unlock();

        // 4秒后唤醒第二批线程
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("notify c1");
        lock.lock();
        c1.signalAll();
        lock.unlock();
    }

    public static void testFair() {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                log.info("enter");
                fairLock.lock();
                try {
                    if ("thread 0".equals(Thread.currentThread().getName())) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    log.info("execute");
                } finally {
                    fairLock.unlock();
                }
            });

            thread.setName("thread " + i);
            thread.start();
            if (i == 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void testUnfair() {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                log.info("enter");
                lock.lock();
                try {
                    if ("thread 0".equals(Thread.currentThread().getName())) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    log.info("execute");
                } finally {
                    lock.unlock();
                }
            });

            thread.setName("thread " + i);
            thread.start();
            if (i == 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void testTimeout() {
        Thread thread = new Thread(() -> {
            log.info("enter");
            try {
                // 立即获取结果
                // boolean succeed = lock.tryLock();
                // 等待1秒，并获取最终的结果
                boolean succeed = lock.tryLock(1, TimeUnit.SECONDS);
                if (!succeed) {
                    System.out.println("get lock failed");
                    return;
                }
                log.info("get lock");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                log.info("execute");
            } finally {
                log.info("release lock");
                lock.unlock();
            }
        });

        // 主线程先获取锁
        log.info("get lock");
        lock.lock();
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testInterrupt() {
        Thread thread = new Thread(() -> {
            log.info("enter");
            try {
                lock.lockInterruptibly();
                log.info("get lock");
            } catch (InterruptedException e) {
                log.info("interrupted");
                e.printStackTrace();
                return;
            }
            try {
                log.info("execute");
            } finally {
                log.info("release lock");
                lock.unlock();
            }
        });

        // 主线程先获取锁
        log.info("get lock");
        lock.lock();
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("interrupt");
        thread.interrupt();
    }

    private static void execute(int i) {
        // 获取锁
        lock.lock();
        try {
            if (i == 0) return;
            execute(i - 1);
            // 代码
            System.out.println(Thread.currentThread());
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    public static void testReentrant() {
        execute(5);
    }
}
