#include <criterion/criterion.h>
#include <criterion/new/assert.h>
#include <limits.h>
#include <stdio.h>

#include "../src/queue.h"

#define MAX_CAPACITY 5

static cq_queue *q;

void setup(void) { cq_init(&q, MAX_CAPACITY); }

void teardown(void) { cq_free(&q); }

Test(queuetests, enqueue_on_empty, .init = setup, .fini = teardown) {
  cr_expect(cq_enqueue(q, 42),
            "cq_enqueue should return TRUE if queue is not full yet");
  cr_expect(eq(int, cq_size(q), 1));
}

Test(queuetests, enqueue_on_full, .init = setup, .fini = teardown) {
  for (int i = 0; i < MAX_CAPACITY; i++) {
    cq_enqueue(q, i);
  }

  cr_expect(not(cq_enqueue(q, 42)),
            "cq_enqueue should return FALSE if queue is full");
  cr_expect(eq(int, cq_size(q), MAX_CAPACITY));
}

Test(queuetests, dequeue_on_empty, .init = setup, .fini = teardown) {
  cr_expect(eq(int, cq_dequeue(q, NULL), INT_MIN),
            "cq_dequeue should return INT_MIN if queue is empty");
  cr_expect(eq(int, cq_size(q), 0));
}

Test(queuetests, dequeue_on_full, .init = setup, .fini = teardown) {
  cq_enqueue(q, 42);
  cq_enqueue(q, 43);

  cr_expect(eq(int, cq_dequeue(q, NULL), 42),
            "cq_dequeue should return oldest value if queue is not empty");
  cr_expect(eq(int, cq_size(q), 1));
}
