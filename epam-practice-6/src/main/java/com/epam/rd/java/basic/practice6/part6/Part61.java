package com.epam.rd.java.basic.practice6.part6;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part61 {
    private Part61() {}
    static void run(Stream<String> stream) {
        Map<String, Integer> map = new LinkedHashMap<>();

        stream.flatMap(line -> Arrays.stream(line.split(" ")))
                .forEachOrdered(w -> {
                    if(map.get(w) != null) map.replace(w, map.get(w) + 1);
                    else map.put(w, 1);
                });

        map.entrySet().stream()
                .sorted((v1, v2) -> v2.getValue().compareTo(v1.getValue()))
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                .entrySet().stream()
                .sorted((v1, v2) -> -v1.getKey().compareTo(v2.getKey()))
                .forEachOrdered(v -> System.out.println(v.getKey() + " ==> " + v.getValue()));
    }

}
