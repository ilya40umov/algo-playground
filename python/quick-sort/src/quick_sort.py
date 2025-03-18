from typing import List, Any


def sort(values: List[Any]) -> None:
    def swap(pos1, pos2):
        tmp = values[pos1]
        values[pos1] = values[pos2]
        values[pos2] = tmp

    def sort_slice(start, end):
        if end - start <= 1:
            return
        if end - start == 2:
            if values[start] > values[end - 1]:
                swap(start, end - 1)
            return
        
        # XXX pivot selection could be better
        pivot_index = start
        pivot_value = values[pivot_index]
        
        print(pivot_value, values)

        i = start
        j = end - 1
        while i < j:
            if values[i] <= pivot_value:
                i += 1
                continue
            if values[j] >= pivot_value:
                j -= 1
                continue
            swap(i, j)
        
        if values[pivot_index] > values[i]:
            swap(pivot_index, i)

        sort_slice(start, i)
        sort_slice(i, end)

    sort_slice(0, len(values))
