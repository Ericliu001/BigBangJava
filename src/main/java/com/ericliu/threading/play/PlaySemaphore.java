package com.ericliu.threading.play;

import java.util.concurrent.Semaphore;

/**
 * Created by ericliu on 9/08/2016.
 */

public final class PlaySemaphore {

    private final Semaphore mSemaphore = new Semaphore(2); // only allow 2 threads to access the name at the same time.
    private String name = "Eric_Liu";


    public String getName() throws InterruptedException {
        mSemaphore.acquire();
        return name;
    }

    public void releaseName() {
        mSemaphore.release();
    }


    public static void main(String[] args) {
        PlaySemaphore playSemaphore = new PlaySemaphore();

        for (int i = 0; i < 20; i++) {
            new Thread() {

                @Override
                public void run() {
                    String name = null;
                    try {
                        name = playSemaphore.getName();
                        Thread.sleep(1000);
                        System.out.printf(" " + name);
                        playSemaphore.releaseName();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        }
    }


}
