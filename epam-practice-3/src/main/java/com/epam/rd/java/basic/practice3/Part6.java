package com.epam.rd.java.basic.practice3;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part6 {

    public static void main(String[] args) {
        // noting
    }

    public static String convert(String input) {

        String[] words = input.split("\\s");

        return getStream(input)
                .parallel()
                .map(line ->
                        Arrays
                                .stream(line.split(" "))
                                .map(w -> checkForDuplicates(words, w))
                                .collect(Collectors.joining(" "))
                )
                .collect(Collectors.joining("\n"));
    }

    private static String checkForDuplicates(String[] words, String word) {
        return hasDuplicates(words, word) ? "_" + word : word;
    }

    private static boolean hasDuplicates(String[] input, String word) {
        int count = 0;
        for (String i : input) {
            if (count >= 2) {
                return true;
            }
            if (i.equals(word)) {
                count++;
            }
        }
        return false;
    }

    private static Stream<String> getStream(String str) {
        return new BufferedReader(new StringReader(str)).lines();
    }

}
