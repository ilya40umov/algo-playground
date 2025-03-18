package me.ilya40umov.algo.search;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchTest {

    @Test
    void search_shouldReturnIndexOfElementInSortedArray_whenArraySizeIsEven() {
        var array = new int[]{1, 2, 3, 4, 5, 6, 7, 8};

        for (int value : array) {
            assertEquals(Optional.of(value - 1), BinarySearch.search(array, value));
        }
    }

    @Test
    void search_shouldReturnIndexOfElementInSortedArray_whenArraySizeIsOdd() {
        var array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

        for (int value : array) {
            assertEquals(Optional.of(value - 1), BinarySearch.search(array, value));
        }
    }

    @Test
    void search_shouldReturnEmptyOptional_whenValueNotPresent() {
        var array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

        assertEquals(Optional.empty(), BinarySearch.search(array, 11));
    }

}