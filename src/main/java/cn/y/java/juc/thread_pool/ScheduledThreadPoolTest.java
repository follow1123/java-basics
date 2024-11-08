package cn.y.java.juc.thread_pool;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "ScheduledThreadPoolTest")
public class ScheduledThreadPoolTest {

    public static void main(String[] args) {
        // test();

        // 每秒执行一次任务
        // testScheduleFixedRate();

        // 每天晚上23点执行任务
        testScheduleFixedDelay();
    }

    private static void testScheduleFixedDelay() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);

        // 默认间隔时间，24小时
        long delay = 24 * 60 * 60;
        LocalTime localTime = LocalTime.now();
        LocalTime scheduleTime = LocalTime.of(23, 0, 0);
        Duration between = Duration.between(localTime, scheduleTime);
        /*
            计算出当前时间距离晚上11点还剩多少秒
            果是负数，说明已经超过了晚上11点了，直接减去多余的秒数
         */
        long seconds = between.toSeconds();
        long initDelay = seconds >= 0 ? seconds : delay + seconds;
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = localDate.atTime(localTime.plusSeconds(initDelay));
        log.info("execute time: {}", localDateTime);
        log.info("next execute time: {}", localDateTime.plusSeconds(delay));
        pool.scheduleWithFixedDelay(() -> log.info("execute"), initDelay, delay, TimeUnit.SECONDS);
    }

    private static void testScheduleFixedRate() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
        // 两秒后每隔1秒执行一次这个任务
        pool.scheduleAtFixedRate(() -> log.info("execute"), 2, 1, TimeUnit.SECONDS);
    }

    private static void test() {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(3);
        pool.schedule(()-> {
            // 耗时任务不影响后面的任务
            try{Thread.sleep(2000);}catch(InterruptedException e){e.printStackTrace();}
            log.info("execute t1");
        }, 1, TimeUnit.SECONDS);
        pool.schedule(()-> {
            // 出现异常不影响后面的任务
            int i = 1 /0;
            log.info("execute t2");
        }, 1, TimeUnit.SECONDS);
        pool.schedule(()-> {
            log.info("execute t3");
        }, 1, TimeUnit.SECONDS);
    }
}
