package com.senla.courses;

public class Main {
    public static void main(String[] strings) throws InterruptedException {
        Object lock = new Object();
        Object lock1 = new Object();
        Thread thread = new FirstThread();
        Thread thread1 = new SecondThread(lock);
        Thread thread2 = new ThirdThread(lock1);

        System.out.println(thread.getState());// NEW
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lock){
            thread1.start();
            lock.wait();
            System.out.println(thread1.getState()); // WAITING
            lock.notifyAll();
            System.out.println(thread1.getState()); // BLOCKED

        }
        synchronized (lock1) {
            thread2.start();
            lock1.wait();
            System.out.println(thread2.getState()); // TIMED_WAITING
        }
        System.out.println(thread.getState());// TERMINATED
    }
}
