package cn.y.java.multithreading.thread_safe.deadlock;


public class DeadLockTest {

    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1){
                    // 模拟耗时操作
                    try{Thread.sleep(100);}catch(InterruptedException e){e.printStackTrace();}
                    synchronized (lock2){
                        System.out.println(Thread.currentThread().getName() + "执行");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock2){
                    // 模拟耗时操作
                    try{Thread.sleep(100);}catch(InterruptedException e){e.printStackTrace();}
                    synchronized (lock1){
                        System.out.println(Thread.currentThread().getName() + "执行");
                    }
                }
            }
        }).start();
    }
}
