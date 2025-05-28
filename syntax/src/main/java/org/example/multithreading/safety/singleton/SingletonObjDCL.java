package org.example.multithreading.safety.singleton;

public class SingletonObjDCL {
    private SingletonObjDCL() {
    }

    /*
        这里需要加上volatile关键字
        创建对象时在JVM层面会执行多个步骤，有可能对象已经实例化，但没执行<init>方法
        这样获取到的对象就有问题，所有需要添加volatile关键字将创建对象的步骤原子化
     */
    private static volatile SingletonObjDCL instance;

    public static SingletonObjDCL getInstance() {
        if (instance == null) {
            synchronized (SingletonObjDCL.class) {
                if (instance == null) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    instance = new SingletonObjDCL();
                }
            }
        }

        return instance;
    }
}
