package me.ilya40umov.algo.heap;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaxHeapTest {

    @Test
    void shouldMaintainHeapProperties() {
        var maxHeap = new MaxHeap();
        maxHeap.add(5);
        maxHeap.add(7);
        maxHeap.add(3);
        maxHeap.add(1);
        maxHeap.add(6);
        maxHeap.add(8);
        maxHeap.add(2);
        maxHeap.add(4);
        maxHeap.add(9);

        var values = new ArrayList<Integer>();
        while (!maxHeap.isEmpty()) {
            values.add(maxHeap.remove());
        }

        assertEquals(Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1), values);
    }

}