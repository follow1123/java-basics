package cn.y.java.multithreading.thread_safe.singleton;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SingletonTest {

    /**
     * 测试懒汉式线程安全问题
     */
    @Test
    public void testSingletonNotSafe() {
        SingletonObj[] singletonObjs = new SingletonObj[2];

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                singletonObjs[0] = SingletonObj.getInstance();
            }
        });
        thread.start();

        singletonObjs[1] = SingletonObj.getInstance();


        // 等待子线程执行完
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 判断子线程和主线程创建的两个对象是否相同
        System.out.println(Arrays.toString(singletonObjs));
        System.out.println(singletonObjs[0] == singletonObjs[1]);
    }

    /**
     * 测试使用同步方法解决懒汉式线程安全问题
     */
    @Test
    public void testSingletonSyncMethod() {
        SingletonObjSyncMethod[] singletonObjs = new SingletonObjSyncMethod[2];

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                singletonObjs[0] = SingletonObjSyncMethod.getInstance();
            }
        });
        thread.start();

        singletonObjs[1] = SingletonObjSyncMethod.getInstance();


        // 等待子线程执行完
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 判断子线程和主线程创建的两个对象是否相同
        System.out.println(Arrays.toString(singletonObjs));
        System.out.println(singletonObjs[0] == singletonObjs[1]);
    }
    /**
     * 测试使用双重检测锁解决懒汉式线程安全问题
     */
    @Test
    public void testSingletonDCL() {
        SingletonObjDCL[] singletonObjs = new SingletonObjDCL[2];

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                singletonObjs[0] = SingletonObjDCL.getInstance();
            }
        });
        thread.start();

        singletonObjs[1] = SingletonObjDCL.getInstance();


        // 等待子线程执行完
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 判断子线程和主线程创建的两个对象是否相同
        System.out.println(Arrays.toString(singletonObjs));
        System.out.println(singletonObjs[0] == singletonObjs[1]);
    }
}
