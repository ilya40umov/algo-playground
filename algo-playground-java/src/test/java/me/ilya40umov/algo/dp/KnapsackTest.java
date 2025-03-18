package me.ilya40umov.algo.dp;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KnapsackTest {

    @Test
    void findMaxPossibleCost_shouldReturnZero_ifNoSolutionExists() {
        assertEquals(
                0,
                Knapsack.findMaxPossibleCost(
                        List.of(new Knapsack.Item(15, 15)),
                        5
                )
        );
    }

    @Test
    void findMaxPossibleCost_shouldReturnMaxCost_ifSolutionExists() {
        assertEquals(
                16,
                Knapsack.findMaxPossibleCost(
                        List.of(
                                new Knapsack.Item(3, 5),
                                new Knapsack.Item(5, 6),
                                new Knapsack.Item(1, 7),
                                new Knapsack.Item(6, 3),
                                new Knapsack.Item(2, 3)
                        ),
                        8
                )
        );
    }

}