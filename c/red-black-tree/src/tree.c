#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include "tree.h"

enum rbt_node_color {BLACK, RED};

struct rbt_node {
  int value;
  enum rbt_node_color color;
  rbt_node *parent, *left, *right;
};

// 
// Internal helper functions (definitions)
//

static bool _rbt_is_subtree_valid(rbt_node *node, int encountered_blacks, int expected_blacks);

static bool _rbt_contains(rbt_node *node, int value);

static void _rbt_print(rbt_node *node, const char *prefix);

static bool _rbt_insert(rbt_node **root_ptr, rbt_node *node, int value);
static void _rbt_fix_after_insert(rbt_node **root_ptr, rbt_node *node);
static void _rbt_rotate(rbt_node **root_ptr, rbt_node *node);

static bool _rbt_remove(rbt_node **root_ptr, rbt_node *node, int value);
static rbt_node *_rbt_in_order_predecessor(rbt_node *node);
static void _rbt_remove_node(rbt_node **root_ptr, rbt_node *node);
static void _rbt_resolve_double_black(rbt_node **root_ptr, rbt_node *node);

static void _rbt_free(rbt_node **node_ptr);

//
// Exposed red-black tree functions 
//
bool rbt_is_valid(rbt_node *root) {
  if (root == NULL) {
    return true;
  }
  if (root->color == RED) {
    return false;
  }
  
  rbt_node *node = root;
  int expected_blacks = 0;
  while (node != NULL) {
    if (node->color == BLACK) {
      expected_blacks++;
    }
    node = node->left;
  }
  return _rbt_is_subtree_valid(root->left, 1, expected_blacks)
    && _rbt_is_subtree_valid(root->right, 1, expected_blacks);
}

bool rbt_contains(rbt_node *root, int value) {
  return _rbt_contains(root, value);
}

void rbt_print(rbt_node *root) {
  printf("\nTree (valid read-black - %s):\n", rbt_is_valid(root) ? "Yes" : "No");
  _rbt_print(root, "");
  printf("\n");
}

bool rbt_insert(rbt_node **root_ptr, int value) {
  return _rbt_insert(root_ptr, *root_ptr, value);
}

bool rbt_remove(rbt_node **root_ptr, int value) {
  return _rbt_remove(root_ptr, *root_ptr, value);
}

void rbt_free(rbt_node **root_ptr) {
  _rbt_free(root_ptr);
}

// 
// Internal helper functions (implementations)
//
bool _rbt_is_subtree_valid(rbt_node *node, int encountered_blacks, int expected_blacks) {
  if (node == NULL) {
    return encountered_blacks == expected_blacks;
  }
  if (node->color == RED && node->parent->color == RED) {
    return false;
  }
  int new_encountered_blacks = encountered_blacks + (node->color == BLACK ? 1 : 0);
  return _rbt_is_subtree_valid(node->left, new_encountered_blacks, expected_blacks) 
    && _rbt_is_subtree_valid(node->right, new_encountered_blacks, expected_blacks);
}

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

static void _rbt_print(rbt_node *node, const char *prefix) {
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
  _rbt_print(node->left, ext_prefix);

  printf("\n%s%*s%s", prefix, 3 + value_len - 1, " ", VLINE_END);

  snprintf(ext_prefix, ext_prefix_size, "%s%*s", prefix, 3 + value_len, "");
  _rbt_print(node->right, ext_prefix);

  free(ext_prefix);
}

static bool _rbt_insert(rbt_node **root_ptr, rbt_node *node, int value) {
  if (node != NULL) {
    if (node->value == value) {
      return true; 
    }
    if (value < node->value && node->left != NULL) {
      return _rbt_insert(root_ptr, node->left, value);
    }
    if (value > node->value && node->right != NULL) {
      return _rbt_insert(root_ptr, node->right, value);
    }
  }
  rbt_node **new_node_ptr;
  if (node == NULL) {
    new_node_ptr = root_ptr;
  } else {
    new_node_ptr = (value < node->value) ? &node->left : &node->right;
  }
  *new_node_ptr = calloc(1, sizeof(rbt_node));
  if (*new_node_ptr == NULL) {
    return false;
  }
  rbt_node *new_node = *new_node_ptr;
  new_node->value = value;
  new_node->parent = node;
  new_node->color = RED;
  _rbt_fix_after_insert(root_ptr, new_node);
  return true;
}


