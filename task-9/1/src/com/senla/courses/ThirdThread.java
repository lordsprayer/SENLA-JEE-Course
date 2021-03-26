package com.senla.courses;

public class ThirdThread extends Thread {
private final Object lock1;

    public ThirdThread(Object lock1) {
        this.lock1 = lock1;
    }

    @Override
    public void run() {
        try {
            synchronized (lock1) {
                lock1.notifyAll();
                lock1.wait(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
