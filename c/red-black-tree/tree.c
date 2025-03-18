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

static const char *HLINE = "─";
static const char *VLINE = "│";
static const char *HLINE_BRANCH = "┬";
static const char *VLINE_END = "└";

static void rbt_print_rec(rbt_node *node, const char *prefix) {
  if (node == NULL) {
    printf("%sNULL", HLINE);
    return;
  }

  printf("%s", HLINE);
  int value_len;
  printf("%d%n", node->value, &value_len);
  printf("%s%s", HLINE, HLINE_BRANCH);

  size_t ext_prefix_size = strlen(prefix) + 3 + value_len + strlen(VLINE) + 1;
  char *ext_prefix = malloc(sizeof(char) * ext_prefix_size);

  snprintf(ext_prefix, ext_prefix_size, "%s%*s%s", prefix, 3 + value_len - 1, "", VLINE);
  rbt_print_rec(node->left, ext_prefix);

  printf("\n%s%*s%s", prefix, 3 + value_len - 1, " ", VLINE_END);

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

bool rbt_insert(rbt_node **root, int value) {
  if (*root == NULL) {
    *root = calloc(1, sizeof(rbt_node));
    if (*root == NULL) {
      return false;
    }
    (*root)->value = value;
    return true;
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
  if (*root == NULL) {
    return false;
  }
  rbt_node **current_node_ptr = root;
  rbt_node *current_node = *current_node_ptr;
  if (value < current_node->value) {
    return rbt_remove(&current_node->left, value); 
  } else if (value > current_node->value) {
    return rbt_remove(&current_node->right, value);
  } else {
    if (current_node->left != NULL) {
      current_node->value = current_node->left->value;
      return rbt_remove(&current_node->left, current_node->value);
    } else if (current_node->right != NULL) {
      current_node->value = current_node->right->value;
      return rbt_remove(&current_node->right, current_node->value);
    } else {
      free(current_node);
      *current_node_ptr = NULL;
      return true;
    }
  }
}

void rbt_free_tree(rbt_node **root) {
  if (*root == NULL) {
    return;
  }
  rbt_node **current_node_ptr = root;
  rbt_node *current_node = *current_node_ptr;
  if (current_node->left != NULL) {
    rbt_free_tree(&current_node->left);
  }
  if (current_node->right != NULL) {
    rbt_free_tree(&current_node->right);
  }
  free(current_node);
  *current_node_ptr = NULL;
}
