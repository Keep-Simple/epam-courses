package com.epam.rd.java.basic.practice6.part4;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class Range implements Iterable<Integer>{

    private final Integer[] arr;

    public Range(int n, int m) {
        this(n, m, false);
    }

    public Range(int firstBound, int secBound, boolean reversedOrder) {
        IntStream stream = IntStream.rangeClosed(firstBound, secBound);

        if (reversedOrder) {
            arr = stream
                    .boxed()
                    .sorted(Collections.reverseOrder())
                    .toArray(Integer[]::new);
        } else {
            arr = stream
                    .boxed()
                    .toArray(Integer[]::new);
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new IteratorImpl();
    }

    private final class IteratorImpl implements Iterator<Integer> {

        int pos = 0;

        @Override
        public boolean hasNext() {
            return pos < arr.length;
        }

        @Override
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return arr[pos++];
        }

    }

}
