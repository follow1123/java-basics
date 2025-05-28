package org.example.juc.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ThreadPoolExecutorTest {
    private static final Logger log = LoggerFactory.getLogger("ThreadPoolExecutorTest");

    public static void main(String[] args) {
        // test();

        // 测试救急线程什么时候执行
        // testWhenEmergencyThreadRun();

        // 使用固定大小的线程池
        // testFixedThreadPool();

        // 使用缓存线程池
        // testCachedThreadPool();

        // 测试同步队列
        // testSynchronousQueue();

        // 测试单线程线程池
        // testSingleThreadPool();

        // 提交单个任务
        // testSubmitTask();

        // 提交全部任务，并获取所有任务的返回值
        // testInvokeAllTask();

        // 提交全部任务，并获取最快执行完成的任务的返回值
        // testInvokeAnyTask();

        // 测试关闭线程
        // testShutdown();
        // 立即关闭线程
        testShutdownNow();
    }


    public static void testShutdownNow() {
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        // ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 3, 3, TimeUnit.SECONDS,
        //         new LinkedBlockingQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 5; i++) {
            int num = i;
            pool.submit(() -> {
                log.info("execute {}", num);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        log.info("before shutdown");
        /*
         不会阻塞当前线程
         正在运行的任务直接打断
         阻塞队列中的任务不会执行，会返回
         */
        List<Runnable> tasks = pool.shutdownNow();
        log.info("runnable tasks myself");
        tasks.forEach(Runnable::run);

        // 线程关闭后再提交任务就会执行默认或指定的拒绝策略
        pool.submit(() -> log.info("execute after shutdown"));
    }

    public static void testShutdown() {
        // ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 3, 3, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 5; i++) {
            int num = i;
            pool.submit(() -> {
                log.info("execute {}", num);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        log.info("before shutdown");
        // 不会阻塞当前线程，所有已经提交的任务都会执行完成
        pool.shutdown();
        log.info("other");

        // 线程关闭后再提交任务，就会执行默认或指定的拒绝策略
        pool.submit(() -> log.info("execute after shutdown"));
    }

    public static void testInvokeAnyTask() {
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        ArrayList<Callable<String>> tasks = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int mills = random.nextInt(0, 10) * 100;
            int num = i;
            tasks.add(() -> {
                try {
                    Thread.sleep(mills);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return num + "";
            });
        }
        /*
            只会获取最快执行完成的一个任务的结果
            其他任务，如果正在执行的就打断，没执行的就放弃
         */
        try {
            log.info(pool.invokeAny(tasks));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("core size: {}, largest size: {}", pool.getCorePoolSize(), pool.getLargestPoolSize());
        log.info("all task: {}, complete task: {}", pool.getTaskCount(), pool.getCompletedTaskCount());
    }

    public static void testInvokeAllTask() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        ArrayList<Callable<String>> tasks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int num = i;
            tasks.add(() -> num + "");
        }
        try {
            List<Future<String>> futures = pool.invokeAll(tasks);
            for (Future<String> future : futures) {
                log.info(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static void testSubmitTask() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.execute(() -> log.info("execute runnable task"));
        pool.submit(() -> log.info("submit runnable task"));
        Future<Integer> t1 = pool.submit(() -> {
            log.info("submit callable task");
            return 1;
        });
        try {
            System.out.println(t1.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        // 指定一个返回值，用于确认线程是否完成
        Future<String> t2 = pool.submit(() -> {
            int i = 1 / 0;
            log.info("submit runnable task with result");
        }, "done");
        try {
            System.out.println(t2.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static void testSingleThreadPool() {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            int num = i;
            pool.submit(() -> {
                log.info("execute {}", num);
                // 出现异常后也会执行后续的任务
                if (num == 2) throw new RuntimeException("err");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void testSynchronousQueue() {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        new Thread(() -> {
            log.info("putting 1");
            try {
                queue.put(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("1 putted");

            log.info("putting 2");
            try {
                queue.put(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("2 putted");
        }).start();

        // 一秒后取1时才能添加1
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            log.info("take 1");
            try {
                queue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        // 再一秒后取2时才能添加2
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            log.info("take 2");
            try {
                queue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }


    public static void testCachedThreadPool() {
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            int num = i;
            pool.submit(() -> {
                log.info("start {}", num);
                try {
                    Thread.sleep(10000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("core size: {}, largest size: {}", pool.getCorePoolSize(), pool.getLargestPoolSize());
        log.info("all task: {}, complete task: {}", pool.getTaskCount(), pool.getCompletedTaskCount());
    }

    public static void testFixedThreadPool() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 4; i++) {
            int num = i;
            pool.submit(() -> {
                log.info("execute {}", num);
            });
        }
    }

    private static void testWhenEmergencyThreadRun() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3, 5,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(2));
        for (int i = 0; i < 5; i++) {
            long[] valRef = new long[]{i, 1000};
            if (i == 0 || i == 1) {
                valRef[1] = 1000000;
            }
            pool.submit(() -> {
                log.info("start {} task", valRef[0]);
                try {
                    Thread.sleep(valRef[1]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("end {} task", valRef[0]);
            });
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("core size: {}, largest size: {}", pool.getCorePoolSize(), pool.getLargestPoolSize());
        log.info("all task: {}, complete task: {}", pool.getTaskCount(), pool.getCompletedTaskCount());
    }

    private static void test() {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3, 5,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 8; i++) {
            int num = i;
            pool.submit(() -> {
                log.info("execute {}", num);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        pool.allowCoreThreadTimeOut(true);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("core: {}, max: {}, active: {}", pool.getCorePoolSize(), pool.getMaximumPoolSize(), pool.getActiveCount());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("core: {}, max: {}, active: {}", pool.getCorePoolSize(), pool.getMaximumPoolSize(), pool.getActiveCount());
        pool.submit(() -> log.info("1234"));
    }
}
