package org.example.multithreading.safety.singleton;

public class SingletonObj {
    private SingletonObj() {
    }

    private static SingletonObj instance;

    public static synchronized SingletonObj getInstance() {
        if (instance == null) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            instance = new SingletonObj();
        }

        return instance;
    }
}
