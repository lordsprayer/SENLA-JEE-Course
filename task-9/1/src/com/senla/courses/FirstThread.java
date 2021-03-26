package com.senla.courses;

public class FirstThread extends Thread{

    @Override
    public void run() {
        System.out.println(getState());// RUNNABLE
    }
}
