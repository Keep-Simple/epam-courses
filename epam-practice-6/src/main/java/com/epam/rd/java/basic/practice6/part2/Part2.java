package com.epam.rd.java.basic.practice6.part2;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Part2 {

    public static void main(String[] args) {
        removeByIndex(IntStream
                .range(0, 7)
                .boxed()
                .collect(Collectors.toList()), 3);
    }

    public static long removeByIndex(final List<Integer> list, final int k) {
        long start = System.currentTimeMillis();
        int x = k - 1;

        while (!list.isEmpty()) {
            x %= list.size();
            list.remove(x);
            x += k - 1;
        }

        return System.currentTimeMillis() - start;
    }

    public static long removeByIterator(final List<Integer> list, int k) {
        long start = System.currentTimeMillis();

        int i = 1;

        while (!list.isEmpty()) {
            Iterator<Integer> iterator = list.listIterator();

            while (iterator.hasNext()) {
                iterator.next();
                if (i++ % k == 0)
                    iterator.remove();
            }

        }

        return System.currentTimeMillis() - start;
    }
}
