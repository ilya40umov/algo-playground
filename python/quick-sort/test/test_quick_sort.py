import unittest
import random
from src import quick_sort


class TestQuickSort(unittest.TestCase):
    def test_sort_works_for_empty_list(self):
        l = []
        quick_sort.sort(l)
        self.assertSequenceEqual(l, [])

    def test_sort_works_for_single_element_list(self):
        l = [42]
        quick_sort.sort(l)
        self.assertSequenceEqual(l, [42])

    def test_sort_works_for_sorted_list(self):
        l = [1, 2, 7, 18, 90]
        quick_sort.sort(l)
        self.assertSequenceEqual(l, [1, 2, 7, 18, 90])

    def test_sort_works_for_unsorted_list_with_odd_elements_count(self):
        l = [75, 3, 15, 42, 9, 99, 20]
        quick_sort.sort(l)
        self.assertSequenceEqual(l, [3, 9, 15, 20, 42, 75, 99])

    def test_sort_works_for_unsorted_list_with_even_elements_count(self):
        l = [75, 3, 15, 42, 9, 99]
        quick_sort.sort(l)
        self.assertSequenceEqual(l, [3, 9, 15, 42, 75, 99])

    def test_sort_works_for_list_consisting_of_same_values(self):
        l = [4, 4, 4, 4, 4]
        quick_sort.sort(l)
        self.assertSequenceEqual(l, [4, 4, 4, 4, 4])

    def test_sort_works_for_big_array(self):
        random.seed(123)
        l = [random.randint(1, 99) for i in range(1, 30)]
        quick_sort.sort(l)
        self.assertSequenceEqual(l, sorted(l))


if __name__ == "__main__":
    unittest.main()
