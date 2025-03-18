#ifndef QUEUE_H
#define QUEUE_H
#include <stdbool.h>

typedef struct {
  int *values;
  int head_idx, tail_idx, size, capacity;
} cq_queue;

bool cq_init(cq_queue *queue, int capacity);

void cq_free(cq_queue *queue);

bool cq_enqueue(cq_queue *queue, int value);

int cq_dequeue(cq_queue *queue, bool *error);

#endif
