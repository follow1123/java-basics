package cn.y.java.multithreading.thread_methods;

import org.junit.jupiter.api.Test;

public class ThreadMethodsTest {

    @Test
    public void testCurrentThread() {
        // 打印线程名
        System.out.println(Thread.currentThread().getName());

        // 修改线程名
        Thread.currentThread().setName("主线程");

        try {
            // 当前线程休眠1秒
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 打印线程名
        System.out.println(Thread.currentThread().getName());
    }
    
    
    @Test
    public void testYield() {
        // 子线程打印偶数
        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + "==" + i);
                    }
                    // 当i为10的倍数是主动释放CPU的执行权
                    if (i % 10 == 0){
                        Thread.yield();
                    }
                }
            }
        }, "sub-thread").start();

        // 主线程打印奇数
        for (int i = 1; i <= 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + "==" + i);
            }
        }
    }

    @Test
    public void testJoin() {
        // 子线程打印偶数
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + "==" + i);
                    }
                }
            }
        }, "sub-thread");

        thread.start();

        System.out.println("子线程是否存活：" + thread.isAlive());
        // 主线程打印奇数
        for (int i = 1; i <= 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + "==" + i);
            }
            // 当主线程打印到25时等待子线程完成后在执行
            if (i == 25){
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("子线程是否存活：" + thread.isAlive());
    }

    @Test
    public void testPriority() {
        // 子线程打印偶数
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + "==" +
                                Thread.currentThread().getPriority() + "==" + i);
                    }
                }
            }
        }, "sub-thread");

        // 设置子线程为最小优先级
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();

        // 设置主线程为最大优先级
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        // 主线程打印奇数
        for (int i = 1; i <= 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + "==" +
                        Thread.currentThread().getPriority() + "==" + i);
            }
        }
    }

}
