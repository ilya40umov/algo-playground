#include "knapsack.h"
#include <stdio.h>
#include <stdlib.h>

int main() {
  knapsack_item items[] = {
      {"A", 3, 10}, {"B", 2, 15}, {"C", 5, 30},
      {"D", 4, 30}, {"E", 2, 20}, {"F", 1, 15},
  };
  size_t items_size = sizeof(items) / sizeof(knapsack_item);

  printf("Available items:\n");
  for (int i = 0; i < items_size; ++i) {
    knapsack_item *item = &items[i];
    printf("Name: %s, weight: %u, value: %u\n", item->name, item->weight,
           item->value);
  }

  unsigned int max_weight = 5;
  printf("\nKnapsack max weight: %u\n\n", max_weight);

  SOLVE_VERBOSE = 1;
  knapsack_item **result;
  size_t result_size = solve_knapsack(items, items_size, max_weight, &result);

  if (result_size == 0) {
    printf("Solution: None\n");
    return 0;
  }

  printf("Solution: ");
  unsigned int total_weight = 0, total_value = 0;
  for (int i = 0; i < result_size; ++i) {
    knapsack_item *item = result[i];
    printf("%s ", item->name);
    total_weight += item->weight;
    total_value += item->value;
  }
  printf("\nTotal weight: %u, total value: %u\n", total_weight, total_value);

  free(result);

  return 0;
}
