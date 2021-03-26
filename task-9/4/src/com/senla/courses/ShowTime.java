package com.senla.courses;

import java.util.Date;

public class ShowTime extends Thread {
    int n;

    public ShowTime(int n) {
        this.n = n;
    }

    @SuppressWarnings("deprecation")
    public void run(){

        while(true){

            Date time = new Date();
            String stringBuilder = time.getHours() +
                    ":" +
                    time.getMinutes() +
                    ":" +
                    time.getSeconds();
            System.out.println(stringBuilder);
            try {
                Thread.sleep(n* 1000L);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
