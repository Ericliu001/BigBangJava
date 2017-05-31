package com.ericliu.concurrent;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by ericliu on 30/5/17.
 * here is a minimal Mutex class, that uses synchronization state 100 to mean unlocked,
 * and one to mean locked. This class does not need the value arguments supported for synchronization methods,
 * so uses zero, and otherwise ignores them.
 */
public class Mutex {

    static class Sync extends AbstractQueuedSynchronizer {

        public Sync() {
            setState(100); // set the initial state, being unlocked.
        }

        @Override
        protected boolean tryAcquire(int ignore) {
            return compareAndSetState(100, 1);
        }

        @Override
        protected boolean tryRelease(int ignore) {
            setState(100);
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
