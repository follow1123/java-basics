package org.example.multithreading.safety.singleton;

public class SingletonObjSyncMethod {
    private SingletonObjSyncMethod() {
    }

    private static SingletonObjSyncMethod instance;

    public static synchronized SingletonObjSyncMethod getInstance() {
        if (instance == null) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            instance = new SingletonObjSyncMethod();
        }

        return instance;
    }
}
