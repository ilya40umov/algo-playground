#include "queue.h"
#include <limits.h>
#include <stdbool.h>
#include <stdlib.h>

#define QUEUE_EMPTY INT_MIN

struct cq_queue {
  int *values;
  unsigned int head_idx, tail_idx, size, capacity;
};

bool cq_init(cq_queue **q_ptr, unsigned int capacity) {
  cq_queue *q = malloc(sizeof(cq_queue));
  q->capacity = capacity;
  q->size = 0;
  q->head_idx = 0;
  q->tail_idx = 0;
  q->values = malloc(capacity * sizeof(int));
  if (q->values == NULL) {
    free(q);
    return false;
  }
  *q_ptr = q;
  return true;
}

void cq_free(cq_queue **q_ptr) {
  free((*q_ptr)->values);
  free(*q_ptr);
  *q_ptr = NULL;
}

unsigned int cq_size(cq_queue *q) { return q->size; }

bool cq_enqueue(cq_queue *q, int value) {
  if (q->size == q->capacity) {
    return false;
  }
  q->values[q->tail_idx] = value;
  q->tail_idx = (q->tail_idx + 1) % q->capacity;
  q->size++;
  return true;
}

int cq_dequeue(cq_queue *q, bool *error) {
  if (q->size == 0) {
    if (error != NULL) {
      *error = true;
    }
    return QUEUE_EMPTY;
  }
  int value = q->values[q->head_idx];
  q->head_idx = (q->head_idx + 1) % q->capacity;
  q->size--;
  return value;
}
