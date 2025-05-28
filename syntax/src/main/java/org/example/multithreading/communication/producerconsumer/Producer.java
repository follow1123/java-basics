package org.example.multithreading.communication.producerconsumer;

public class Producer extends Thread {
    private Clerk clerk;

    public Producer(Clerk clerk, String name) {
        this.clerk = clerk;
        this.setName(name);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            clerk.addProduct();
        }
    }

}
