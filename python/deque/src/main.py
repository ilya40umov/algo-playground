from deque import Deque


def main():
    dq = Deque()

    print("enqueue(A)")
    dq.enqueue("A")
    print("enqueue(B)")
    dq.enqueue("B")
    print("enqueue(C)")
    dq.enqueue("C")

    print("Deque contents:", dq.reduce("", lambda s, v: f"{s} {v}"))

    print("dequeue() -> ", dq.dequeue())
    print("dequeue() -> ", dq.dequeue())
    print("dequeue() -> ", dq.dequeue())
    print("dequeue() -> ", dq.dequeue())

    print("push(A)")
    dq.push("A")
    print("push(B)")
    dq.push("B")
    print("push(C)")
    dq.push("C")

    print("Deque contents:", dq.reduce("", lambda s, v: f"{s} {v}"))

    print("pop() -> ", dq.pop())
    print("pop() -> ", dq.pop())
    print("pop() -> ", dq.pop())
    print("pop() -> ", dq.pop())


if __name__ == "__main__":
    main()
