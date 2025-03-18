#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include "tree.h"

int main(int argc, char **argv) {
  rbt_node *root = NULL;
  rbt_print(root);

  printf("\nAdding 1-10:\n");
  for (int i = 1; i <= 10; i++) {
    rbt_insert(&root, i);
  }
  rbt_print(root);

  printf("\nAdding 10-20:\n");
  for (int i = 20; i > 10; i--) {
    rbt_insert(&root, i);
  }
  rbt_print(root);

  printf("\nAdding 22, 23, 27, 29:\n");
  rbt_insert(&root, 23);
  rbt_insert(&root, 29);
  rbt_insert(&root, 22);
  rbt_insert(&root, 27);
  rbt_print(root);

  printf("\n");
  printf("Searched for 1:  %s\n", rbt_contains(root, 1) != false ? "Found" : "Not Found");
  printf("Searched for 7:  %s\n", rbt_contains(root, 7) != false ? "Found" : "Not Found");
  printf("Searched for 13: %s\n", rbt_contains(root, 13) != false ? "Found" : "Not Found");
  printf("Searched for 18: %s\n", rbt_contains(root, 18) != false ? "Found" : "Not Found");
  printf("Searched for 24: %s\n", rbt_contains(root, 24) != false ? "Found" : "Not Found");

  printf("\nDeleting numbers between 5 and 20\n");
  for (int i = 5; i <= 20; i++) {
    rbt_remove(&root, i);
  }
  rbt_print(root);

  rbt_free(&root);

  return EXIT_SUCCESS;
}
