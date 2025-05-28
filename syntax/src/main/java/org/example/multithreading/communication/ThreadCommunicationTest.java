package org.example.multithreading.communication;

public class ThreadCommunicationTest {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            private int number = 1;

            @Override
            public void run() {
                while (true) {
                    synchronized (this) {
                        notify(); //  唤醒等待中的线程
                        if (number <= 100) {
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println(Thread.currentThread().getName() + "---" + number);
                            number++;
                            try {
                                wait(); // 释放同步监视器（锁）并进入WAITING状态
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        };

        Thread thread1 = new Thread(runnable, "线程1");
        Thread thread2 = new Thread(runnable, "线程2");
        thread1.start();
        thread2.start();
    }
}
