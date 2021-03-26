package com.senla.courses;

public class SecondThread extends Thread {
    private final Object lock;

    public SecondThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                lock.notifyAll();
                lock.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
