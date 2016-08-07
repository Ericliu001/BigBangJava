package com.ericliu.threading.play;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ericliu on 7/08/2016.
 */

public class LockAndCondition {

    private static final int CAPACITY = 15;
    private int count;

    private ReentrantLock mLock;

    private Condition notEmpty;
    private Condition notFull;

    int[] mArray = new int[CAPACITY];

    public LockAndCondition() {
        mLock = new ReentrantLock();
        notEmpty = mLock.newCondition();
        notFull = mLock.newCondition();
    }


    public int take() throws InterruptedException {
        mLock.lockInterruptibly();

        try {
            while (count == 0) {
                notEmpty.await();
            }

            System.out.println("Take " + count);
            int data = mArray[count - 1];
            count--;
            notFull.signal();
            return data;
        } finally {
            mLock.unlock();
        }
    }

    public void put(int data) throws InterruptedException {
        mLock.lockInterruptibly();

        try {
            while (count == CAPACITY) {
                notFull.await();
            }
            System.out.println("Put " + count);
            mArray[count] = data;
            count++;
            notEmpty.signal();
        } finally {
            mLock.unlock();
        }
    }


    public static void main(String[] args) {

        LockAndCondition lockAndCondition = new LockAndCondition();

        new Thread() {

            @Override
            public void run() {

                for (int i = 0; i < 15; i++) {
                    try {
                        lockAndCondition.put(i + 100);
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();


        new Thread() {

            @Override
            public void run() {
                for (int i = 0; i < 15; i++) {
                    try {
                        lockAndCondition.take();
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
