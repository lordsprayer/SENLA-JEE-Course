package com.senla.courses;

public class Main {
    public static void main(String[] strings) {
        Object lock = new Object();
        Thread firstThread = new Thread(new StepThread(lock, "first"));
        Thread secondThread = new Thread(new StepThread(lock, "second"));
        firstThread.start();
        secondThread.start();
    }
}