static void _rbt_fix_after_insert(rbt_node **root, rbt_node *node) {
  if (node->color != RED) {
    // nothing to fix
    return;
  }
  if (node->parent == NULL) {
    // red root => recolor
    node->color = BLACK;
    return;
  }
  if (node->parent->color != RED) {
    // nothing to fix
    return;
  }
  if (node->parent->parent == NULL) {
    // parent is a red root => recolor parent
    node->parent->color = BLACK;
    return;
  }
  // at this point the node and its parent are both red, and neither is a root
  if (node->parent == node->parent->parent->left) {
    rbt_node *y = node->parent->parent->right;
    if (y != NULL && y->color == RED) {
      // parent's sibling is red => recolor and fix parent's parent
      y->color = BLACK;
      node->parent->color = BLACK;
      node->parent->parent->color = RED;
      _rbt_fix_after_insert(root, node->parent->parent);
    } else {
      rbt_node *z;
      if (node == node->parent->right) {
        z = node;
        // left rotate to make the current parent a left child
        _rbt_rotate(root, z);
      } else {
        // node is already a left child, we can simply use the parent
        z = node->parent;
      }
      // right rotate to get z higher up the tree
      _rbt_rotate(root, z);
      // recolor
      z->color = BLACK;
      z->right->color = RED;
    }
  } else {
    // symmetrical case
    rbt_node *y = node->parent->parent->left;
    if (y != NULL && y->color == RED) {
      // parent's sibling is red => recolor and fix parent's parent
      y->color = BLACK;
      node->parent->color = BLACK;
      node->parent->parent->color = RED;
      _rbt_fix_after_insert(root, node->parent->parent);
    } else {
      rbt_node *z;
      if (node == node->parent->left) {
        z = node;
        // right rotate to make the current parent a right child
        _rbt_rotate(root, z);
      } else {
        // node is already a right child, we can simply use the parent
        z = node->parent;
      }
      // left rotate to get z higher up the tree
      _rbt_rotate(root, z);
      // recolor
      z->color = BLACK;
      z->left->color = RED;
    }
  }
}

static void _rbt_rotate(rbt_node **root, rbt_node *node) {
  rbt_node *x = node, *y = x->parent, *y_parent = y->parent;
  if (x == y->left) { 
    // right rotate on x
    rbt_node *x_right = x->right;
    // make y x's right child
    x->right = y;
    y->parent = x;
    // make x's former right child y's left child
    y->left = x_right;
    if (x_right != NULL) {
      x_right->parent = y;
    }
  } else {
    // left rotate on x
    rbt_node *x_left = x->left;
    // make y x's left child
    x->left = y;
    y->parent = x;
    // make x's former left child y's right child
    y->right = x_left;
    if (x_left != NULL) {
      x_left->parent = y;
    }
  }
  // make y's former parent x's parent
  if (y_parent == NULL) {
    *root = x;
    x->parent = NULL;
  } else if (y_parent != NULL && y_parent->left == y) {
    y_parent->left = x;
    x->parent = y_parent;
  } else if (y_parent != NULL && y_parent->right == y) {
    y_parent->right = x;
    x->parent = y_parent;
  }
}

static bool _rbt_remove(rbt_node **root_ptr, rbt_node *node, int value) {
  if (node == NULL) {
    return false;
  }
  if (value < node->value) {
    return _rbt_remove(root_ptr, node->left, value); 
  }
  if (value > node->value) {
    return _rbt_remove(root_ptr, node->right, value);
  }
  // if 0 or 1 children we can simply remove node and promote the child
  if (node->left == NULL || node->right == NULL) {
    rbt_node **node_ptr;
    if (node->parent == NULL) {
      node_ptr = root_ptr;
    } else if (node->parent->left == node) {
      node_ptr = &node->parent->left;
    } else {
      node_ptr = &node->parent->right;
    }
    *node_ptr = (node->left != NULL) ? node->left : node->right;
    if (*node_ptr != NULL) {
      (*node_ptr)->parent = node->parent;
    }
    free(node);
    return true;
  }
  // otherwise we need to replace the node with the rightmost node in the left sub-tree
  rbt_node *p = _rbt_in_order_predecessor(node);
  node->value = p->value;
  return _rbt_remove(root_ptr, p, p->value);
}

static rbt_node *_rbt_in_order_predecessor(rbt_node *node) {
  rbt_node *p = node->left;
  while (p->right != NULL) {
    p = p->right;
  }
  return p;
}

static void _rbt_remove_node(rbt_node **root, rbt_node *node) {

}

static void _rbt_resolve_double_black(rbt_node **root, rbt_node *node) {

}

static void _rbt_free(rbt_node **node_ptr) {
  if (*node_ptr == NULL) {
    return;
  }
  rbt_node *node = *node_ptr;
  if (node->left != NULL) {
    _rbt_free(&node->left);
  }
  if (node->right != NULL) {
    _rbt_free(&node->right);
  }
  free(node);
  *node_ptr = NULL;
}
