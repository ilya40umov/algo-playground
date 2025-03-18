package me.ilya40umov.algo.heap;

import java.util.Arrays;

public class MaxHeap {

    private int[] elements;
    private int size;

    public MaxHeap() {
        elements = new int[10];
        size = 0;
    }

    public void add(int element) {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
        elements[size] = element;
        size++;
        var elementIndex = size - 1;
        while (!isRootIndex(elementIndex)) {
            var parentIndex = getParentIndex(elementIndex);
            if (elements[elementIndex] > elements[parentIndex]) {
                swap(elementIndex, parentIndex);
                elementIndex = parentIndex;
                continue;
            }
            break;
        }
    }

    public int remove() {
        var result = elements[0];
        elements[0] = elements[size - 1];
        elements[size - 1] = 0; // zeroing out the removed element for better debuggability
        size--;
        var elementIndex = 0;
        while (true) {
            var leftChildIndex = getLeftChildIndex(elementIndex);
            var rightChildIndex = getRightChildIndex(elementIndex);
            if (
                    rightChildIndex < size &&
                            elements[rightChildIndex] > elements[elementIndex] &&
                            elements[rightChildIndex] > elements[leftChildIndex]
            ) {
                swap(elementIndex, rightChildIndex);
                elementIndex = rightChildIndex;
                continue;
            }
            if (leftChildIndex < size && elements[leftChildIndex] > elements[elementIndex]) {
                swap(elementIndex, leftChildIndex);
                elementIndex = leftChildIndex;
                continue;
            }
            break;
        }
        return result;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isRootIndex(int index) {
        return index == 0;
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private int getLeftChildIndex(int index) {
        return index * 2 + 1;
    }

    private int getRightChildIndex(int index) {
        return index * 2 + 2;
    }

    private void swap(int index1, int index2) {
        var temp = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = temp;
    }

}
