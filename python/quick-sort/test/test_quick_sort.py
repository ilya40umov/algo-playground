import unittest
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


if __name__ == "__main__":
    unittest.main()
