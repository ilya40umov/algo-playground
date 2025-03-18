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
    void sort_shouldReturnSortedArray_whenGivenUnsortedArray() {
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
    void sort_shouldReturnEmptyArray_whenGivenEmptyArray() {
        assertArrayEquals(
                new int[]{},
                sort.apply(new int[]{})
        );
    }
}
