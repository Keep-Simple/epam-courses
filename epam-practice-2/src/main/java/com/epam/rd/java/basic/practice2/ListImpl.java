package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {

    private int size = 0;
    private Node head = null;
    private Node last = null;

    @Override
    public void clear() {
        head = last = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private Node newNode = head;

        @Override
        public boolean hasNext() {
            return newNode != null;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Iterated beyond the end of the collection");
            }

            Object data = newNode.data;
            newNode = newNode.next;
            return data;
        }

    }

    @Override
    public void addFirst(Object elem) {
        if (isEmpty()) {
            head = last = new Node(elem, null, null);
        } else {
            head.prev = new Node (elem, null, head);
            head = head.prev;
        }
        size++;
    }

    @Override
    public void addLast(Object elem) {
        if (isEmpty()) {
            head = last = new Node(elem, null, null);
        } else {
            last.next = new Node(elem, last, null);
            last = last.next;
        }
        size++;
    }

    @Override
    public void removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty list");
        }

        head = head.next;
        --size;
    }

    @Override
    public void removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty list");
        }

        last = last.prev;
        --size;
    }

    @Override
    public Object getFirst() {
        if (isEmpty()) {
            return null;
        }
        return head.data;
    }

    @Override
    public Object getLast() {
        if (isEmpty()) {
            return null;
        }
        return last.data;
    }

    @Override
    public Object search(Object element) {
        for (Object val : this) {
            if (val == element || element != null && element.equals(val)) {
                return val;
            }
        }
        return null;
    }

    private void removeNode(Node node) {

        if (node.prev == null) {
            removeFirst();
            return;
        }

        if (node.next == null) {
            removeLast();
            return;
        }

        node.next.prev = node.prev;
        node.prev.next = node.next;

        --size;
    }

    @Override
    public boolean remove(Object obj) {
        Node newNode;

        for (newNode = head; newNode != null; newNode = newNode.next) {
            if (newNode.data == obj || obj != null && obj.equals(newNode.data)) {
                removeNode(newNode);
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Node newNode = head;

        while (newNode != null) {
            sb.append(newNode.data);

            if (newNode.next != null) {
                sb.append(", ");
            }

            newNode = newNode.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private static class Node {
        private final Object data;
        private Node next;
        private Node prev;

        public Node(Object data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        // nothing to check
    }
}
