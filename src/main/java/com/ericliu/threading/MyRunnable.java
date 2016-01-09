package com.ericliu.threading;

/**
 * Created by ericliu on 9/01/2016.
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("runs from MyRunnable");
    }
}
