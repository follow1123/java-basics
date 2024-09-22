package cn.y.java.multithreading.thread_safe.deadlock;


public class DeadLockTest {

    private static void sleep(long millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        Object[] locks = new Object[]{new Object(), new Object()};

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (locks[0]){
                    sleep(100); // 模拟耗时操作
                    synchronized (locks[1]){
                        System.out.println(Thread.currentThread().getName() + "执行");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (locks[1]){
                    sleep(100); // 模拟耗时操作
                    synchronized (locks[0]){
                        System.out.println(Thread.currentThread().getName() + "执行");
                    }
                }
            }
        }).start();

    }
}
