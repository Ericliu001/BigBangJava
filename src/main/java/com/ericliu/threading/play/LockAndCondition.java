package com.ericliu.threading.play;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ericliu on 7/08/2016.
 */

public class LockAndCondition {

    private static final int CAPACITY = 15;
    private int index;

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
            while (index == 0) {
                notEmpty.await();
            }

            System.out.println("Take " + index);
            int data = mArray[index - 1];
            index--;
            notFull.signal();
            return data;
        } finally {
            mLock.unlock();
        }
    }

    public void put(int data) throws InterruptedException {
        mLock.lockInterruptibly();

        try {
            while (index == CAPACITY) {
                notFull.await();
            }
            System.out.println("Put " + index);
            mArray[index] = data;
            index++;
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
                        Thread.sleep(660);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
