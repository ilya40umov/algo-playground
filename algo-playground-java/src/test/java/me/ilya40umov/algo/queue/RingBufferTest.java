package me.ilya40umov.algo.queue;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RingBufferTest {

    @Test
    void enqueue_shouldReturnTrue_whenQueueIsNotFull() {
        var buffer = new RingBuffer<Integer>(5);

        assertTrue(buffer.enqueue(1));
    }

    @Test
    void enqueue_shouldReturnFalse_whenQueueIsFull() {
        var buffer = new RingBuffer<Integer>(5);

        IntStream.range(0, 5).forEach(buffer::enqueue);

        assertFalse(buffer.enqueue(1));
    }

    @Test
    void enqueue_shouldCauseQueueToBecomeNonEmpty() {
        var buffer = new RingBuffer<Integer>(5);

        buffer.enqueue(123);

        assertFalse(buffer.isEmpty());
    }

    @Test
    void dequeue_shouldReturnNextElement_whenQueueHasData() {
        var buffer = new RingBuffer<Integer>(5);
        buffer.enqueue(123);

        assertEquals(123, buffer.dequeue());
    }

    @Test
    void dequeue_shouldReturnNull_whenQueueHasNoData() {
        var buffer = new RingBuffer<Integer>(5);

        assertNull(buffer.dequeue());
    }

    @Test
    void dequeue_shouldReturnAllAddedElements_whenCalledRepeatedly() {
        var buffer = new RingBuffer<Integer>(5);
        IntStream.range(0, 5).forEach(buffer::enqueue);

        assertEquals(
                Arrays.asList(0, 1, 2, 3, 4),
                Stream.generate(buffer::dequeue)
                        .limit(5)
                        .collect(Collectors.toList())
        );
    }

    @Test
    void dequeue_shouldLeaveQueueInEmptyState_whenCalledEnoughTimes() {
        var buffer = new RingBuffer<Integer>(5);

        IntStream.range(0, 3).forEach(buffer::enqueue);
        IntStream.range(0, 3).forEach(i -> buffer.dequeue());

        assertTrue(buffer.isEmpty());
    }

}