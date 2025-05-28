package org.example.juc.threadapi;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaemonTest {
    private static final Logger log = LoggerFactory.getLogger("DaemonTest");

    public static void main(String[] args) {
        /*
            测试普通线程
            主线程执行完成后继续执行
         */
        // testNoDaemonThread();

        /*
            测试守护线程
            主线程执行完成后直接退出
         */
        testDaemonThread();
    }

    public static void testDaemonThread() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("execute");
            }
        });

        thread.setDaemon(true);
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("done");
    }

    public static void testNoDaemonThread() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("execute");
            }
        });

        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("done");
    }
}
