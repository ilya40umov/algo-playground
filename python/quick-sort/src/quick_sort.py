from typing import List, Any


def sort(values: List[Any]) -> None:
    def swap(pos1, pos2):
        tmp = values[pos1]
        values[pos1] = values[pos2]
        values[pos2] = tmp

    def sort_slice(start, end):
        if end - start <= 1:
            return

        # XXX pivot selection could be better
        pivot_value = values[start]

        i = start
        j = end - 1
        while i < j:
            if values[i] <= pivot_value:
                i += 1
                continue
            if values[j] <= pivot_value:
                swap(i, j)
                i += 1
            j -= 1
        if values[start] > values[j]:
            swap(start, j)

        sort_slice(start, j)
        sort_slice(j + 1, end)

    sort_slice(0, len(values))
