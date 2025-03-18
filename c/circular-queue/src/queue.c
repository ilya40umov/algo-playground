#include <limits.h>
#include <stdbool.h>
#include <stdlib.h>
#include "queue.h"

#define QUEUE_EMPTY INT_MIN

bool cq_init(cq_queue *queue, int capacity) {
  queue->capacity = capacity;
  queue->size = 0;
  queue->head_idx = 0;
  queue->tail_idx = 0;
  queue->values = malloc(capacity * sizeof(int));
  if (queue->values == NULL) {
    return false;
  }
  return true;
}

void cq_free(cq_queue *queue) {
  free(queue->values);
}

bool cq_enqueue(cq_queue *queue, int value) {
  if (queue->size == queue->capacity) {
    return false;
  }
  queue->values[queue->tail_idx] = value;
  queue->tail_idx = (queue->tail_idx + 1) % queue->capacity;
  queue->size++;
  return true;
}

int cq_dequeue(cq_queue *queue, bool *error) {
  if (queue->size == 0) {
    if (error != NULL) {
      *error = true;
    }
    return QUEUE_EMPTY;
  }
  int value = queue->values[queue->head_idx];
  queue->head_idx = (queue->head_idx + 1) % queue->capacity;
  queue->size--;
  return value;
}
