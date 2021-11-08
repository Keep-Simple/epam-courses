package com.epam.rd.java.basic.practice6.part3;

import java.util.function.Function;
import java.util.stream.IntStream;

public class Parking {

    private final boolean[] parked;

    public Parking(int capacity) {
        this.parked = new boolean[capacity];
    }

    public boolean arrive(int k) {
        if (k >= parked.length || k < 0) throw new IllegalArgumentException();

        if (parkHelper(k)) return true;

        for (int i = k; i < parked.length; i++)
            if (parkHelper(i)) return true;

        for (int i = 0; i < k; i++)
            if (parkHelper(i)) return true;

        return false;
    }

    public boolean depart(int k) {
        if (parked[k]) {
            parked[k] = false;
            return true;
        }
        return false;
    }

    public void print() {
        IntStream.range(0, parked.length)
                .mapToObj(idx -> parked[idx])
                .map(this.printHelper())
                .forEach(System.out::print);

        System.out.println();
    }

    private boolean parkHelper(int k) {
        if (!parked[k]) {
            parked[k] = true;
            return true;
        }
        return false;
    }

    private Function<Boolean, Integer> printHelper() {
        return val -> {
            if (val) return 1;
            return 0;
        };
    }
}
