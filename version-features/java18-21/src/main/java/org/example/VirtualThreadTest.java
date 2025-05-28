package org.example;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadTest {
    @Test
    public void testStartWithStaticMethod() {
        Thread.ofVirtual().name("virtual thread 1")
                .start(() -> System.out.printf("%s started\n", Thread.currentThread().getName()));

        Thread.startVirtualThread(() -> System.out.println("started"));

        Thread virtualThread3 = Thread.ofVirtual().name("virtual thread 3")
                .unstarted(() -> System.out.printf("%s started\n", Thread.currentThread().getName()));
        virtualThread3.start();

        System.out.println(virtualThread3.isDaemon());
        System.out.println(virtualThread3.isVirtual());
    }

    @Test
    public void testStartWithThreadPool() {
        try (ExecutorService pool = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 5; i++) {
                pool.submit(() -> System.out.println("started"));
            }
        }
    }
}
