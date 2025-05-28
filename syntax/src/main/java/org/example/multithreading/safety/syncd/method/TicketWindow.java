package org.example.multithreading.safety.syncd.method;

public class TicketWindow extends Thread {
    private static int tickets = 100;

    public static synchronized void sale() {
        if (tickets > 0) {
            System.out.println(Thread.currentThread().getName() + "售出一张票，剩余：" + tickets);
            tickets--;
        }
    }

    @Override
    public void run() {
        while (tickets > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sale();
        }
    }
}
