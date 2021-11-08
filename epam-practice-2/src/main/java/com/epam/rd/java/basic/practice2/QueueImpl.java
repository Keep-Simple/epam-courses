package com.epam.rd.java.basic.practice2;

import java.util.Iterator;

public class QueueImpl implements Queue {

    ListImpl list = new ListImpl();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

    public Iterator<Object> iterator() {
        return list.iterator();
    }

    @Override
    public void enqueue(Object element) {
        list.addLast(element);
    }

    @Override
    public Object dequeue() {
        if (list.size() == 0) {
            return null;
        }

        Object res = list.getFirst();
        list.removeFirst();

        return res;
    }

    @Override
    public Object top() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public static void main(String[] args) {
        // nothing to check
    }

}
