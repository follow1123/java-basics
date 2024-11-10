package cn.y.java.juc.thread_local;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j(topic = "ThreadLocalTest")
public class ThreadLocalTest {

    public static void main(String[] args) {
        // 不使用ThreadLocal
        // testWithoutThreadLocal();

        // 使用ThreadLocal
        testWithThreadLocal();

        // testWithThreadLocalAndThreadPool();

        // 测试内存泄漏，添加-Xms10m -Xmx10m参数，设置最大堆内存
        // testMemoryLeaks();

    }

    private static void testMemoryLeaks(){
        ExecutorService pool = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 6; i++) {
            pool.submit(() -> {
                log.info("enter task");
                ThreadLocal<Obj> tl = ThreadLocal.withInitial(() -> new Obj(true));
                try {
                    try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
                    Obj obj = tl.get();
                    log.info("use data: {}", obj);
                    // 使用对象...
                }finally {
                    // 这里注释就会出现内存溢出
                    // tl.remove();
                }
            });
        }
        try{Thread.sleep(2000);}catch(InterruptedException e){e.printStackTrace();}
        for (int i = 0; i < 10; i++) {
            try{Thread.sleep(10);}catch(InterruptedException e){e.printStackTrace();}
            System.gc();
        }

        log.info("create 2mb byte array");
        byte[] data = new byte[1024 * 1024 * 3];
        log.info("use byte array");
    }

    private static void testWithThreadLocalAndThreadPool() {
        Rating rating = new Rating();
        ExecutorService pool = Executors.newFixedThreadPool(4);

        for (int i = 0; i < 6; i++) {
            String name = "评委" + (i + 1);
            pool.submit(() -> {
                try{
                    rating.scoringLocal(new Random().nextInt(0, 10));
                    log.info("{} 打分：{}", name, rating.myScore());
                }finally {
                    // 使用线程池的情况下，由于线程会复用，如果不清理，会出现累加的问题
                    rating.clearScore();
                }
            });
        }
        try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
        log.info("total score: {}", rating.total());
        pool.shutdown();
    }

    private static void testWithThreadLocal() {
        Rating rating = new Rating();

        new Thread(() -> {
            try{
                rating.scoringLocal(new Random().nextInt(0, 10));
                log.info("{} 打分：{}", Thread.currentThread().getName(), rating.myScore());
            }finally {
                boolean enter = false;
                while (!enter){}
                rating.clearScore();
            }
        }, "评委1").start();
        new Thread(() -> {
            try{
                rating.scoringLocal(new Random().nextInt(0, 10));
                log.info("{} 打分：{}", Thread.currentThread().getName(), rating.myScore());
            }finally {
                boolean enter = false;
                while (!enter){}
                rating.clearScore();
            }
        }, "评委2").start();

        try{Thread.sleep(100);}catch(InterruptedException e){e.printStackTrace();}
        log.info("total score: {}", rating.total());
    }

    private static void testWithoutThreadLocal() {
        Rating rating = new Rating();
        new Thread(() -> rating.scoring(new Random().nextInt(0, 10)), "评委1").start();
        new Thread(() -> rating.scoring(new Random().nextInt(0, 10)), "评委2").start();
        try{Thread.sleep(100);}catch(InterruptedException e){e.printStackTrace();}
        log.info("total score: {}", rating.total());
    }

    @Slf4j(topic = "Rating")
    static class Rating{

        private int score;

        private final ThreadLocal<Integer> tl = ThreadLocal.withInitial(() -> 0);

        public synchronized void scoring(int score){
            log.info("{} 打分：{}", Thread.currentThread().getName(), score);
            this.score += score;
        }

        public void scoringLocal(int score){
            tl.set(tl.get() + score);
            synchronized (this){
                this.score += score;
            }
        }

        public int myScore(){
            return tl.get();
        }

        public void clearScore(){
            tl.remove();
        }

        public int total(){
            return score;
        }
    }
}
