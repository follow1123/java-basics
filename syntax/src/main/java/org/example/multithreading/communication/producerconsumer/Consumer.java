package org.example.multithreading.communication.producerconsumer;

public class Consumer extends Thread {
    private Clerk clerk;

    public Consumer(Clerk clerk, String name) {
        this.clerk = clerk;
        this.setName(name);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clerk.minusProduct();
        }
    }
}
