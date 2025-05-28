package org.example.juc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    private static final Logger log = LoggerFactory.getLogger("LockSupportTest");

    public static void main(String[] args) {

        // 基础使用
        // test();

        // 先执行unpark再执行park
        // testParkAfterUnPark();

        // 交替打印
        testPrint();
    }


    private static int num = 0;

    private static void testPrint() {
        Thread[] threads = new Thread[2];
        threads[0] = new Thread(() -> {
            while (true) {
                if (num % 2 == 0) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("num: {}", num++);
                    LockSupport.unpark(threads[1]);
                } else {
                    LockSupport.park();
                }
            }
        });

        threads[1] = new Thread(() -> {
            while (true) {
                if (num % 2 != 0) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("num: {}", num++);
                    LockSupport.unpark(threads[0]);
                } else {
                    LockSupport.park();
                }
            }
        });
        threads[0].start();
        threads[1].start();
    }

    private static void testParkAfterUnPark() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("enter");
            LockSupport.park();
            // 发放的凭证只能使用一次
            // LockSupport.park();
            log.info("wake");
        });

        thread.start();

        new Thread(() -> {
            log.info("start");
            LockSupport.unpark(thread);
            // 凭证只有一个，无法多次发放
            // LockSupport.unpark(thread);
            log.info("done");
        }).start();
    }

    private static void test() {
        Thread thread = new Thread(() -> {
            log.info("enter");
            LockSupport.park();
            log.info("wake");
        });

        thread.start();

        new Thread(() -> {
            log.info("start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.unpark(thread);
            log.info("done");
        }).start();
    }
}
