from typing import List, Any


def sort(values: List[Any]) -> None:
    for i in range(0, len(values)):
        min_idx = i
        for j in range(i + 1, len(values)):
            if values[j] < values[min_idx]:
                min_idx = j
        tmp = values[i]
        values[i] = values[min_idx]
        values[min_idx] = tmp
