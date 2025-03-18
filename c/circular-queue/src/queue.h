#ifndef QUEUE_H
#define QUEUE_H

#include <stdbool.h>

typedef struct cq_queue cq_queue;

bool cq_init(cq_queue **q_ptr, unsigned int capacity);

void cq_free(cq_queue **q_ptr);

unsigned int cq_size(cq_queue *q);

bool cq_enqueue(cq_queue *q, int value);

int cq_dequeue(cq_queue *q, bool *error);

#endif
