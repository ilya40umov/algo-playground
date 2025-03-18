#include <stdlib.h>
#include <stdio.h>
#include "queue.h"

int main(int argc, char **argv) {
  cq_queue *q;
  cq_init(&q, 5);

  cq_enqueue(q, 42);
  cq_enqueue(q, 777);
  cq_dequeue(q, NULL);
  cq_dequeue(q, NULL);

  printf("Adding '1'. Success? - %u\n", cq_enqueue(q, 1));
  printf("Adding '2'. Success? - %u\n", cq_enqueue(q, 2));
  printf("Adding '3'. Success? - %u\n", cq_enqueue(q, 3));
  printf("Adding '4'. Success? - %u\n", cq_enqueue(q, 4));
  printf("Adding '5'. Success? - %u\n", cq_enqueue(q, 5));
  printf("Adding '6'. Success? - %u\n", cq_enqueue(q, 6));

  while (cq_size(q) > 0) {
    printf("Dequeued: %d\n", cq_dequeue(q, NULL));
  }

  printf("Tried to dequeue more, but got: %d\n", cq_dequeue(q, NULL));

  cq_free(&q);
  return EXIT_SUCCESS;
}
