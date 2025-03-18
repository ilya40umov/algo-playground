import unittest

from src import hashtable


class TestHashtable(unittest.TestCase):
    def test_get_returns_value_for_known_item(self):
        h = hashtable.Hashtable()
        h["a"] = "apple"
        self.assertEqual(h["a"], "apple")

    def test_get_returns_none_for_unknown_item(self):
        h = hashtable.Hashtable()
        h["a"] = "apple"
        self.assertIsNone(h["b"])

    def test_delete_ignores_unknown_item(self):
        h = hashtable.Hashtable()
        del h["a"]

    def test_delete_removes_known_item(self):
        h = hashtable.Hashtable()
        h["a"] = "apple"
        del h["a"]
        self.assertIsNone(h["a"])

    def test_get_works_after_multiple_resizings_and_deletions(self):
        h = hashtable.Hashtable()
        for i in range(1, 1000):
            h[str(i)] = i
        for i in range(2, 1000, 2):
            del h[str(i)]
        for i in range(1, 1000, 2):
            self.assertEqual(h[str(i)], i)
