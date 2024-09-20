package cn.y.java.multithreading.thread_lifecycle;

import org.junit.jupiter.api.Test;

/**
 * 线程生命周期测试，JDK1.5及之后
 */
public class ThreadLifecycleTest {

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 测试线程创建，运行和终止
     */
    @Test
    public void testNewRunnableTerminated() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getState()); // RUNNABLE
            }
        });
        System.out.println(thread.getState()); // NEW
        thread.start();

        sleep(1000);

        System.out.println(thread.getState()); // TERMINATED
    }

    /**
     * 测试竞争锁等待
     */
    @Test
    public void testBlocked() {
        Object lock = new Object();
        Thread[] threads = new Thread[2];
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + " get lock");
                    // 获取锁后打印所有线程的状态
                    System.out.println(threads[0].getName() + "---" + threads[0].getState());
                    System.out.println(threads[1].getName() + "---" + threads[1].getState());
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        threads[0] = thread1;
        threads[1] = thread2;

        thread1.start();
        thread2.start();

        sleep(1000);
    }

    /**
     * 测试有限期等待
     */
    @Test
    public void testTimedWaiting() {
        // 使用sleep方式
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                sleep(1000);
            }
        });
        // 使用join方式
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread1.join(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        // 使用wait方式
        Object lock = new Object();
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    try {
                        lock.wait(800);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        sleep(200);
        System.out.println(thread1.getState()); // TIMED_WAITING
        System.out.println(thread2.getState()); // TIMED_WAITING
        System.out.println(thread3.getState()); // TIMED_WAITING
    }

    /**
     * 测试无限期等待
     */
    @Test
    public void testWaiting() {
        // 使用wait方式
        Object lock = new Object();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        // 使用join方式
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread1.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread1.start();
        thread2.start();

        sleep(200);
        System.out.println(thread1.getState()); // WAITING
        System.out.println(thread2.getState()); // WAITING

        // 打印后唤醒thread2
        synchronized (lock){
            lock.notify();
        }

    }
}
