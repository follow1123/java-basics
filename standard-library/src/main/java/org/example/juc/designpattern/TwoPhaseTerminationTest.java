package org.example.juc.designpattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 两阶段终止模式测试
 * 一个线程如何安全，优雅的终止另一个线程
 */
public class TwoPhaseTerminationTest {
    private static final Logger log = LoggerFactory.getLogger("TwoPhaseTerminationTest");

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                Thread cur = Thread.currentThread();
                if (cur.isInterrupted()) {
                    // 清理工作
                    log.info("clean up");
                    break;
                }
                try {
                    // 睡眠后执行任务
                    Thread.sleep(500);
                    log.info("execute");
                } catch (InterruptedException e) {
                    log.info("re interrupt");
                    // 重新设置打断标记
                    cur.interrupt();
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        try {
            Thread.sleep(2200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("interrupt");
        // 执行打断，停止另一个线程
        thread.interrupt();
    }
}
