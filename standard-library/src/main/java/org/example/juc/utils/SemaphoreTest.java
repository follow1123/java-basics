package org.example.juc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    private static final Logger log = LoggerFactory.getLogger("SemaphoreTest");

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 50; i++) {
            int idx = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    log.info("acquire resource {}", idx);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
