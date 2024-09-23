package cn.y.java.multithreading.thread_communication.producer_comsumer;

/**
 * 店员
 */
public class Clerk {

    private int product;

    public synchronized void addProduct(){
        if (product == 20){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }else {
            System.out.println(Thread.currentThread().getName() + "开始生成商品---" + ++product);
            notifyAll();
        }
    }

    public synchronized void minusProduct(){
        if (product == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }else {
            System.out.println(Thread.currentThread().getName() + "开始消费商品---" + product--);
            notifyAll();
        }

    }
}
