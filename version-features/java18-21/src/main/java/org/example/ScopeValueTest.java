package org.example;

public class ScopeValueTest {

    public static void main(String[] args) {
    }
    // private static final ScopedValue<String> scopeValue = ScopedValue.newInstance();
    //
    // public static void main(String[] args) {
    //     try (ExecutorService pool = Executors.newFixedThreadPool(3)) {
    //         for (int i = 0; i < 5; i++) {
    //             pool.submit(() -> {
    //                 ScopedValue.runWhere(scopeValue, "hello", ScopeValueTest::handleMessage);
    //             });
    //         }
    //     }
    // }
    //
    // public static void handleMessage(){
    //     String value = scopeValue.get();
    //     System.out.printf("handle message from %s: %s\n", Thread.currentThread().getName(), value);
    // }
}
