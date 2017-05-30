package com.ericliu.concurrent;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * Created by ericliu on 30/5/17.
 */
public class MutexTest {

    private Mutex mutex = new Mutex();
    private int num;
    private int num2;

    private void printNumberSynced() {
        mutex.lock();
        System.out.print((num++) + ", ");

        mutex.unlock();
    }

    private void printNumberUnSynced() {
        System.out.print((num2++) + ", ");
    }

    @Test
    public void test1() throws Exception {
        System.out.println("Synced:");
        final CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    printNumberSynced();
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
                    printNumberUnSynced();
                    latch.countDown();
                }
            }.start();
        }
        latch.await();
    }

}