package com.ericliu.concurrent;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ericliu on 30/5/17.
 */
public class MutexTest {

    private Mutex mutex = new Mutex();
    private int num;
    private int num2;

    private void printNumber() {
        System.out.print((num++) + ", ");
    }


    @Test
    public void test1() throws Exception {
        System.out.println("Synced:");
        final CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread() {

                @Override
                public void run() {
                    mutex.lock();
                    printNumber();

                    try {
                        Random random = new Random();
                        Thread.sleep(random.nextInt(500));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mutex.unlock();
                    latch.countDown();
                }
            }.start();
        }
        latch.await();
    }


    @Test
    public void test2() throws Exception {
        System.out.println("\nUnSynced:");
        final CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    printNumber();
                    try {
                        Random random = new Random();
                        Thread.sleep(random.nextInt(500));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    latch.countDown();
                }
            }.start();
        }
        latch.await();
    }

}