package com.epam.rd.java.basic.practice6.part6;

import java.util.Arrays;
import java.util.stream.Stream;

public class Part62 {
    private Part62() {}
    static void run(Stream<String> stream) {
        stream
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .sorted((w1, w2) -> w2.length() - w1.length())
                .limit(3)
                .forEachOrdered(w -> System.out.println(w + " ==> " + w.length()));
    }
}
