package com.epam.rd.java.basic.practice2;

import java.util.Iterator;

public class StackImpl implements Stack {

    private final ListImpl list = new ListImpl();

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
    public void push(Object element) {
        list.addFirst(element);
    }

    @Override
    public Object pop() {
        if (list.isEmpty()) {
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
        if (list.size() == 0) {
            return "[]";
        }

        Iterator<Object> itr = iterator();
        Object[] ar = new Object[list.size()];
        int i = list.size() - 1;

        StringBuilder sb = new StringBuilder();

        while (itr.hasNext()) {
            ar[i--] = itr.next();
        }

        sb.append("[");
        for (Object o : ar)
            sb.append(o).append(", ");
        sb.delete(sb.length()-2,sb.length()).append("]");

        return sb.toString();
    }

    public static void main(String[] args) {
        // nothing to check
    }

}
