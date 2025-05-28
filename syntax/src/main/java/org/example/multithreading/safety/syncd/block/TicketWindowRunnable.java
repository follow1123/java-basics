package org.example.multithreading.safety.syncd.block;

public class TicketWindowRunnable implements Runnable {

    private int tickets = 1000;
    private final Object lock = new Object();

    @Override
    public void run() {
        while (true) {
            // 这里可以使用this当同步监视器
            synchronized (lock) {
                if (tickets > 0) {
                    System.out.println(Thread.currentThread().getName() + "售出一张票，剩余：" + tickets);
                    tickets--;
                } else {
                    break;
                }
            }
        }
    }
}
