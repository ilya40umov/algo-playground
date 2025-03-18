package me.ilya40umov.algo.queue;

public class RingBuffer<T> {

    private final static int NO_VALUE = -1;

    private final T[] elements;
    private int nextReadIndex = NO_VALUE;
    private int lastWriteIndex = NO_VALUE;

    public RingBuffer(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        //noinspection unchecked
        this.elements = (T[]) new Object[capacity];
    }

    public boolean enqueue(T element) {
        var writeIndex = getWriteIndex();
        if (writeIndex == NO_VALUE) {
            return false;
        }
        elements[writeIndex] = element;
        lastWriteIndex = writeIndex;
        if (nextReadIndex == NO_VALUE) {
            nextReadIndex = lastWriteIndex;
        }
        return true;
    }

    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        var element = elements[nextReadIndex];
        if (nextReadIndex == lastWriteIndex) {
            nextReadIndex = NO_VALUE;
            lastWriteIndex = NO_VALUE;
        } else {
            nextReadIndex++;
            if (nextReadIndex == elements.length) {
                nextReadIndex = 0;
            }
        }
        return element;
    }

    boolean isEmpty() {
        return nextReadIndex == NO_VALUE;
    }

    private int getWriteIndex() {
        if (isEmpty()) {
            return 0;
        }
        var writeIndex = lastWriteIndex + 1;
        if (writeIndex == elements.length) {
            writeIndex = 0;
        }
        if (writeIndex == nextReadIndex) {
            return NO_VALUE; // queue is full
        }
        return writeIndex;
    }
}
