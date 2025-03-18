#include "hashtable.h"
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

typedef struct ht_node ht_node;

struct ht_hashtable {
  ht_node **nodes;
  size_t capacity;
};

struct ht_node {
  ht_node *prev, *next;
  const char *key;
  const char *value;
};

static unsigned long hash(const char *str);
static const char *strclone(const char *str);

bool ht_init(ht_hashtable **t_ptr, size_t capacity) {
  ht_hashtable *t = malloc(sizeof(ht_hashtable));
  if (t == NULL) {
    return false;
  }
  t->capacity = capacity;
  t->nodes = calloc(capacity, sizeof(ht_node *));
  if (t->nodes == NULL) {
    free(t);
    return false;
  }
  *(t_ptr) = t;
  return true;
}

bool ht_free(ht_hashtable **t_ptr) {
  ht_hashtable *t = *t_ptr;
  for (int i = 0; i < t->capacity; i++) {
    ht_node *cur = t->nodes[i];
    if (cur == NULL) {
      continue;
    }
    while (cur != NULL) {
      ht_node *next = cur->next;
      free((void *)cur->key);
      free((void *)cur->value);
      free(cur);
      cur = next;
    }
  }
  free(t->nodes);
  free(t);
  *t_ptr = NULL;
  return true;
}

bool ht_put(ht_hashtable *t, const char *key, const char *value) {
  unsigned int code = hash(key) % t->capacity;

  if (t->nodes[code] == NULL) {
    ht_node *node = calloc(1, sizeof(ht_node));
    if (node == NULL) {
      return false;
    }
    node->key = strclone(key);
    node->value = strclone(value);
    t->nodes[code] = node;
    return true;
  }

  ht_node *cur = t->nodes[code];
  while (true) {
    if (strcmp(cur->value, key) == 0) {
      return true;
    }
    if (cur->next == NULL) {
      break;
    }
    cur = cur->next;
  }

  ht_node *node = calloc(1, sizeof(ht_node));
  if (node == NULL) {
    return false;
  }
  node->key = strclone(key);
  node->value = strclone(value);
  cur->next = node;
  node->prev = cur;
  return true;
}

const char *ht_get(ht_hashtable *t, const char *key) {
  unsigned int code = hash(key) % t->capacity;
  ht_node *cur = t->nodes[code];
  while (cur != NULL) {
    if (strcmp(cur->key, key) == 0) {
      return cur->value;
    }
    cur = cur->next;
  }
  return NULL;
}

bool ht_remove(ht_hashtable *t, const char *key) {
  unsigned int code = hash(key) % t->capacity;
  ht_node *cur = t->nodes[code];
  while (cur != NULL) {
    if (strcmp(cur->key, key) == 0) {
      if (cur->prev != NULL) {
        cur->prev->next = cur->next;
      }
      if (cur->next != NULL) {
        cur->next->prev = cur->prev;
      }
      if (cur == t->nodes[code]) {
        t->nodes[code] = cur->next;
      }
      free((void *)cur->key);
      free((void *)cur->value);
      free(cur);
      return true;
    }
    cur = cur->next;
  }
  return false;
}

bool ht_contains(ht_hashtable *t, const char *key) {
  return ht_get(t, key) != NULL;
}

// http://www.cse.yorku.ca/~oz/hash.html
static unsigned long hash(const char *str) {
  unsigned long hash = 5381;
  unsigned int c;
  while ((c = *str++)) {
    // hash =  hash * 33 + c
    hash = ((hash << 5) + hash) + c;
  }
  return hash;
}

static const char *strclone(const char *str) {
  unsigned long len = strlen(str);
  char *clone = malloc((len + 1) * sizeof(char));
  strcpy(clone, str);
  return clone;
}
