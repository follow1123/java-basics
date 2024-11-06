package cn.y.java.juc.cas.aba;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

@Slf4j(topic = "ABATest")
public class ABATest {

    public static void main(String[] args) {
        // before();
        after();
    }

    public static void after(){
        AtomicStampedReference<String> str = new AtomicStampedReference<>("A", 1);
        afterMidOperate(str);

        String prev = str.getReference();
        int stamp = str.getStamp();
        try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
        log.info("set to C - {}", str.compareAndSet(prev, "C", stamp, stamp + 1));
        log.info("reference: {}, stamp: {}", str.getReference(), str.getStamp());
    }

    public static void afterMidOperate(AtomicStampedReference<String> str){
        Thread t1 = new Thread(() -> {
            String prev = str.getReference();
            int stamp = str.getStamp();
            log.info("set to B - {}", str.compareAndSet(prev, "B", stamp, stamp + 1));
            log.info("reference: {}, stamp: {}", str.getReference(), str.getStamp());
        });

        Thread t2 = new Thread(() -> {
            String prev = str.getReference();
            int stamp = str.getStamp();
            log.info("set to A - {}", str.compareAndSet(prev, "A", stamp, stamp + 1));
            log.info("reference: {}, stamp: {}", str.getReference(), str.getStamp());
        });

        t1.start();
        try{Thread.sleep(100);}catch(InterruptedException e){e.printStackTrace();}
        t2.start();
    }

    public static void before(){
        AtomicReference<String> str = new AtomicReference<>("A");

        String prev = str.get();
        beforeMidOperate(str);
        try{Thread.sleep(1000);}catch(InterruptedException e){e.printStackTrace();}
        log.info("set to C - {}", str.compareAndSet(prev, "C"));
    }

    public static void beforeMidOperate(AtomicReference<String> str){
        Thread t1 = new Thread(() -> {
            log.info("set to B - {}", str.compareAndSet(str.get(), "B"));
        });

        Thread t2 = new Thread(() -> {
            log.info("set to A - {}", str.compareAndSet(str.get(), "A"));
        });

        t1.start();
        try{Thread.sleep(100);}catch(InterruptedException e){e.printStackTrace();}
        t2.start();
    }
}
