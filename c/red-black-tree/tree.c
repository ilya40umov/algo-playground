#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include "tree.h"

bool rbt_contains(rbt_node *root, int value) {
  if (root == NULL) {
    return false;
  }
  rbt_node *current_node = root;
  if (value == current_node->value) {
    return true;
  } else if (value < current_node->value) {
    return rbt_contains(current_node->left, value); 
  } else {
    return rbt_contains(current_node->right, value);
  }
}

static const char *no_prefix = "";
static const char *ext_ascii = "│";

static void rbt_print_rec(rbt_node *node, const char *prefix) {
  if (node == NULL) {
    printf("─NULL");
    return;
  }

  printf("─");
  int value_len;
  printf("%d%n", node->value, &value_len);
  printf("─┬");

  size_t ext_prefix_size = strlen(prefix) + 3 + value_len + strlen(ext_ascii) + 1;
  char *ext_prefix = malloc(sizeof(char) * ext_prefix_size);

  snprintf(ext_prefix, ext_prefix_size, "%s%*s│", prefix, 3 + value_len - 1, "");
  rbt_print_rec(node->left, ext_prefix);

  printf("\n%s%*s└", prefix, 3 + value_len - 1, " ");

  snprintf(ext_prefix, ext_prefix_size, "%s%*s", prefix, 3 + value_len, "");
  rbt_print_rec(node->right, ext_prefix);

  free(ext_prefix);
}

void rbt_print_tree(rbt_node *root) {
  if (root == NULL) {
    printf("\nTree: Empty\n");
    return;
  }
  printf("\nTree:\n");
  rbt_print_rec(root, "");
  printf("\n");
}

rbt_node *rbt_insert(rbt_node **root, int value) {
  if (*root == NULL) {
    *root = calloc(1, sizeof(rbt_node));
    (*root)->value = value;
    return *root;
  }
  rbt_node *current_node = *root;
  if (value == current_node->value) {
    return current_node;
  } else if (value < current_node->value) {
    return rbt_insert(&current_node->left, value); 
  } else {
    return rbt_insert(&current_node->right, value);
  }
}

bool rbt_remove(rbt_node **root, int value) {
  return false;
  //if (*root == NULL) {
  //  return false;
  //}
  //rbt_node *current_node = *root;
  //if (value == current_node->value) {
  //  return current_node;
  //} else if (value < current_node->value) {
  //  return rbt_insert(&current_node->left, value); 
  //} else {
  //  return rbt_insert(&current_node->right, value);
  //}
  //return NULL;
}
