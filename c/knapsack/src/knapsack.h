#ifndef KNAPSACK_H
#define KNAPSACK_H

#include <stdlib.h>

extern int SOLVE_VERBOSE;

typedef struct knapsack_item {
  char *name;
  unsigned int weight;
  unsigned int value;
} knapsack_item;

size_t solve_knapsack(knapsack_item *available_items, size_t size,
                      unsigned int max_weight, knapsack_item ***result);

#endif
