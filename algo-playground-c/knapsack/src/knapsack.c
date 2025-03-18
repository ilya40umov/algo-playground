#include "knapsack.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int SOLVE_VERBOSE = 0;

typedef struct {
  knapsack_item **items;
  size_t size;
  unsigned int weight;
  unsigned int value;
} knapsack;

void add_item_to_knapsack(knapsack *k, knapsack_item *item) {
  if (k->size == 0) {
    k->items = malloc(sizeof(knapsack_item *));
  } else {
    k->items = realloc(k->items, sizeof(knapsack_item *) * (k->size + 1));
  }
  k->items[k->size] = item;
  k->size += 1;
  k->weight += item->weight;
  k->value += item->value;
}

void add_items_to_knapsack(knapsack *to, knapsack *from) {
  if (from->size == 0) {
    return;
  }
  if (to->size == 0) {
    to->items = malloc(sizeof(knapsack_item *) * from->size);
  } else {
    to->items =
        realloc(to->items, sizeof(knapsack_item *) * (to->size + from->size));
  }
  memcpy(to->items + to->size, from->items,
         sizeof(knapsack_item *) * from->size);
  to->size += from->size;
  to->weight += from->weight;
  to->value += from->value;
}

size_t solve_knapsack(knapsack_item *available_items, size_t size,
                      unsigned int max_weight, knapsack_item ***result) {
  if (size == 0) {
    *result = NULL;
    return 0;
  }

  knapsack solutions[size][max_weight];
  memset(solutions, 0, sizeof(knapsack) * size * max_weight);

  for (int i = 0; i < size; ++i) {
    knapsack_item *item = available_items + i;
    for (int w = 0; w < max_weight; ++w) {
      unsigned int weight = w + 1;
      if (i == 0) {
        if (item->weight <= weight) {
          add_item_to_knapsack(&solutions[i][w], item);
        }
        continue;
      }
      unsigned int prev_best_value = solutions[i - 1][w].value;
      if (item->weight == weight && item->value > prev_best_value) {
        add_item_to_knapsack(&solutions[i][w], item);
        continue;
      }
      if (item->weight < weight &&
          item->value + solutions[i - 1][weight - item->weight - 1].value >
              prev_best_value) {
        add_items_to_knapsack(&solutions[i][w],
                              &solutions[i - 1][weight - item->weight - 1]);
        add_item_to_knapsack(&solutions[i][w], item);
        continue;
      }
      add_items_to_knapsack(&solutions[i][w], &solutions[i - 1][w]);
    }
  }

  if (SOLVE_VERBOSE) {
    printf("   ");
    for (int w = 0; w < max_weight; ++w) {
      printf("%02u ", w);
    }
    printf("\n");
    for (int i = 0; i < size; ++i) {
      printf("%s: ", available_items[i].name);
      for (int w = 0; w < max_weight; ++w) {
        printf("%02u ", solutions[i][w].value);
      }
      printf("\n");
    }
    printf("\n");
  }

  // free up memory used for intermediate solutions
  for (int i = 0; i < size; ++i) {
    for (int w = 0; w < max_weight; ++w) {
      if (i == size - 1 && w == max_weight - 1) {
        continue;
      }
      free(solutions[i][w].items);
    }
  }

  knapsack *solution = &solutions[size - 1][max_weight - 1];
  *result = solution->items;
  return solution->size;
}
