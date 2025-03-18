package me.ilya40umov.algo.dp;

import java.util.List;

public class Knapsack {
    public record Item(int weight, int cost) {
    }

    public static int findMaxPossibleCost(List<Item> items, int maxWeight) {
        /* A(3kg, 5$), B(5kg, 6$), C(1kg, 7$), D(6kg, 3$), E(2, 3$), max weight: 8kg => B,C,E (6$+7$+3$ = 16$)
            1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 |
        A | 0$| 0$| 5$| 5$| 5$| 5$| 5$| 5$|
        B | 0$| 0$| 5$| 5$| 6$| 6$| 6$| 6$|
        C | 7$| 7$| 7$|12$|12$|13$|13$|13$|
        D | 7$| 7$| 7$|12$|12$|13$|13$|13$|
        E | 7$| 7$|10$|12$|12$|15$|16$|16$| => 16$
         */
        var grid = new int[items.size() + 1][maxWeight + 1];
        for (int curItemIdx = 1; curItemIdx < grid.length; curItemIdx++) {
            var item = items.get(curItemIdx - 1);
            for (int curMaxWeight = 1; curMaxWeight < grid[0].length; curMaxWeight++) {
                var maxCost = grid[curItemIdx - 1][curMaxWeight];
                if (item.weight <= curMaxWeight) {
                    var potentialNewCost = item.cost + grid[curItemIdx - 1][curMaxWeight - item.weight];
                    if (potentialNewCost > maxCost) {
                        maxCost = potentialNewCost;
                    }
                }
                grid[curItemIdx][curMaxWeight] = maxCost;
            }
        }
        return grid[items.size()][maxWeight];
    }
}
