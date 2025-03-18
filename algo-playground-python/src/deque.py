from typing import Any, Callable, Optional


# noinspection DuplicatedCode
class Deque(object):
    class Node(object):
        def __init__(self, value):
            self.value = value
            self.next = None
            self.prev = None

    def __init__(self):
        self.__head = None
        self.__tail = None

    def enqueue(self, value: Any):
        """Append value to the tail."""
        self.add_right(value)

    def dequeue(self) -> Optional[Any]:
        """Remove value from the head."""
        return self.remove_left()

    def push(self, value: Any):
        """Append value to the head."""
        self.add_left(value)

    def pop(self) -> Optional[Any]:
        """Remove value from the head."""
        return self.remove_left()

    def reduce(self, aggr: Any, func: Callable[[Any, Any], Any]) -> Any:
        node = self.__head
        while node is not None:
            aggr = func(aggr, node.value)
            node = node.next
        return aggr

    def head(self) -> Any:
        if self.__head is None:
            return None
        return self.__head.value

    def tail(self) -> Any:
        if self.__tail is None:
            return None
        return self.__tail.value

    def add_left(self, value: Any):
        if self.__tail is None:
            self.__tail = Deque.Node(value)
            self.__head = self.__tail
            return
        head = Deque.Node(value)
        head.next = self.__head
        self.__head.prev = head
        self.__head = head

    def remove_left(self) -> Optional[Any]:
        if self.__head is None:
            return None
        value = self.__head.value
        if self.__head == self.__tail:
            self.__head = None
            self.__tail = None
        else:
            self.__head = self.__head.next
            self.__head.prev = None
        return value

    def add_right(self, value: Any):
        if self.__tail is None:
            self.__tail = Deque.Node(value)
            self.__head = self.__tail
            return
        tail = self.__tail
        tail.next = Deque.Node(value)
        tail.next.prev = tail
        self.__tail = tail.next

    def remove_right(self) -> Optional[Any]:
        if self.__tail is None:
            return None
        value = self.__tail.value
        if self.__tail == self.__head:
            self.__tail = None
            self.__head = None
        else:
            self.__tail = self.__tail.prev
            self.__tail.next = None
        return value
