package cn.y.java.juc.thread_api;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j(topic = "InterruptTest")
public class InterruptTest {

    public static void main(String[] args) {
        // 测试阻塞的线程被打断时的打断标记
        // testInterruptBlockedThread();

        // 测试正在运行的线程被打断时的打断标记
        // testInterruptRunningThread();

        // 测试打断park等待的线程
        testInterruptPark();
    }

    public static void testInterruptBlockedThread() {
        Thread thread = new Thread(() -> {
            log.info("sleep");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                log.info("interrupt after exception: {}", Thread.currentThread().isInterrupted());
                e.printStackTrace();
            }
            log.info("done");
        });
        thread.start();

        try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
        log.info("interrupt");
        thread.interrupt();
        log.info("isInterrupted: {}", thread.isInterrupted());
    }

    public static void testInterruptRunningThread() {
        Thread thread = new Thread(() -> {
            while (true) {
                log.info("running");
                if (Thread.currentThread().isInterrupted()){
                    log.info("break");
                    break;
                }
            }
        });
        thread.start();

        try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
        log.info("interrupt");
        thread.interrupt();
        log.info("isInterrupted: {}", thread.isInterrupted());
    }

    public static void testInterruptPark(){
        Thread thread = new Thread(() -> {
            log.info("step 1");
            LockSupport.park();
            // System.out.println(Thread.currentThread().isInterrupted());
            System.out.println(Thread.interrupted());
            log.info("step 2");

            /*
                只是线程不会阻塞，因为park方法判断如果打断标记为true时就不会执行
                上面可以使用Thread.interrupted()输出后清除打断标记
             */
            LockSupport.park();
            log.info("step 3");
        });
        thread.start();

        try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
        log.info("interrupt");
        thread.interrupt();
    }
}
