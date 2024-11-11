package cn.y.java.multithreading.thread_communication.producer_comsumer;

public class ProducerConsumerTest {

    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer producer = new Producer(clerk, "生产者");
        Consumer consumer1 = new Consumer(clerk, "消费者1");
        Consumer consumer2 = new Consumer(clerk, "消费者2");

        // 一个生产者，两个消费者
        producer.start();
        consumer1.start();
        consumer2.start();
    }
}
