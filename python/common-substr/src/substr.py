from typing import Optional
from functools import cache


def longest_common_substring(str1: str, str2: str) -> Optional[str]:
    if len(str1) == 0 or len(str2) == 0:
        return None

    if str1 in str2:
        return str1

    if str2 in str1:
        return str2

    if len(str1) == 1 or len(str2) == 1:
        return None

    # using memoization instead of building a DP matrix
    @cache
    def longest_common_suffix(str1: str, str2: str) -> Optional[str]:
        if not str1 or not str2 or str1[-1] != str2[-1]:
            return None
        sub_suffix = longest_common_suffix(str1[:-1], str2[:-1])
        if sub_suffix:
            return sub_suffix + str1[-1]
        else:
            return str1[-1]

    longest_result = None
    for i in range(1, len(str1) + 1):
        for j in range(1, len(str2) + 1):
            candidate = longest_common_suffix(str1[:i], str2[:j])
            if not candidate:
                continue
            if not longest_result or len(candidate) > len(longest_result):
                longest_result = candidate

    return longest_result
