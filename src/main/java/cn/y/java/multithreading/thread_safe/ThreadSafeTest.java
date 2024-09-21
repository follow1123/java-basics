package cn.y.java.multithreading.thread_safe;


public class ThreadSafeTest {

    /**
     * 测试出现重复卖同一张票的问题
     */
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            private int tickets = 100;

            @Override
            public void run() {
                while(true){
                    if (tickets > 0){
                        System.out.println(Thread.currentThread().getName() + "售出一张票，剩余：" + tickets);
                        tickets--;
                    }else {
                        break;
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

