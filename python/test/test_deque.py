import unittest

from src import deque


class TestDeque(unittest.TestCase):
    def test_enqueue_adds_elements_at_tail(self):
        dq = deque.Deque()
        dq.enqueue("A")
        dq.enqueue("B")

        self.assertEqual(dq.head(), "A")
        self.assertEqual(dq.tail(), "B")

    def test_dequeue_removes_elements_at_head(self):
        dq = deque.Deque()
        dq.enqueue("A")
        dq.enqueue("B")

        self.assertEqual(dq.dequeue(), "A")
        self.assertEqual(dq.dequeue(), "B")
        self.assertIsNone(dq.head())
        self.assertIsNone(dq.tail())

    def test_push_adds_elements_at_head(self):
        dq = deque.Deque()
        dq.push("A")
        dq.push("B")

        self.assertEqual(dq.head(), "B")
        self.assertEqual(dq.tail(), "A")

    def test_pop_removes_elements_at_head(self):
        dq = deque.Deque()
        dq.push("A")
        dq.push("B")

        self.assertEqual(dq.pop(), "B")
        self.assertEqual(dq.pop(), "A")
        self.assertIsNone(dq.head())
        self.assertIsNone(dq.tail())

    def test_deque_supports_mixed_usage(self):
        dq = deque.Deque()
        dq.push("A")
        dq.push("B")
        dq.enqueue("C")
        dq.enqueue("D")

        self.assertEqual(dq.head(), "B")
        self.assertEqual(dq.tail(), "D")
        self.assertEqual(dq.remove_left(), "B")
        self.assertEqual(dq.remove_right(), "D")
        self.assertEqual(dq.head(), "A")
        self.assertEqual(dq.tail(), "C")
