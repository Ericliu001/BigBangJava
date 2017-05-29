package com.ericliu.concurrent;

import com.ericliu.concurrent.ConcurrentLinkedList.Node;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

/**
 * Created by ericliu on 29/5/17.
 */
public class ConcurrentLinkedListTest {

    private ConcurrentLinkedList list;


    @Before
    public void setUp() throws Exception {
        list = new ConcurrentLinkedList();
    }

    @Test
    public void enqueue() throws Exception {
        final CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            final int element = i;
            new Thread(){
                @Override
                public void run() {
                    Random random = new Random();
                    try {
                        Thread.sleep(random.nextInt(5000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    list.enqueue(new Node(element));
                    latch.countDown();
                }
            }.start();
        }

        latch.await();

        Node current = list.getHead();
        while (current != null) {
            System.out.print(current.data + ", ");
            current = current.next;
        }
    }

}