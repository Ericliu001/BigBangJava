package com.ericliu.threading.play;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ericliu on 9/08/2016.
 */

public final class PlayCountDownLatch {

    private static final int NUM_PARALLEL_TASKS = 4;
    private CountDownLatch taskStartLatch = new CountDownLatch(1);
    private CountDownLatch taskDoneLatch = new CountDownLatch(NUM_PARALLEL_TASKS);


    private class WorkerThread extends Thread {

        @Override
        public void run() {
            try {
                taskStartLatch.await();
                Random random = new Random();

                Thread.sleep(1000 * random.nextInt(5));
                System.out.println("Task done!");
                taskDoneLatch.countDown();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private class ProcessingThread extends Thread {

        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                new WorkerThread().start();
            }

            doPreparationWork();

            try {
                taskDoneLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("All work done! ");


        }

        private void doPreparationWork() {
            try {
                Thread.sleep(2000);
                System.out.println("Preparing to start work threads ... ");
                taskStartLatch.countDown(); // let all worker thread run.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        new PlayCountDownLatch().new ProcessingThread().start();

    }

}


