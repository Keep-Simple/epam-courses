package com.epam.rd.java.basic.practice3;

import java.util.Objects;

public class Part2 {

    public static void main(String[] args) {
        // noting
    }

    public static String convert(String input) {
        int maxLength = 0;
        int minLength = Integer.MAX_VALUE;

        String[] words = input.split("\\W");

        for (String i : words) {
            int length = i.length();
            if (length > 0) {
                maxLength = Math.max(length, maxLength);
                minLength = Math.min(length, minLength);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Min: ");

        for (String word : words) {
            if (word != null && word.length() == minLength) {
                removeByWord(words, word);
                sb.append(word).append(", ");
            }
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append('\n').append("Max: ");

        for (String word : words) {
            if (word != null && word.length() == maxLength) {
                removeByWord(words, word);
                sb.append(word).append(", ");
            }
        }

        return sb.delete(sb.length() - 2, sb.length()).toString();
    }

    private static void removeByWord(String[] arr, String word) {
        for (int i = 0; i < arr.length; i++) {
            if (Objects.equals(word, arr[i])) {
                arr[i] = null;
            }
        }
    }
}
