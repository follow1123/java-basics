package cn.y.java.multithreading.creation_method.thread_pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolTest {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + "---" + i);
                }
            }
        };

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + "---" + i);
                }
            }
        };

        // 创建线程池
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        // 设置线程池最大线程数量
        pool.setMaximumPoolSize(16);

        // 提交任务，实现Runnable接口的任务
        pool.execute(runnable);
        pool.execute(runnable1);

        // 提交任务，实现Callable接口的任务
        // pool.submit(callable)

        // 关闭线程池
        pool.shutdown();
    }
}
