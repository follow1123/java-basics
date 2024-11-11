package cn.y.java.multithreading.thread_safe.synchronized_block;

public class SynchronizedBlockTest {

    public static void main(String[] args) {
        testSynchronizedBlockWithRunnable();
        // testSynchronizedBlockWithThread();
    }
    /**
     * 用实现Runnable接口的方式实现synchronized同步代码块解决重复卖票问题
     */
    public static void testSynchronizedBlockWithRunnable() {
        TicketWindowRunnable twr = new TicketWindowRunnable();

        Thread window1 = new Thread(twr, "窗口1");
        Thread window2 = new Thread(twr, "窗口2");
        Thread window3 = new Thread(twr, "窗口3");

        window1.start();
        window2.start();
        window3.start();
    }
    /**
     * 用继承Thread类的方式实现synchronized同步代码块解决重复卖票问题
     */
    public static void testSynchronizedBlockWithThread() {
        System.out.println("继承Thread类方式");
        TicketWindowThread w1 = new TicketWindowThread("窗口1");
        TicketWindowThread w2 = new TicketWindowThread("窗口2");
        TicketWindowThread w3 = new TicketWindowThread("窗口3");

        w1.start();
        w2.start();
        w3.start();
    }
}
