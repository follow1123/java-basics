package org.example.multithreading.safety.syncd.block;

public class TicketWindowThread extends Thread {
    private static int tickets = 100;

    public TicketWindowThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 使用当前类的Class对象作为同步监视器
            synchronized (TicketWindowThread.class) {
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
