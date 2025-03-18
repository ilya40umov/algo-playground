package me.ilya40umov.algo.sorting;

import java.util.Arrays;

public class MergeSort {
    public static int[] sort(int[] array) {
        if (array.length == 0) {
            return array;
        }
        sortSlice(array, 0, array.length);
        return array;
    }

    private static void sortSlice(int[] array, int start, int end) {
        if (end - start <= 1) {
            return;
        }

        var middle = start + (end - start) / 2;
        sortSlice(array, start, middle);
        sortSlice(array, middle, end);
        mergeSlices(array, start, end);
    }

    private static void mergeSlices(int[] array, int start, int end) {
        var rangeToMerge = Arrays.copyOfRange(array, start, end);
        var middleInRange = (end - start) / 2;
        int leftIdx = 0, rightIdx = middleInRange, targetIdx = start;
        while (leftIdx < middleInRange && rightIdx < rangeToMerge.length) {
            if (rangeToMerge[leftIdx] < rangeToMerge[rightIdx]) {
                array[targetIdx++] = rangeToMerge[leftIdx++];
            } else {
                array[targetIdx++] = rangeToMerge[rightIdx++];
            }
        }
        while (leftIdx < middleInRange) {
            array[targetIdx++] = rangeToMerge[leftIdx++];
        }
        while (rightIdx < rangeToMerge.length) {
            array[targetIdx++] = rangeToMerge[rightIdx++];
        }
    }

}
