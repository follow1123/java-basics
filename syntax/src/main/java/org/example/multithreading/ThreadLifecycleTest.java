package org.example.multithreading;

/**
 * 线程生命周期测试，JDK1.5及之后
 */
public class ThreadLifecycleTest {

    /**
     * 测试线程创建，运行和终止
     */
    public void testNewRunnableTerminated() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getState()); // RUNNABLE
            }
        });
        System.out.println(thread.getState()); // NEW
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(thread.getState()); // TERMINATED
    }

    /**
     * 测试竞争锁等待
     */
    public void testBlocked() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    System.out.println(Thread.currentThread().getName() + " get lock");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 以下有一个线程的状态是BLOCKED
        System.out.println(thread1.getState());
        System.out.println(thread2.getState());
    }

    /**
     * 测试有限期等待
     */
    public void testTimedWaiting() {
        // 使用sleep方式
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    try {
                        wait(800);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread1.getState()); // TIMED_WAITING
        System.out.println(thread2.getState()); // TIMED_WAITING
        System.out.println(thread3.getState()); // TIMED_WAITING
    }

    /**
     * 测试无限期等待
     */
    public void testWaiting() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        // 使用wait方式
        Thread thread1 = new Thread(runnable);
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
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread1.getState()); // WAITING
        System.out.println(thread2.getState()); // WAITING
    }
}
