package com.ericliu;

import com.ericliu.threading.MyRunnable;

/**
 * Created by ericliu on 6/01/2016.
 */
public class Main {


    public static void main(String[] args) throws InterruptedException {

        System.out.println("main method starts");

        Thread thread1 = new Thread(new MyRunnable());
        thread1.start();
        thread1.join(); // wait for the run method of this thread to return then continue.

        System.out.println("main method ends");

    }



}
