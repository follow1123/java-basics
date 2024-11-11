package cn.y.java.juc.data_structure;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j(topic = "ConcurrentLinkedQueueTest")
public class ConcurrentLinkedQueueTest {
    public static void main(String[] args) {
        testMethod();
    }

    public static void testMethod(){
        ExecutorService pool = Executors.newFixedThreadPool(10);
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

        pool.submit(() -> {
            log.info("start take");
            Integer val = queue.poll();
            // 也可以指定等待的时间
            // queue.poll(1, TimeUnit.SECONDS)
            log.info("take value: {}", val);
        });
        try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
        pool.submit(() -> {
            log.info("start put");
            queue.offer(1);
            // 也可以指定等待的时间
            // queue.offer(1, 1, TimeUnit.SECONDS);
            log.info("put value: {}", 1);
        });
    }
}