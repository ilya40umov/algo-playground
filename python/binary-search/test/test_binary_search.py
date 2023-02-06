import unittest
from src import binary_search


class TestBinarySearch(unittest.TestCase):
    def test_find_returns_none_if_list_empty(self):
        self.assertIsNone(binary_search.find([], 33))

    def test_find_returns_none_if_value_not_present(self):
        self.assertIsNone(binary_search.find([11, 22, 44, 55], 11))
        self.assertIsNone(binary_search.find([11, 22, 44, 55], 33))
        self.assertIsNone(binary_search.find([11, 22, 44, 55], 70))

    def test_find_returns_position_if_value_is_present(self):
        self.assertEqual(binary_search.find([11, 22, 44, 55], 11), 0)
        self.assertEqual(binary_search.find([11, 22, 44, 55], 22), 1)
        self.assertEqual(binary_search.find([11, 22, 44, 55], 44), 2)
        self.assertEqual(binary_search.find([11, 22, 44, 55], 55), 3)
        self.assertEqual(binary_search.find([1, 2, 4, 5, 6], 1), 0)
        self.assertEqual(binary_search.find([1, 2, 4, 5, 6], 5), 3)
        self.assertEqual(binary_search.find([1, 2, 4, 5, 6], 6), 4)


if __name__ == "__main__":
    unittest.main()
