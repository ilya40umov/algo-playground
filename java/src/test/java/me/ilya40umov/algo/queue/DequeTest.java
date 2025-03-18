package me.ilya40umov.algo.queue;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DequeTest {

    @Test
    void enqueue_shouldAddElementsOnTheLeft() {
        var deque = new Deque<Integer>();

        IntStream.range(0, 5).forEach(deque::enqueue);

        assertEquals(Arrays.asList(4, 3, 2, 1, 0), deque.getElements());
    }

    @Test
    void dequeue_shouldRemoveElementsOnTheRight() {
        var deque = new Deque<Integer>();
        IntStream.range(0, 5).forEach(deque::enqueue);

        assertEquals(
                Arrays.asList(0, 1, 2, 3, 4),
                Stream.generate(deque::dequeue)
                        .limit(5)
                        .collect(Collectors.toList())
        );
    }


    @Test
    void addRight_shouldAddElementsOnTheRight() {
        var deque = new Deque<Integer>();

        IntStream.range(0, 5).forEach(deque::addRight);

        assertEquals(Arrays.asList(0, 1, 2, 3, 4), deque.getElements());
    }

    @Test
    void removeLeft_shouldRemoveElementsOnTheLeft() {
        var deque = new Deque<Integer>();
        IntStream.range(0, 5).forEach(deque::addLeft);

        assertEquals(
                Arrays.asList(4, 3, 2, 1, 0),
                Stream.generate(deque::removeLeft)
                        .limit(5)
                        .collect(Collectors.toList())
        );
    }

}