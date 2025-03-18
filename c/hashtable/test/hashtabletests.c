#include <stdio.h>
#include <criterion/criterion.h>
#include <criterion/new/assert.h>
#include <criterion/assert.h>

#include "../src/hashtable.h"

#define CAPACITY 10

static ht_hashtable *t;

void setup(void) {
  t = ht_create(CAPACITY);
}

void teardown(void) {
  ht_free(&t);
}

Test(hashtabletests, put_not_existing, .init = setup, .fini = teardown) {
  cr_expect(
    ht_put(t, "Name", "Gandalf"),
    "ht_put should return TRUE if adding key/value was successful"
  );
}

Test(hashtabletests, get_existing, .init = setup, .fini = teardown) {
  ht_put(t, "Name", "Gandalf");
  ht_put(t, "Nickname", "Mithrandir");

  cr_expect(
    eq(str, (char *)ht_get(t, "Name"), "Gandalf"),
    "ht_get should return value associated with the key if it's present"
  );
}

Test(hashtabletests, get_not_existing, .init = setup, .fini = teardown) {
  ht_put(t, "Name", "Gandalf");
  ht_put(t, "Nickname", "Mithrandir");

  cr_expect_null(
    ht_get(t, "Number"),
    "ht_get should return NULL if the key is not present"
  );
}

Test(hashtabletests, remove_existing, .init = setup, .fini = teardown) {
  ht_put(t, "Name", "Gandalf");
  ht_put(t, "Nickname", "Mithrandir");

  cr_expect(
    ht_remove(t, "Name"),
    "ht_remove should return TRUE if the key was present"
  );
}

Test(hashtabletests, remove_not_existing, .init = setup, .fini = teardown) {
  ht_put(t, "Name", "Gandalf");
  ht_put(t, "Nickname", "Mithrandir");

  cr_expect(
    not(ht_remove(t, "Age")),
    "ht_remove should return FALSE if the key was present"
  );
}
