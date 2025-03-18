#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include "tree.h"

// 
// Internal helper functions (definitions)
//

static bool _rbt_contains(rbt_node *node, int value);
static void _rbt_print_tree(rbt_node *node, const char *prefix);
static bool _rbt_insert(rbt_node **node_ptr, rbt_node *parent, int value);
static bool _rbt_remove(rbt_node **node_ptr, int value);
static void _rbt_free_tree(rbt_node **node_ptr);

//
// Exposed red-black tree functions 
//

bool rbt_contains(rbt_node *root, int value) {
  return _rbt_contains(root, value);
}

void rbt_print_tree(rbt_node *root) {
  printf("\nTree:\n");
  _rbt_print_tree(root, "");
  printf("\n");
}

bool rbt_insert(rbt_node **root_ptr, int value) {
  return _rbt_insert(root_ptr, NULL, value);
}

bool rbt_remove(rbt_node **root_ptr, int value) {
  return _rbt_remove(root_ptr, value);
}

void rbt_free_tree(rbt_node **root_ptr) {
  if (*root_ptr == NULL) {
    return;
  }
  rbt_node **current_node_ptr = root_ptr;
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

// 
// Internal helper functions (implementations)
//

static bool _rbt_contains(rbt_node *node, int value) {
  if (node == NULL) {
    return false;
  }
  if (value == node->value) {
    return true;
  } else if (value < node->value) {
    return rbt_contains(node->left, value); 
  } else {
    return rbt_contains(node->right, value);
  }
}

static const char *HLINE = "─";
static const char *VLINE = "│";
static const char *HLINE_BRANCH = "┬";
static const char *VLINE_END = "└";
static const char *RED_COLOR = "\033[0;31m";
static const char *WHITE_COLOR = "\033[0;37m";

static void _rbt_print_tree(rbt_node *node, const char *prefix) {
  if (node == NULL) {
    printf("%sNULL", HLINE);
    return;
  }

  printf("%s%s", HLINE, node->color == RED ? RED_COLOR : WHITE_COLOR);
  int value_len;
  printf("%d%n", node->value, &value_len);
  printf("%s%s%s", WHITE_COLOR, HLINE, HLINE_BRANCH);

  size_t ext_prefix_size = strlen(prefix) + 3 + value_len + strlen(VLINE) + 1;
  char *ext_prefix = malloc(sizeof(char) * ext_prefix_size);

  snprintf(ext_prefix, ext_prefix_size, "%s%*s%s", prefix, 3 + value_len - 1, "", VLINE);
  _rbt_print_tree(node->left, ext_prefix);

  printf("\n%s%*s%s", prefix, 3 + value_len - 1, " ", VLINE_END);

  snprintf(ext_prefix, ext_prefix_size, "%s%*s", prefix, 3 + value_len, "");
  _rbt_print_tree(node->right, ext_prefix);

  free(ext_prefix);
}

static bool _rbt_insert(rbt_node **node_ptr, rbt_node *parent, int value) {
  rbt_node *node;
  if (*node_ptr == NULL) {
    *node_ptr = calloc(1, sizeof(rbt_node));
    if (*node_ptr == NULL) {
      return false;
    }
    node = *node_ptr;
    node->value = value;
    node->parent = parent;
    node->color = parent != NULL && parent->color == BLACK ? RED : BLACK;
    return true;
  }
  node = *node_ptr;
  if (value == node->value) {
    return node;
  } else if (value < node->value) {
    return _rbt_insert(&node->left, node, value); 
  } else {
    return _rbt_insert(&node->right, node, value);
  }
}

static bool _rbt_remove(rbt_node **node_ptr, int value) {
  if (*node_ptr == NULL) {
    return false;
  }
  rbt_node *node = *node_ptr;
  if (value < node->value) {
    return _rbt_remove(&node->left, value); 
  } else if (value > node->value) {
    return _rbt_remove(&node->right, value);
  } else {
    if (node->left != NULL) {
      node->value = node->left->value;
      return _rbt_remove(&node->left, node->value);
    } else if (node->right != NULL) {
      node->value = node->right->value;
      return _rbt_remove(&node->right, node->value);
    } else {
      free(node);
      *node_ptr = NULL;
      return true;
    }
  }
}

static void _rbt_free_tree(rbt_node **node_ptr) {
  if (*node_ptr == NULL) {
    return;
  }
  rbt_node *node = *node_ptr;
  if (node->left != NULL) {
    rbt_free_tree(&node->left);
  }
  if (node->right != NULL) {
    rbt_free_tree(&node->right);
  }
  free(node);
  *node_ptr = NULL;
}
