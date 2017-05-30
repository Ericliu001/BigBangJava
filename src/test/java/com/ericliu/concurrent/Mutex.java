package com.ericliu.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by ericliu on 30/5/17.
 */
public class Mutex {

    static class Sync extends AbstractQueuedSynchronizer{
        @Override
        protected boolean tryAcquire(int ignore) {
            return compareAndSetState(0,1);
        }

        @Override
        protected boolean tryRelease(int ignore) {
            setState(0);
            return true;
        }
    }

    private final Sync sync = new Sync();

    public void lock() {
        sync.acquire(0);
    }

    public void unlock() {
        sync.release(0);
    }
}
