import sys
from typing import Generic, Optional, TypeVar

K = TypeVar("K")
V = TypeVar("V")


class Hashtable(Generic[K, V]):
    def __init__(self, load_factor=0.7, capacity=10):
        self.__load_factor = load_factor
        self.__size = 0
        self.__buckets = [[] for _ in range(0, capacity)]

    def __getitem__(self, key: K) -> Optional[V]:
        idx = self.__key_to_idx(key)
        return next((item[1] for item in self.__buckets[idx] if item[0] == key), None)

    def __delitem__(self, key: K):
        idx = self.__key_to_idx(key)
        bucket = self.__buckets[idx]
        for i in range(0, len(bucket)):
            if bucket[i][0] == key:
                del bucket[i]
                return

    def __setitem__(self, key: K, value: V):
        if self.__put_if_not_present(key, value):
            self.__size += 1
            self.__resize_if_needed()

    def __resize_if_needed(self):
        if self.__size / len(self.__buckets) < self.__load_factor:
            return
        old_buckets = self.__buckets
        self.__buckets = [[] for _ in range(0, len(old_buckets) * 2)]
        for bucket in old_buckets:
            for k, v in bucket:
                self.__put_if_not_present(k, v)

    def __put_if_not_present(self, key: K, value: V) -> bool:
        idx = self.__key_to_idx(key)
        if value not in self.__buckets[idx]:
            self.__buckets[idx].append((key, value))
            return True
        return False

    def __key_to_idx(self, key: K) -> int:
        return int(
            (hash(key) + sys.maxsize + 1) / sys.maxsize / 2 * len(self.__buckets)
        )
