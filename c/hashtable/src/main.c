#include <stdio.h>
#include <stdlib.h>
#include "hashtable.h"

char *fmtbool(bool value);

int main(int argc, char **argv) {
  printf("Test #1:\n");
  
  ht_hashtable *t = ht_create(10);
  printf("ht_put(Name,Zorro): %s\n", fmtbool(ht_put(t, "Name", "Zorro")));
  printf("ht_put(Age,23): %s\n", fmtbool(ht_put(t, "Age", "23")));
  printf("ht_put(Planet,Earth): %s\n", fmtbool(ht_put(t, "Planet", "Earth")));
  printf("ht_remove(Name): %s\n", fmtbool(ht_remove(t, "Name")));
  printf("ht_remove(Galaxy): %s\n", fmtbool(ht_remove(t, "Galaxy")));
  printf("ht_contains(Planet): %s\n", fmtbool(ht_contains(t, "Planet")));
  printf("ht_contains(Name): %s\n", fmtbool(ht_contains(t, "Name")));
  printf("ht_get(Age): %s\n", ht_get(t, "Age"));
  printf("ht_get(Magic): %s\n", ht_get(t, "Magic"));
  ht_free(&t);

  printf("\nTest #2:\n");
  t = ht_create(20);
  int j = 0;
  char key[20] = {0};
  key[0] = 'a';
  for (int i = 1; i < 50; i++) {
    printf("ht_put(%s): %s\n", key, fmtbool(ht_put(t, key, key)));
    if (key[j] == 'z') {
      j++;
      key[j] = 'a';
    } else {
      key[j] += 1;
    }
  }
  printf("\n");
  key[j] -= 1;
  for (int i = 1; i < 50; i++) {
    printf("ht_get(%s): %s\n", key, ht_get(t, key));
    if (key[j] == 'a') {
      key[j] = '\0';
      j--;
    } else {
      key[j] -= 1;
    }
  }
  ht_free(&t);

  return EXIT_SUCCESS;
}

char *fmtbool(bool value) {
  return value ? "true" : "false";
}
