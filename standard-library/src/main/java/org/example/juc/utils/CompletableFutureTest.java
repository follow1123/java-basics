package org.example.juc.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureTest {
    private static final Logger log = LoggerFactory.getLogger("CompletableFutureTest");

    public static void main(String[] args) {
        // 开始执行任务的方法
        // testStart();

        // 基础使用
        // testBasicUse();

        // 获取结果
        // testGetResult();

        // 处理异常
        // testHandleResult();

        // 消费结果
        // testConsumeResult();

        // 忽略结果
        // testIgnoreResult();

        // 使用线程池的规则
        // testTaskThreadPool();

        // 获取运行最快的任务的结果
        // testUseFaster();

        // 组合任务
        // testCombineTask();

        // 串联任务，常用于组合方法
        // testComposeTask();

        // 等待所有任务执行完成
        // testAllOfTask();

        // 等待任意一个任务执行完成，其他任务不会停止
        testAnyOfTask();
    }

    public static void testAnyOfTask() {
        CompletableFuture.anyOf(
                CompletableFuture.runAsync(() -> {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("task 1");
                }),
                CompletableFuture.runAsync(() -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("task 2");
                }),
                CompletableFuture.runAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("task 3");
                }),
                CompletableFuture.runAsync(() -> log.info("task 4"))
        ).thenRun(() -> log.info("done"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testAllOfTask() {
        CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> log.info("task 1")),
                CompletableFuture.runAsync(() -> log.info("task 2")),
                CompletableFuture.runAsync(() -> log.info("task 3")),
                CompletableFuture.runAsync(() -> log.info("task 4"))
        ).thenRun(() -> log.info("done"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testComposeTask() {
        CompletableFuture.supplyAsync(() -> {
                    log.info("run task 1");
                    return 1;
                }).thenCompose(r -> CompletableFuture.supplyAsync(() -> r + 1))
                .thenAccept(v -> log.info("get result: {}", v));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testCombineTask() {
        CompletableFuture.supplyAsync(() -> {
                    log.info("run task 1");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 1;
                }).thenCombine(CompletableFuture.supplyAsync(() -> {
                    log.info("run task 2");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 2;
                }), (result1, result2) -> result1 + "-" + result2)
                .thenAccept(v -> log.info("get result: {}", v));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testUseFaster() {
        CompletableFuture.supplyAsync(() -> {
                    log.info("run task 1");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 1;
                }).applyToEither(CompletableFuture.supplyAsync(() -> {
                    log.info("run task 2");
                    try {
                        Thread.sleep(11);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 2;
                }), v -> v)
                .thenAccept(v -> log.info("get result: {}", v));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testTaskThreadPool() {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        // 启动时指定了线程池，当前任务包括后续不带Async的方法都会使用指定的线程池
        CompletableFuture.supplyAsync(() -> {
                    log.info("task 1");
                    return 1;
                }, pool)
                // 如果提交的任务执行过快，后面的操作可能直接让主线程运行
                .thenRun(() -> {
                    log.info("task 2");
                })
                /*
                    使用带Async的方法没指定线程池的情况下
                    当前任务及后续使用不带Async的方法都在默认的ForkJoinPool内运行
                 */
                .thenRunAsync(() -> {
                    log.info("task 3");
                }).thenRun(() -> {
                    log.info("task 4");
                });
    }

    public static void testIgnoreResult() {
        System.out.println(CompletableFuture.supplyAsync(() -> {
                    int person = 20;
                    for (int i = 0; i < person; i++) {
                        log.info("上车{}人", i + 1);
                    }
                    return person;
                })
                // 消费结果
                .thenRun(() -> log.info("发车")).join());
    }

    public static void testConsumeResult() {
        CompletableFuture.supplyAsync(() -> {
                    log.info("run task");
                    return 1;
                })
                // 将结果转换为字符串
                .thenApply(v -> {
                    log.info("apply result: {}", v);
                    return v + "";
                })
                // 消费结果
                // .thenAccept(v -> log.info("accept result: {}", v));
                // 消费结果并处理异常
                .whenComplete((v, e) -> {
                    if (e != null) {
                        log.error("error", e);
                    } else {
                        log.info("accept result: {}", v);
                    }
                });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testHandleResult() {
        String result = CompletableFuture.supplyAsync(() -> {
                    log.info("run task");
                    int i = 1 / 0;
                    return 1;
                })
                // 将结果转换为字符串，无法处理异常
                // .thenApply(v -> {
                //     log.info("apply result: {}", v);
                //     return v + "";
                // })
                // 可以处理异常
                .handle((v, e) -> {
                    if (e != null) return "error str";
                    return v + "";
                })
                .join();
        log.info("get result: {}", result);
    }

    public static void testGetResult() {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
            log.info("run task");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });
        Integer v = null;
        // 阻塞获取结果，和Future一样
        // try {v = cf.get();} catch (InterruptedException | ExecutionException e) {throw new RuntimeException(e);}

        // 指定时间阻塞获取结果，超时就抛出异常，和Future一样
        // try {v = cf.get(500, TimeUnit.MICROSECONDS);} catch (InterruptedException | TimeoutException | ExecutionException e) {throw new RuntimeException(e);}

        // 等待获取结果，阻塞
        // v = cf.join();

        // 立即获取值，没有就返回默认值
        // v = cf.getNow(0);

        // log.info("result: {}", v);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
            直接打断运行的任务给一个指定的返回值
            这个方法的返回值为true说明打断成功，使用时会获取指定的值
            如果返回false，说明任务已经完成，使用时会获取任务返回的值
         */
        boolean complete = cf.complete(0);
        v = cf.join();
        log.info("is complete early: {}, value: {}", complete, v);
    }

    public static void testBasicUse() {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        // 提交任务
        CompletableFuture.supplyAsync(() -> {
                    log.info("step 1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "100";
                }, pool)
                // 处理上一步任务的结果
                .thenApply(v -> {
                    log.info("step 2");
                    return Integer.parseInt(v);
                })
                // 处理上一步任务的结果
                .thenApply(v -> {
                    log.info("step 3");
                    // return v / 0;
                    return v / 10;
                })
                // 处理上面所有步骤的异常
                .exceptionally(e -> {
                    log.error("error", e);
                    return 1;
                })
                // 接收结果并使用
                .thenAccept(v -> log.info("step 4 get value: {}", v));
    }

    public static void testStart() {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        CompletableFuture.runAsync(() -> log.info("runnable task 1"));
        CompletableFuture.runAsync(() -> log.info("runnable task 2 use custom pool"), pool);
        CompletableFuture.supplyAsync(() -> {
            log.info("supplier task 3");
            return 1;
        });
        CompletableFuture.supplyAsync(() -> {
            log.info("supplier task 4 use custom pool");
            return 1;
        }, pool);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
