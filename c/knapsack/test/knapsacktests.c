#include <criterion/criterion.h>
#include <criterion/new/assert.h>

#include "../src/knapsack.h"

static knapsack_item **result = NULL;

void teardown(void) {
  if (result == 0) {
    free(result);
  }
}

Test(knapsacktests, solve_knapsack_finds_optimum, .fini = teardown) {
  knapsack_item items[] = {
      {"A", 3, 10}, {"B", 2, 15}, {"C", 5, 30},
      {"D", 4, 30}, {"E", 2, 20}, {"F", 1, 15},
  };
  size_t items_size = sizeof(items) / sizeof(knapsack_item);
  unsigned int max_weight = 5;

  size_t result_size = solve_knapsack(items, items_size, max_weight, &result);

  cr_expect(eq(result_size, 3),
            "solve_knapsack should return correct result size");
  cr_expect(eq(str, result[0]->name, "B"),
            "first solution element should be B");
  cr_expect(eq(str, result[1]->name, "E"),
            "second solution element should be E");
  cr_expect(eq(str, result[2]->name, "F"),
            "third solution element should be F");
}

Test(knapsacktests, solve_knapsack_if_nothing_fits, .fini = teardown) {
  knapsack_item items[] = {{"A", 6, 10}, {"B", 7, 15}, {"C", 8, 30}};
  size_t items_size = sizeof(items) / sizeof(knapsack_item);
  unsigned int max_weight = 5;

  size_t result_size = solve_knapsack(items, items_size, max_weight, &result);

  cr_expect(eq(result_size, 0),
            "solve_knapsack should return correct result size");
}

Test(knapsacktests, solve_knapsack_if_everything_fits, .fini = teardown) {
  knapsack_item items[] = {{"A", 1, 10}, {"B", 2, 15}, {"C", 3, 30}};
  size_t items_size = sizeof(items) / sizeof(knapsack_item);
  unsigned int max_weight = 6;

  size_t result_size = solve_knapsack(items, items_size, max_weight, &result);

  cr_expect(eq(result_size, items_size),
            "solve_knapsack should return correct result size");
  cr_expect(eq(str, result[0]->name, "A"),
            "first solution element should be A");
  cr_expect(eq(str, result[1]->name, "B"),
            "second solution element should be B");
  cr_expect(eq(str, result[2]->name, "C"),
            "third solution element should be C");
}

Test(knapsacktests, solve_knapsack_if_no_items, .fini = teardown) {
  knapsack_item items[] = {};
  size_t items_size = sizeof(items) / sizeof(knapsack_item);
  unsigned int max_weight = 6;

  size_t result_size = solve_knapsack(items, items_size, max_weight, &result);

  cr_expect(eq(result_size, 0),
            "solve_knapsack should return 0 when no items available");
}
