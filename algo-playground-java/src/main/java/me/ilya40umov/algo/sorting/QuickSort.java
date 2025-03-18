package me.ilya40umov.algo.sorting;

public class QuickSort {
    public static int[] sort(int[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private static void quickSort(int[] array, int fromIdx, int toIdx) {
        if (fromIdx >= toIdx) {
            return;
        }
        var pivotIdx = toIdx; // XXX this can also be toIdx or a random index
        var pivotValue = array[pivotIdx];
        var leftIdx = fromIdx;
        var rightIdx = toIdx;
        while (leftIdx < rightIdx) {
            while (leftIdx < toIdx && array[leftIdx] <= pivotValue) {
                leftIdx++;
            }
            while (rightIdx > leftIdx && array[rightIdx] > pivotValue) {
                rightIdx--;
            }
            if (leftIdx < rightIdx) {
                swap(array, leftIdx, rightIdx);
                if (leftIdx == pivotIdx) {
                    pivotIdx = rightIdx;
                }
            }
        }
        if (array[leftIdx] < pivotValue) {
            swap(array, leftIdx, pivotIdx);
        }
        quickSort(array, fromIdx, leftIdx - 1);
        quickSort(array, leftIdx, toIdx);
    }

    private static void swap(int[] array, int index1, int index2) {
        var tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }
}
