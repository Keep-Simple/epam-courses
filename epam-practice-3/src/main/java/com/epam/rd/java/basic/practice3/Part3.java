package com.epam.rd.java.basic.practice3;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    public static void main(String[] args) {
        // noting
    }

    public static String convert(String input) {
        String[] newWords = Arrays
                .stream(input.split("\\W"))
                .filter(w -> w.length() > 0)
                .map(w -> {
                    if (w.length() >= 3) {
                        char ch = w.charAt(0);
                        ch = Character.isLowerCase(ch) ? Character.toUpperCase(ch) : Character.toLowerCase(ch);
                        w = w.replaceFirst("^.", Character.toString(ch));
                    }
                    return w;
                })
                .map(String::trim)
                .toArray(String[]::new);

        return replaceAllWords(input, newWords);
    }

    public static String replaceAllWords(String input, String[] newWords) {
        Pattern pattern = Pattern.compile("\\b(\\w+?)\\b");
        Matcher matcher = pattern.matcher(input);

        StringBuilder sb = new StringBuilder(input);

        int i = 0;

        while (matcher.find()) {
            sb.replace(matcher.start(), matcher.end(), newWords[i++]);
        }

        return sb.toString();
    }
}
