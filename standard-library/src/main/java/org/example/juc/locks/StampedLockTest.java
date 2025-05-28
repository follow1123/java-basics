package org.example.juc.locks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {
    private static final Logger log = LoggerFactory.getLogger("StampedLockTest");

    private static final StampedLock lock = new StampedLock();

    private static int num = 50;

    public static void main(String[] args) {
        int count = 100;
        for (int i = 0; i < count; i++) {
            boolean flag = i % 2 == 0;
            new Thread(() -> {
                if (flag) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    long stamp = lock.writeLock();
                    log.info("write stamp: {}", stamp);
                    try {
                        // try{Thread.sleep(300);}catch(InterruptedException e){e.printStackTrace();}
                        num = num - 1;
                        log.info("update num");
                    } finally {
                        lock.unlockWrite(stamp);
                    }
                } else {
                    long stamp = lock.tryOptimisticRead();
                    log.info("optimistic read stamp: {}", stamp);
                    if (lock.validate(stamp)) {
                        // try{Thread.sleep(100);}catch(InterruptedException e){e.printStackTrace();}
                        log.info("first time num: {}", num);
                        return;
                    }
                    log.info("update to read lock");
                    stamp = lock.readLock();
                    try {
                        // try{Thread.sleep(100);}catch(InterruptedException e){e.printStackTrace();}
                        log.info("num: {}", num);
                    } finally {
                        lock.unlockRead(stamp);
                    }
                }
            }).start();
        }

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("num: {}", num);
    }
}
