#ifndef TREE_H
#define TREE_H

#include <stdbool.h>

typedef struct rbt_node rbt_node;

bool rbt_is_valid(rbt_node *root);

bool rbt_contains(rbt_node *root, int value);

void rbt_print(rbt_node *root);

bool rbt_insert(rbt_node **root_ptr, int value);

bool rbt_remove(rbt_node **root_ptr, int value);

void rbt_free(rbt_node **root_ptr);

#endif
