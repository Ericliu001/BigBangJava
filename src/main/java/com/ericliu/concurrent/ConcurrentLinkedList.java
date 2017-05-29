package com.ericliu.concurrent;

import sun.misc.Unsafe;

/**
 * Created by ericliu on 29/5/17.
 */
public class ConcurrentLinkedList {

    private Node head;
    private Node tail;

    private final static Unsafe unsafe = Unsafe.getUnsafe();
    private final static long headOffset;
    private final static long tailOffset;

    static {
        try {
            headOffset = unsafe.objectFieldOffset(ConcurrentLinkedList.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset(ConcurrentLinkedList.class.getDeclaredField("tail"));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
    }

    private boolean compareAndSetHead(final Node update) {
        return unsafe.compareAndSwapObject(ConcurrentLinkedList.this, headOffset, null, update);
    }

    private boolean compareAndSwapTail(final Node expected, final Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expected, update);
    }


    public static class Node {
        public Node prev;
        public Node next;
    }


    public Node enqueue(final Node node) {
        for (;;) {
            Node t = tail;
            if (t == null) {
                if (compareAndSetHead(new Node())) {
                    tail = head;
                }
            } else {
                node.prev = t;
                if (compareAndSwapTail(t, node)) {
                    t.next = node;
                    return t;
                }
            }
        }
    }

}
