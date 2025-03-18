package me.ilya40umov.algo.sorting;

public class HeapSort {
    public static int[] sort(int[] array) {
        var heapSize = 1;
        while (heapSize < array.length) {
            heapSize += 1;
            var elemIdx = heapSize - 1;
            while (elemIdx != 0) {
                var parentIdx = (elemIdx - 1) / 2;
                if (array[elemIdx] > array[parentIdx]) {
                    var temp = array[elemIdx];
                    array[elemIdx] = array[parentIdx];
                    array[parentIdx] = temp;
                    elemIdx = parentIdx;
                    continue;
                }
                break;
            }
        }
        while (heapSize > 1) {
            var temp = array[0];
            array[0] = array[heapSize - 1];
            array[heapSize - 1] = temp;
            heapSize -= 1;
            var elemIdx = 0;
            while (true) {
                var lChildIdx = elemIdx * 2 + 1;
                var rChildIdx = elemIdx * 2 + 2;
                if (rChildIdx < heapSize && array[rChildIdx] > array[elemIdx] && array[rChildIdx] > array[lChildIdx]) {
                    temp = array[elemIdx];
                    array[elemIdx] = array[rChildIdx];
                    array[rChildIdx] = temp;
                    elemIdx = rChildIdx;
                    continue;
                }
                if (lChildIdx < heapSize && array[lChildIdx] > array[elemIdx]) {
                    temp = array[elemIdx];
                    array[elemIdx] = array[lChildIdx];
                    array[lChildIdx] = temp;
                    elemIdx = lChildIdx;
                    continue;
                }
                break;
            }
        }
        return array;
    }
}
