package com.max.epi.heaps;


import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;


/**
 * 11.6. Compute the K largest elements in a max-heap.
 */
public class LargestElementInMaxHeap {

    private static final Logger LOG = Logger.getLogger(LargestElementInMaxHeap.class);

    private static final int[] EMPTY_ARR = new int[0];

    private LargestElementInMaxHeap() {

        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        maxHeap.add(10);

        maxHeap.add(6);
        maxHeap.add(8);

        maxHeap.add(3);
        maxHeap.add(4);
        maxHeap.add(7);
        maxHeap.add(5);

        maxHeap.add(1);
        maxHeap.add(2);
        maxHeap.add(3);
        maxHeap.add(2);
        maxHeap.add(6);
        maxHeap.add(5);


        try {
            /*
             * Access array field for PriorityQueue using reflection, otherwise
             * we need to use 'maxHeap.toArray(new Integer[0])', but this will add space complexity O(N).
            */
            Field arrField = PriorityQueue.class.getDeclaredField("queue");
            arrField.setAccessible(true);

            int k = 5;
            int[] arr = getLargestElements((Object[]) arrField.get(maxHeap), maxHeap.size(), k);

            LOG.info(Arrays.toString(arr));
        }
        catch (IllegalAccessException | NoSuchFieldException ex) {
            LOG.error(ex.getMessage(), ex);
        }

        LOG.info("'OnlineMedian' completed. java-" + System.getProperty("java.version"));
    }

    /**
     * time: O(K*lgK)
     * space: O(K)
     */
    private static int[] getLargestElements(Object[] maxHeapArr, int totalElementsCount, int k) {
        checkNotNull(maxHeapArr);
        checkArgument(totalElementsCount <= maxHeapArr.length);
        checkArgument(k >= 0);

        if (k == 0 || totalElementsCount == 0) {
            return EMPTY_ARR;
        }

        int[] largest = new int[Math.min(k, totalElementsCount)];

        Queue<HeapEntry> candidates = new PriorityQueue<>(k, Collections.reverseOrder());

        checkState(maxHeapArr.length > 0 && totalElementsCount > 0, "Empty array handled incorrectly");

        candidates.add(new HeapEntry(maxHeapArr, 0));

        for (int i = 0; i < largest.length; ++i) {

            checkState(!candidates.isEmpty(), "empty 'candidates' max-heap detected");

            HeapEntry curEntry = candidates.poll();

            largest[i] = curEntry.getValue();

            int child1 = 2 * curEntry.getIndex() + 1;
            int child2 = 2 * curEntry.getIndex() + 2;

            if (child1 < totalElementsCount) {
                candidates.add(new HeapEntry(maxHeapArr, child1));
            }

            if (child2 < totalElementsCount) {
                candidates.add(new HeapEntry(maxHeapArr, child2));
            }
        }

        checkState(largest.length == Math.min(k, maxHeapArr.length), "Incorrect number of elements selected");

        return largest;
    }

    public static void main(String[] args) {
        try {
            new LargestElementInMaxHeap();
        }
        catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    private static final class HeapEntry implements Comparable<HeapEntry> {

        final Object[] arr;
        final int index;

        HeapEntry(Object[] arr, int index) {
            this.arr = arr;
            this.index = index;
        }

        Integer getValue() {
            return (Integer) arr[index];
        }

        int getIndex() {
            return index;
        }

        @Override
        public int compareTo(HeapEntry other) {
            return getValue().compareTo(other.getValue());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            HeapEntry heapEntry = (HeapEntry) o;

            return Objects.equals(getValue(), heapEntry.getValue());
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(getValue());
        }

        @Override
        public String toString() {
            return String.valueOf(getValue());
        }
    }

}
