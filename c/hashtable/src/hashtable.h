#ifndef HASHTABLE_H
#define HASHTABLE_H

#include <stdlib.h>
#include <stdbool.h>

typedef struct ht_hashtable ht_hashtable;

bool ht_init(ht_hashtable **t_ptr, size_t capacity);

bool ht_free(ht_hashtable **t_ptr);

bool ht_put(ht_hashtable *t, const char *key, const char *value);

const char *ht_get(ht_hashtable *t, const char *key);

bool ht_remove(ht_hashtable *t, const char *key);

bool ht_contains(ht_hashtable *t, const char *key);

#endif
