package org.example.juc.pool.custom;

import java.util.concurrent.TimeUnit;

@FunctionalInterface
public interface RejectStrategy {

    void reject(MThreadPool.MBlockingQueue taskQueue, Runnable task);

    public static class DropStrategy implements RejectStrategy {

        @Override
        public void reject(MThreadPool.MBlockingQueue taskQueue, Runnable task) {
            System.out.println("drop task");
        }
    }

    public static class ExceptionStrategy implements RejectStrategy {

        @Override
        public void reject(MThreadPool.MBlockingQueue taskQueue, Runnable task) {
            throw new RuntimeException("reject");
        }
    }

    public static class WaitStrategy implements RejectStrategy {

        private final long time;
        private final TimeUnit unit;

        public WaitStrategy(long time, TimeUnit unit) {
            this.time = time;
            this.unit = unit;
        }

        @Override
        public void reject(MThreadPool.MBlockingQueue taskQueue, Runnable task) {
            while (!taskQueue.offer(task, time, unit)) {

            }
        }
    }

    public static class RunItYourselfStrategy implements RejectStrategy {
        @Override
        public void reject(MThreadPool.MBlockingQueue taskQueue, Runnable task) {
            task.run();
        }
    }

}
