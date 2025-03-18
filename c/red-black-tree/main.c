#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include "tree.h"

int main(int argc, char **argv) {
  rbt_node *root = NULL;
  rbt_insert(&root, 7);
  rbt_insert(&root, 2);
  rbt_insert(&root, 3);
  rbt_insert(&root, 1);
  rbt_insert(&root, 9);
  rbt_insert(&root, 5);
  rbt_insert(&root, 8);
  rbt_insert(&root, 6);
  rbt_insert(&root, 12);
  rbt_print_tree(root);

  printf("\n");
  printf("Searched for 1:  %s\n", rbt_contains(root, 1) != false ? "Found" : "Not Found");
  printf("Searched for 10: %s\n", rbt_contains(root, 10) != false ? "Found" : "Not Found");
  printf("Searched for 12: %s\n", rbt_contains(root, 12) != false ? "Found" : "Not Found");

  printf("\n");
  printf("Deleted 1, 5, 9\n");
  rbt_remove(&root, 1);
  rbt_remove(&root, 5);
  rbt_remove(&root, 9);
  rbt_print_tree(root);

  return EXIT_SUCCESS;
}
