package com.senla.courses;

import java.util.Queue;

class Producer implements Runnable {
    private final Queue<Integer> sharedQueue;
    private final int SIZE;

    public Producer(Queue<Integer> sharedQueue, int size) {
        this.sharedQueue = sharedQueue;
        this.SIZE = size;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Produced: " + produce());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int produce() throws InterruptedException {
        synchronized (sharedQueue) {
            if (sharedQueue.size() == SIZE) {
                sharedQueue.wait();
            }
            int newValue = (int) (Math.random() * 100);
            sharedQueue.add(newValue);
            sharedQueue.notifyAll();
            return newValue;
        }
    }
}