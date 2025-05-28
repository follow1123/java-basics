package org.example.juc.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ExceptionHandlerTest {
    private static final Logger log = LoggerFactory.getLogger("ExceptionHandlerTest");

    public static void main(String[] args) {
        // 测试抛出异常
        // testThrowException();

        // 处理异常
        testHandleException();
    }


    private static void testHandleException() {
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        log.info("submit task start");
        Future<Integer> t1 = pool.submit(() -> {
            log.info("run task1");
            return 1 / 0;
        });
        log.info("submit task end");

        try {
            // 使用Future.get方法时，如果任务执行时出现了异常，在get时会抛出
            Integer result = t1.get();

            log.info("result: {}", result);
        } catch (InterruptedException | ExecutionException e) {
            log.error("handle task exception: ", e);
        }
    }

    private static void testThrowException() {
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        log.info("start");
        log.info("execute task start");
        pool.execute(() -> {
            log.info("run task1");
            int i = 1 / 0;
        });
        log.info("execute task end");
        log.info("submit task start");
        pool.submit(() -> {
            log.info("run task2");
            int i = 1 / 0;
        });
        log.info("submit task end");
        /*
            测试发现使用execute方法执行的任务出现异常后，会打印异常信息
            而使用submit方法执行的任务出现异常时，不会打印异常信息
         */
        log.info("core size: {}, largest size: {}", pool.getCorePoolSize(), pool.getLargestPoolSize());
        log.info("all task: {}, complete task: {}", pool.getTaskCount(), pool.getCompletedTaskCount());
    }
}
