package com.epam.rd.java.basic.practice6.part6;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part63 {
    private Part63() {}
    static void run(Stream<String> stream) {
        Set<String> set = new HashSet<>();
        List<String> words = new LinkedList<>();

        Set<String> duplicates = stream
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .map(w -> {words.add(w); return w;})
                .filter(w -> !set.add(w))
                .collect(Collectors.toSet());


        words.stream().filter(duplicates::contains)
                .distinct()
                .limit(3)
                .map(w -> new StringBuilder(w.toUpperCase()).reverse().toString())
                .forEachOrdered(System.out::println);
    }
}
