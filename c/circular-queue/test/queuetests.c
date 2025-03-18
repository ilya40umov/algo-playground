#include <limits.h>
#include <stdio.h>
#include <criterion/criterion.h>
#include <criterion/new/assert.h>

#include "../src/queue.h"

#define MAX_CAPACITY 5

static cq_queue queue;

void setup(void) {
  cq_init(&queue, MAX_CAPACITY);
}

void teardown(void) {
  cq_free(&queue);
}

Test(queuetests, enqueue_on_empty, .init = setup, .fini = teardown) {
  cr_expect(
    cq_enqueue(&queue, 42),
    "cq_enqueue should return TRUE if queue is not full yet"
  );
  cr_expect(eq(int, queue.size, 1));
}

Test(queuetests, enqueue_on_full, .init = setup, .fini = teardown) {
  for (int i = 0; i < MAX_CAPACITY; i++) {
    cq_enqueue(&queue, i);
  }

  cr_expect(
    not(cq_enqueue(&queue, 42)),
    "cq_enqueue should return FALSE if queue is full"
  );
  cr_expect(eq(int, queue.size, MAX_CAPACITY));
}

Test(queuetests, dequeue_on_empty, .init = setup, .fini = teardown) {
  cr_expect(
    eq(int, cq_dequeue(&queue, NULL), INT_MIN),
    "cq_dequeue should return INT_MIN if queue is empty"
  );
  cr_expect(eq(int, queue.size, 0));
}

Test(queuetests, dequeue_on_full, .init = setup, .fini = teardown) {
  cq_enqueue(&queue, 42);
  cq_enqueue(&queue, 43);

  cr_expect(
    eq(int, cq_dequeue(&queue, NULL), 42),
    "cq_dequeue should return oldest value if queue is not empty"
  );
  cr_expect(eq(int, queue.size, 1));
}
