package com.max.algs.epi.heaps;

import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;


public class StackApiUsingHeapTest {

    @Test
    public void pushAndPop() {
        StackApiUsingHeap.StackAdapter stack = new StackApiUsingHeap.StackAdapter();

        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());

        stack.push(10);
        assertEquals(Integer.valueOf(10), stack.top());
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());

        stack.push(1);
        stack.push(5);
        assertEquals(Integer.valueOf(5), stack.pop());
        assertEquals(Integer.valueOf(1), stack.pop());

        stack.push(7);
        stack.push(12);
        stack.push(4);

        assertEquals(Integer.valueOf(4), stack.pop());
        assertEquals(Integer.valueOf(12), stack.pop());
        assertEquals(Integer.valueOf(7), stack.pop());
        assertEquals(Integer.valueOf(10), stack.pop());

        assertSame(null, stack.top());
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    @Ignore
    public void pushAndPopNoOverflow() {
        StackApiUsingHeap.StackAdapter stack = new StackApiUsingHeap.StackAdapter();

        for (long i = 0; i < (2L * Integer.MAX_VALUE) + 2L; ++i) {
            stack.push((int) i);
            stack.pop();
        }

    }

}
