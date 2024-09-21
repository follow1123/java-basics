package cn.y.java.multithreading.thread_safe.synchronized_block;

public class TicketWindow extends Thread{

    private static int tickets = 100;

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // 使用当前类的Class对象作为同步监视器
            synchronized (TicketWindow.class){
                if (tickets > 0){
                    System.out.println(Thread.currentThread().getName() + "售出一张票，剩余：" + tickets);
                    tickets--;
                }else {
                    break;
                }
            }
        }
    }
}
