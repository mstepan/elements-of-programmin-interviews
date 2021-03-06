package com.max.epi.heaps;


import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for QueueApiUsingHeap
 */
public final class QueueApiUsingHeapTest {

    @Test
    public void enqAndDeq() {

        QueueApiUsingHeap.QueueAdapter queue = new QueueApiUsingHeap.QueueAdapter();

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
        assertSame(null, queue.peek());

        queue.add(5);
        queue.add(7);
        queue.add(12);

        assertEquals(Integer.valueOf(5), queue.poll());
        assertEquals(Integer.valueOf(7), queue.poll());

        queue.add(3);
        queue.add(4);
        queue.add(10);
        queue.add(6);
        queue.add(8);

        assertEquals(Integer.valueOf(12), queue.poll());
        assertEquals(Integer.valueOf(3), queue.poll());
    }

    @Test(expected = IllegalStateException.class)
    public void pollFromEmptyQueue() {

        QueueApiUsingHeap.QueueAdapter queue = new QueueApiUsingHeap.QueueAdapter();
        queue.poll();


    }

    @Test
    public void peekFromEmptyQueue() {
        QueueApiUsingHeap.QueueAdapter queue = new QueueApiUsingHeap.QueueAdapter();
        assertSame(null, queue.peek());
    }



}
