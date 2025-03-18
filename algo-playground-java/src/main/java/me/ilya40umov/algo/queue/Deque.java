package me.ilya40umov.algo.queue;

import java.util.ArrayList;
import java.util.List;

public class Deque<T> {

    private class Node {
        private final T value;
        private Node prev;
        private Node next;

        private Node(T value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;

    public Deque() {
        this.head = null;
        this.tail = null;
    }

    public List<T> getElements() {
        var elements = new ArrayList<T>();
        var node = head;
        while (node != null) {
            elements.add(node.value);
            node = node.next;
        }
        return elements;
    }

    public void enqueue(T value) {
        addLeft(value);
    }

    public T dequeue() {
        return removeRight();
    }

    public void addLeft(T value) {
        var node = new Node(value);
        node.next = head;
        if (head != null) {
            head.prev = node;
        }
        head = node;
        if (tail == null) {
            tail = node;
        }
    }

    public void addRight(T value) {
        var node = new Node(value);
        node.prev = tail;
        if (tail != null) {
            tail.next = node;
        }
        tail = node;
        if (head == null) {
            head = node;
        }
    }

    public T removeLeft() {
        if (head == null) {
            return null;
        }
        var result = head.value;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        return result;
    }

    public T removeRight() {
        if (tail == null) {
            return null;
        }
        var result = tail.value;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        return result;
    }

}
