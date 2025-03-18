package me.ilya40umov.algo.search;

import java.util.NoSuchElementException;
import java.util.Optional;

public class BinarySearch {
    public static Optional<Integer> search(int[] array, int value) throws NoSuchElementException {
        if (array.length == 0) {
            return Optional.empty();
        }
        var startIdx = 0;
        var endIdx = array.length - 1;
        while (endIdx > startIdx) {
            var midIdx = (endIdx + startIdx) / 2;
            if (array[midIdx] == value) {
                return Optional.of(midIdx);
            }
            if (array[midIdx] < value) {
                startIdx = midIdx + 1;
            } else {
                endIdx = midIdx - 1;
            }
        }
        if (startIdx == endIdx && array[startIdx] == value) {
            return Optional.of(startIdx);
        }
        return Optional.empty();
    }
}
