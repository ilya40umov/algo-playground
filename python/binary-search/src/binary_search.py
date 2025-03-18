from typing import List, Optional, Any
from time import sleep


def find(haystack: List[Any], needle: Any) -> Optional:
    if not haystack:
        return None
    start = 0
    end = len(haystack) - 1
    while start <= end:
        mid_idx = start + (end - start) // 2
        value = haystack[mid_idx]
        if value == needle:
            return mid_idx
        elif value < needle:
            start = mid_idx + 1
        else:
            end = mid_idx - 1
    return None
