package me.ilya40umov.algo.sorting;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public abstract class BaseSortTest {

    private final Function<int[], int[]> sort;

    protected BaseSortTest(Function<int[], int[]> sort) {
        this.sort = sort;
    }

    @Test
    void sort_shouldReturnSortedArray_whenGivenUnsortedArrayOfEvenLength() {
        assertArrayEquals(
                new int[]{1, 1, 1, 2, 2, 2, 3, 3},
                sort.apply(new int[]{1, 2, 3, 1, 1, 3, 2, 2})
        );
    }

    @Test
    void sort_shouldReturnSortedArray_whenGivenUnsortedArrayOfOddLength() {
        assertArrayEquals(
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9},
                sort.apply(new int[]{3, 4, 9, 5, 1, 2, 8, 7, 6})
        );
    }

    @Test
    void sort_shouldReturnSortedArray_whenGivenSortedArray() {
        assertArrayEquals(
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9},
                sort.apply(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9})
        );
    }

    @Test
    void sort_shouldReturnProvidedArray_whenGivenArrayOfSizeOne() {
        assertArrayEquals(
                new int[]{},
                sort.apply(new int[]{})
        );
    }

    @Test
    void sort_shouldReturnProvidedArray_whenGivenEmptyArray() {
        assertArrayEquals(
                new int[]{1},
                sort.apply(new int[]{1})
        );
    }
}
