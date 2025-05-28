package org.example.juc.threadlocal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ReferencesTest {
    private static final Logger log = LoggerFactory.getLogger("ReferencesTest");

    public static void main(String[] args) {
        // 强引用
        // testStrongReference();

        // 软引用
        // testSoftReference();

        // 弱引用
        // testWeakReference();

        // 虚引用
        testPhantomReference();
    }

    public static void testPhantomReference() {
        ReferenceQueue<Obj> objQueue = new ReferenceQueue<>();
        PhantomReference<Obj> obj = new PhantomReference<>(new Obj(), objQueue);
        log.info("use {}", obj.get());
        new Thread(() -> {
            log.info("listen reference queue");
            try {
                // 阻塞直到虚引用被回收
                objQueue.remove();
                log.info("obj was recycled");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            System.gc();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void testWeakReference() {
        WeakReference<Obj> obj = new WeakReference<>(new Obj());
        log.info("use {}", obj.get());
        // 手动执行gc
        System.gc();
        // 一执行gc对象就会被回收
        log.info("use {}", obj.get());
    }

    public static void testSoftReference() {
        // 执行前添加jvm参数-Xms10m -Xmx10m
        WeakReference<Obj> obj = new WeakReference<>(new Obj());
        log.info("use {}", obj.get());
        // 创建4m内存数组
        byte[] data = new byte[4 * 1024 * 1024];
        // 此时发现对象已经被回收了
        log.info("use {}", obj.get());
    }

    public static void testStrongReference() {
        Obj a = new Obj("a");

        new Thread(() -> {
            // 只有在没有根指向这个引用的情况下才会被回收
            Obj b = new Obj("b");
            log.info("use {}", b);
        }).start();

        log.info("use {}", a);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.gc();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
