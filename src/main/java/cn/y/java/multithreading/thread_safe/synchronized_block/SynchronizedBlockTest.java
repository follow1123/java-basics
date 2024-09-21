package cn.y.java.multithreading.thread_safe.synchronized_block;

public class SynchronizedBlockTest {

    public static void main(String[] args) {
        // testSynchronizedBlockWhitRunnable();
        testSynchronizedBlockWhitThread();
    }
    /**
     * 用实现Runnable接口的方式实现synchronized同步代码块解决重复卖票问题
     */
    public static void testSynchronizedBlockWhitRunnable() {
        System.out.println("runnable接口方式");
        Runnable runnable = new Runnable() {
            private int tickets = 1000;

            private Object lock = new Object();

            @Override
            public void run() {
                while(true){
                    // 这里可以使用this当同步监视器
                    synchronized (lock){
                        if (tickets > 0){
                            System.out.println(Thread.currentThread().getName() + "售出一张票，剩余：" + tickets);
                            tickets--;
                        }else {
                            break;
                        }
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
    /**
     * 用继承Thread类的方式实现synchronized同步代码块解决重复卖票问题
     */
    public static void testSynchronizedBlockWhitThread() {

        System.out.println("继承Thread类方式");
        TicketWindow w1 = new TicketWindow();
        TicketWindow w2 = new TicketWindow();
        TicketWindow w3 = new TicketWindow();
        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");

        w1.start();
        w2.start();
        w3.start();
    }
}
