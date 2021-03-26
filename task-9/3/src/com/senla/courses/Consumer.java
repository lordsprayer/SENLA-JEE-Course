package com.senla.courses;

import java.util.Queue;

class Consumer implements Runnable {
    private final Queue<Integer> sharedQueue;

    public Consumer(Queue<Integer> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Consumed: " + consume());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private Integer consume() throws InterruptedException {
        synchronized (sharedQueue) {
            if (sharedQueue.isEmpty()) {
                sharedQueue.wait();
            }
            sharedQueue.notifyAll();
            return sharedQueue.poll();
        }
    }
}