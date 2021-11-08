package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    public ArrayImpl(int ignore) { }
    public ArrayImpl() { }

    private Object[] arr = new Object[16];
    private int pseudoLength = 0;

	@Override
    public void clear() {
        pseudoLength = 0;
        arr = new Object[16];
    }

	@Override
    public int size() {
        return pseudoLength;
    }

	@Override
    public Iterator<Object> iterator() {
	    return new IteratorImpl();
    }

	private class IteratorImpl implements Iterator<Object> {
	    private int position = 0;

        @Override
        public boolean hasNext() {
            return position < pseudoLength;
        }

        @Override
        public Object next() {
            if (position > pseudoLength) {
                throw new NoSuchElementException();
            }
            return arr[position++];
        }

    }

	@Override
    public void add(Object element) {
        extendArraySizeIfNeeded(0);
        arr[pseudoLength++] = element;
    }

	@Override
    public void set(int index, Object element) {

	    if (index < 0) {
	        throw new ArrayIndexOutOfBoundsException(index);
        }

        if (extendArraySizeIfNeeded(index)) {
            pseudoLength = index + 1;
        }

        arr[index] = element;
    }

	@Override
    public Object get(int index) {
        if (index > pseudoLength) {
            throw new NoSuchElementException();
        }

	    return arr[index];
    }

	@Override
    public int indexOf(Object element) {
        for (int i = 0; i < pseudoLength; i++) {
            if (arr[i] == element || arr[i] != null && arr[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

	@Override
    public void remove(int index) {
        if (index < 0 || index >= pseudoLength) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        for (int i = index; i < pseudoLength-1; i++) {
                arr[i] = arr[i+1];
        }

        --pseudoLength;
    }

    @Override
    public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");

        for (int i = 0; i < pseudoLength; i++) {
            sb.append(arr[i]);

            if (i != pseudoLength-1) {
                sb.append(", ");

            }
        }
        sb.append("]");

        return sb.toString();
    }

    private boolean extendArraySizeIfNeeded(int index) {
        if (pseudoLength == arr.length || index >= arr.length) {
            int newCapacity = pseudoLength + (pseudoLength >> 1);
            Object[] newArr = new Object[newCapacity];

            System.arraycopy(arr, 0, newArr, 0, arr.length);

            arr = newArr;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        // nothing to check
    }

}
