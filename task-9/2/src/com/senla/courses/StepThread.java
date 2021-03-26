package com.senla.courses;

class StepThread extends Thread {
    private final Object lock;

    private final String nameThread;

    public StepThread(Object object, String nameThread) {
        this.lock = object;
        this.nameThread = nameThread;
    }

    public String getNameThread() {
        return nameThread;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                    System.out.println(getNameThread());
                    lock.notify();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
