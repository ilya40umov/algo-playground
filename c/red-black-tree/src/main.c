#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include "tree.h"

int main(int argc, char **argv) {
  rbt_node *root = NULL;
  printf("Starting with:\n");
  rbt_print(root);

  printf("\nAdding 1-10...\n");
  for (int i = 1; i <= 10; i++) {
    rbt_insert(&root, i);
  }

  printf("Adding 10-20...\n");
  for (int i = 20; i > 10; i--) {
    rbt_insert(&root, i);
  }

  printf("Adding 22, 23, 27, 29...\n");
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

  printf("Removing a childless red node (11).\n");
  rbt_remove(&root, 11);
  printf("Removing a black node with a single red child (20).\n");
  rbt_remove(&root, 20);
  printf("Removing a black node with two children (19).\n");
  rbt_remove(&root, 19);
  printf("Removing the root (8).\n");
  rbt_remove(&root, 8);
  printf("Removing the numbers from 1 to 7.\n");
  for (int i = 1; i <= 7; ++i) {
    rbt_remove(&root, i); 
  }
  rbt_print(root);

  rbt_free(&root);

  return EXIT_SUCCESS;
}
