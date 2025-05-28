package org.example.multithreading.safety;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Runnable runnable = new Runnable() {
            private int tickets = 100;

            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();
                        if (tickets > 0) {
                            System.out.println(Thread.currentThread().getName() + "售出一张票，剩余：" + tickets);
                            tickets--;
                        } else {
                            break;
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            }
        };

        Thread window1 = new Thread(runnable, "窗口1");
        Thread window2 = new Thread(runnable, "窗口2");
        Thread window3 = new Thread(runnable, "窗口3");

        window1.start();
        window2.start();
        window3.start();
    }
}
