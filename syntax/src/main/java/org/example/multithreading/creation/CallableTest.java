package org.example.multithreading.creation;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) {
        // 实现call方法
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                int sum = 0;
                for (int i = 1; i <= 100; i++) {
                    sum += i;
                }
                return sum;
            }
        };

        // 创建FutureTask类包装Callable
        FutureTask futureTask = new FutureTask(callable);

        // 将futureTask传入Thread内
        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            // 获取线程内执行的结果，这个操作会阻塞当前线程
            Object o = futureTask.get();
            System.out.println(o);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
