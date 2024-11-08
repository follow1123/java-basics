package cn.y.java.juc.thread_pool;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j(topic = "TimerTest")
public class TimerTest {

    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask t1 = new TimerTask() {
            @Override
            public void run() {
                // int i = 1/0;
                // 由于任务使用同一个线程执行，一个任务执行耗时会影响后面的任务执行时机
                try{Thread.sleep(2000);}catch(InterruptedException e){e.printStackTrace();}
                log.info("execute t1");
            }
        };
        TimerTask t2 = new TimerTask() {
            @Override
            public void run() {
                log.info("execute t2");
            }
        };

        timer.schedule(t1, 1000);
        timer.schedule(t2, 1000);
    }
}
