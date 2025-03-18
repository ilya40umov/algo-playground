from src.substr import longest_common_substring
import unittest


class TestSubstr(unittest.TestCase):
    def test_longest_substring_returns_none_if_there_no_overlaps(self):
        self.assertIsNone(longest_common_substring("", ""))
        self.assertIsNone(longest_common_substring("abc", ""))
        self.assertIsNone(longest_common_substring("", "def"))
        self.assertIsNone(longest_common_substring("abc", "def"))

    def test_longest_substring_returns_longest_substring_if_overlap_exists(self):
        self.assertEqual(longest_common_substring("a", "a"), "a")
        self.assertEqual(longest_common_substring("abc", "cde"), "c")
        self.assertEqual(longest_common_substring("abc", "bcd"), "bc")
        self.assertEqual(longest_common_substring("abc", "abc"), "abc")
        self.assertEqual(longest_common_substring("abcdefgh", "rtycdefigh"), "cdef")
