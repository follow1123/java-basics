package cn.y.java.juc.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自定义锁，不可重入
 */
@Slf4j(topic = "MLock")
public class MLock implements Lock {

    private final MSync sync;

    public MLock() {this.sync = new MSync();}
    @Override
    public void lock() {sync.lock();}
    @Override
    public void lockInterruptibly() throws InterruptedException {sync.lockInterruptibly();}
    @Override
    public boolean tryLock() {return sync.tryLock();}
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {return sync.tryLock(time, unit);}
    @Override
    public void unlock() {sync.unlock();}
    @Override
    public Condition newCondition() {return sync.newCondition();}

    // 状态0为没加锁，状态1为加锁
    class MSync extends AbstractQueuedSynchronizer{
        /*
            由于状态只有两种，具体逻辑在tryAcquire和tryRelease方法内已经实现了
            所以传递参数时给一个固定值，这个值没意义，就是占个位置
         */
        private final int ignoreArg = 1;

        // 加锁，会阻塞
        void lock(){
            /*
                底层调用自己实现的tryAcquire方法
                无法加锁则直接进阻塞队列，并使用LockSupport.park阻塞
             */
            acquire(ignoreArg);
        }

        // 可打断的锁，会阻塞
        void lockInterruptibly() throws InterruptedException {
            // 底层调用acquire方法
            acquireInterruptibly(ignoreArg);
        }

        // 加锁，只试一次，不会阻塞
        boolean tryLock() {
            // 直角调用自己实现的方法
            return tryAcquire(ignoreArg);
        }

        // 加锁，阻塞指定时间
        boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            // 底层调用acquire方法
            return tryAcquireNanos(ignoreArg, unit.toNanos(time));
        }

        // 解锁
        void unlock() {
            /*
                底层调用自己实现的tryRelease方法
                解锁成功后会唤醒下一个线程
             */
            release(ignoreArg);
        }

        // 条件变量
        Condition newCondition() {
            return new ConditionObject();
        }

        // 主要重写的方法
        // 尝试加锁，试一次，不阻塞线程
        @Override
        protected boolean tryAcquire(int acquires) {
            if (getState() == 0 && compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        // 尝试解锁，试一次，不阻塞线程
        @Override
        protected boolean tryRelease(int arg) {
            if (getExclusiveOwnerThread() != Thread.currentThread())
                throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        // 判断当前是否处于独占模式
        @Override
        protected boolean isHeldExclusively() {
            return Thread.currentThread() == getExclusiveOwnerThread();
        }
    }
}
