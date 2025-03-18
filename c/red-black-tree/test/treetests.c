#include <criterion/criterion.h>

#include "../src/tree.h"

static rbt_node *root = NULL;

void teardown(void) {
  rbt_free(&root);
}

void insert_from_n_to_m(int n, int m) {
  for (int i = n; i <= m; ++i) {
    rbt_insert(&root, i); 
  }
}

void remove_from_n_to_m(int n, int m) {
  for (int i = n; i <= m; ++i) {
    rbt_remove(&root, i); 
  }
}

Test(treetests, is_valid_correct_tree, .fini=teardown) {
  insert_from_n_to_m(1, 10);

  cr_expect(
    rbt_is_valid(root), 
    "rbt_is_valid should return TRUE if tree is valid red-black binary search tree"
  );
}

Test(treetests, contains_existing, .fini=teardown) {
  insert_from_n_to_m(1, 10);

  cr_expect(
    rbt_contains(root, 3), 
    "rbt_contains should return TRUE if tree contains the number"
  );
}

Test(treetests, contains_missing, .fini=teardown) {
  insert_from_n_to_m(1, 10);

  cr_expect(
    !rbt_contains(root, 33), 
    "rbt_contains should return FALSE if tree does not contain the number"
  );
}

Test(treetests, insert_1000, .fini=teardown) {
  insert_from_n_to_m(1, 1000);

  cr_expect(
    rbt_is_valid(root), 
    "inserting 1000 elements should produce a valid tree"
  );
  for (int i = 1; i < 1000; ++i) {
    cr_expect(
      rbt_contains(root, i), 
      "each inserted element should be findable"
    );
  }
}

Test(treetests, insert_1000_delete_900, .fini=teardown) {
  insert_from_n_to_m(1, 1000);
  remove_from_n_to_m(101, 1000);

  cr_expect(
    rbt_is_valid(root), 
    "inserting 1000 and deleting 900 elements should produce a valid tree"
  );
  for (int i = 1; i <= 100; ++i) {
    cr_expect(
      rbt_contains(root, i), 
      "each remaining element should be findable"
    );
  }
  for (int i = 101; i <= 1000; ++i) {
    cr_expect(
      !rbt_contains(root, i), 
      "each deleted element should not be findable"
    );
  }
}
