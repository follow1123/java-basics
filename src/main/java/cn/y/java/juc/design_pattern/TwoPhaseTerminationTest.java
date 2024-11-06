package cn.y.java.juc.design_pattern;

import lombok.extern.slf4j.Slf4j;

/**
 * 两阶段终止模式测试
 * 一个线程如何安全，优雅的终止另一个线程
 */
@Slf4j(topic = "TwoPhaseTerminationTest")
public class TwoPhaseTerminationTest {

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

        try{Thread.sleep(2200);}catch(InterruptedException e){e.printStackTrace();}
        log.info("interrupt");
        // 执行打断，停止另一个线程
        thread.interrupt();
    }
}
