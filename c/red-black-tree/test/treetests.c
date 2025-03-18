#include <criterion/criterion.h>

#include "../src/tree.h"

static rbt_node *root = NULL;

void teardown(void) {
  rbt_free_tree(&root);
}

Test(treetests, contains) {
  rbt_insert(&root, 3);
  rbt_insert(&root, 2);
  rbt_insert(&root, 5);

  cr_expect(
    rbt_contains(root, 3), 
    "rbt_contains should return TRUE if tree contains the number"
  );
  cr_expect(
    !rbt_contains(root, 888), 
    "rbt_contains should return FALSE if tree does not contain the number"
  );
}
