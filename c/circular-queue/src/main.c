#include <stdlib.h>
#include <stdio.h>
#include "queue.h"

int main(int argc, char **argv) {
  cq_queue queue;
  cq_init(&queue, 5);

  cq_enqueue(&queue, 42);
  cq_enqueue(&queue, 777);
  cq_dequeue(&queue, NULL);
  cq_dequeue(&queue, NULL);

  printf("Adding '1'. Success? - %u\n", cq_enqueue(&queue, 1));
  printf("Adding '2'. Success? - %u\n", cq_enqueue(&queue, 2));
  printf("Adding '3'. Success? - %u\n", cq_enqueue(&queue, 3));
  printf("Adding '4'. Success? - %u\n", cq_enqueue(&queue, 4));
  printf("Adding '5'. Success? - %u\n", cq_enqueue(&queue, 5));
  printf("Adding '6'. Success? - %u\n", cq_enqueue(&queue, 6));

  while (queue.size > 0) {
    printf("Dequeued: %d\n", cq_dequeue(&queue, NULL));
  }

  printf("Tried to dequeue more, but got: %d\n", cq_dequeue(&queue, NULL));

  cq_free(&queue);
  return EXIT_SUCCESS;
}
