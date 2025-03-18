#ifndef TREE_H
#define TREE_H
#include <stdbool.h>

enum rbt_node_color {BLACK, RED};

typedef struct rbt_node rbt_node;

struct rbt_node {
  int value;
  enum rbt_node_color color; 
  rbt_node *left;
  rbt_node *right;
};

bool rbt_contains(rbt_node *root, int value);

void rbt_print_tree(rbt_node *root);

bool rbt_insert(rbt_node **root, int value);

bool rbt_remove(rbt_node **root, int value);

void rbt_free_tree(rbt_node **root);
 
#endif

