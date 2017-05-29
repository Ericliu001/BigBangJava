package com.ericliu.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by ericliu on 29/5/17.
 */
public class ConcurrentLinkedList {

    private Node head;
    private Node tail;

    private final static Unsafe unsafe;
    private final static long headOffset;
    private final static long tailOffset;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
            headOffset = unsafe.objectFieldOffset(ConcurrentLinkedList.class.getDeclaredField("head"));
            tailOffset = unsafe.objectFieldOffset(ConcurrentLinkedList.class.getDeclaredField("tail"));
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            throw new Error(ex);
        }
    }

    private boolean compareAndSetHead(final Node update) {
        return unsafe.compareAndSwapObject(ConcurrentLinkedList.this, headOffset, null, update);
    }

    private boolean compareAndSwapTail(final Node expected, final Node update) {
        return unsafe.compareAndSwapObject(this, tailOffset, expected, update);
    }


    public static class Node {
        public int data;
        public Node prev;
        public Node next;

        public Node(final int data) {
            this.data = data;
        }
    }


    public Node enqueue(final Node node) {
        for (; ; ) {
            Node t = tail;
            if (t == null) {
                if (compareAndSetHead(new Node(0))) {
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


    public Node getHead() {
        if (head == null) {
            return null;
        }
        return head.next;
    }
}
