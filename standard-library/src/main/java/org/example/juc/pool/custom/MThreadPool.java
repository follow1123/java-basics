package org.example.juc.pool.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MThreadPool {
    private static final Logger log = LoggerFactory.getLogger("MThreadPool");

    // 工作线程
    private final List<Worker> workers;
    // 任务队列
    private final MBlockingQueue taskQueue;
    // 核心线程数
    private final int coreSize;
    // 超时时间
    private final long time;
    private final TimeUnit unit;
    // 任务队列满时的拒绝策略
    private final RejectStrategy rejectStrategy;

    public MThreadPool(int coreSize, long time, TimeUnit unit, int queueCapacity, RejectStrategy rejectStrategy) {
        this.coreSize = coreSize;
        this.time = time;
        this.unit = unit;
        this.workers = new ArrayList<>(coreSize);
        this.taskQueue = new MBlockingQueue(queueCapacity);
        this.rejectStrategy = rejectStrategy;
    }

    public MThreadPool(int coreSize, long time, TimeUnit unit, int queueCapacity) {
        this.coreSize = coreSize;
        this.time = time;
        this.unit = unit;
        this.workers = new ArrayList<>(coreSize);
        this.taskQueue = new MBlockingQueue(queueCapacity);
        this.rejectStrategy = new RejectStrategy.ExceptionStrategy();
    }

    public void submit(Runnable task) {
        synchronized (workers) {
            if (workers.size() < coreSize) {
                log.info("add worker");
                Worker worker = new Worker(task);
                workers.add(worker);
                worker.start();
            } else {
                taskQueue.tryPut(rejectStrategy, task);
            }
        }
    }

    private void removeWorker(Worker worker) {
        synchronized (workers) {
            log.info("remove worker");
            workers.remove(worker);
        }
    }

    public class Worker extends Thread {

        private Runnable task;
        private boolean state = true;

        public Worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            // while (task != null || (task = taskQueue.take()) != null) {
            while (task != null || (task = taskQueue.poll(time, unit)) != null) {
                try {
                    log.info("task run");
                    task.run();
                } catch (Exception e) {
                    log.error("catch exception:", e);
                } finally {
                    task = null;
                }
            }
            removeWorker(this);
        }
    }

    /**
     * 自定义阻塞队列
     */
    public static class MBlockingQueue {
        private static final Logger log = LoggerFactory.getLogger("MBlockingQueue");

        private final ArrayDeque<Runnable> queue = new ArrayDeque<>();
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition emptyWaitSet = lock.newCondition();
        private final Condition fullWaitSet = lock.newCondition();
        private final int capacity;

        public MBlockingQueue(int capacity) {
            this.capacity = capacity;
        }

        public int size() {
            return queue.size();
        }

        public boolean offer(Runnable task, long time, TimeUnit unit) {
            lock.lock();
            try {
                long nanos = unit.toNanos(time);
                while (queue.size() == capacity) {
                    try {
                        log.info("wait to put");
                        if (nanos <= 0) return false;
                        nanos = fullWaitSet.awaitNanos(nanos);
                        fullWaitSet.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                log.info("put task");
                queue.addLast(task);
                emptyWaitSet.signal();
                return true;
            } finally {
                lock.unlock();
            }
        }

        // 阻塞添加
        public void put(Runnable task) {
            lock.lock();
            try {
                while (queue.size() == capacity) {
                    try {
                        log.info("wait to put");
                        fullWaitSet.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                log.info("put task");
                queue.addLast(task);
                emptyWaitSet.signal();
            } finally {
                lock.unlock();
            }
        }

        // 带超时的阻塞获取
        public Runnable poll(long time, TimeUnit unit) {
            lock.lock();
            try {
                long nanos = unit.toNanos(time);
                while (queue.isEmpty()) {
                    try {
                        if (nanos <= 0) return null;
                        // 防止被唤醒后任务又被其他线程抢走了，继续等待剩余的时间
                        nanos = emptyWaitSet.awaitNanos(nanos);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                Runnable task = queue.removeFirst();
                fullWaitSet.signal();
                return task;
            } finally {
                lock.unlock();
            }

        }

        // 阻塞获取
        public Runnable take() {
            lock.lock();
            try {
                while (queue.isEmpty()) {
                    try {
                        emptyWaitSet.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                Runnable task = queue.removeFirst();
                fullWaitSet.signal();
                return task;
            } finally {
                lock.unlock();
            }
        }

        public void tryPut(RejectStrategy rejectStrategy, Runnable task) {
            lock.lock();
            try {
                if (queue.size() == capacity) {
                    rejectStrategy.reject(this, task);
                } else {
                    log.info("put task");
                    queue.addLast(task);
                    emptyWaitSet.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
