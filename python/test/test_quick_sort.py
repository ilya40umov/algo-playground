import random
import unittest

from src import quick_sort


class TestQuickSort(unittest.TestCase):
    def test_sort_works_for_empty_list(self):
        data = []
        quick_sort.sort(data)
        self.assertSequenceEqual(data, [])

    def test_sort_works_for_single_element_list(self):
        data = [42]
        quick_sort.sort(data)
        self.assertSequenceEqual(data, [42])

    def test_sort_works_for_sorted_list(self):
        data = [1, 2, 7, 18, 90]
        quick_sort.sort(data)
        self.assertSequenceEqual(data, [1, 2, 7, 18, 90])

    def test_sort_works_for_unsorted_list_with_odd_elements_count(self):
        data = [75, 3, 15, 42, 9, 99, 20]
        quick_sort.sort(data)
        self.assertSequenceEqual(data, [3, 9, 15, 20, 42, 75, 99])

    def test_sort_works_for_unsorted_list_with_even_elements_count(self):
        data = [75, 3, 15, 42, 9, 99]
        quick_sort.sort(data)
        self.assertSequenceEqual(data, [3, 9, 15, 42, 75, 99])

    def test_sort_works_for_list_consisting_of_same_values(self):
        data = [4, 4, 4, 4, 4]
        quick_sort.sort(data)
        self.assertSequenceEqual(data, [4, 4, 4, 4, 4])

    def test_sort_works_for_big_array(self):
        random.seed(123)
        date = [random.randint(1, 99) for i in range(1, 30)]
        quick_sort.sort(date)
        self.assertSequenceEqual(date, sorted(date))
