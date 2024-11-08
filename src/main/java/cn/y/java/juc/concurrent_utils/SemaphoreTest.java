package cn.y.java.juc.concurrent_utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

@Slf4j(topic = "SemaphoreTest")
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 50; i++) {
            int idx = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    log.info("acquire resource {}", idx);
                    try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
