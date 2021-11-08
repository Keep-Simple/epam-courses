package com.epam.rd.java.basic.practice3;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {

    public static void main(String[] args) {
        // noting
    }

    public static String convert1(String input) {
        return deleteFirstLine(input.replaceAll(";.+?;", ": ")).concat("\n");
    }

    public static String convert2(String input) {
        input = deleteFirstLine(input);

        return getStream(input)
                .parallel()
                .map(s -> s.replaceFirst("^.+?;", ""))
                .map(s -> {
                    String name = s.substring(0, s.indexOf(' '));
                    String surname = s.substring(s.indexOf(' ') + 1, s.indexOf(';'));
                    return s.replaceFirst("^.+?\\s.+;", surname + ' ' + name + ';');
                })
                .map(s -> s.replaceFirst(";", " (email: "))
                .map(s -> s.concat(")"))
                .collect(Collectors.joining("\n")).concat("\n");
    }

    public static String convert3(String input) {
        input = deleteFirstLine(input);

        String[] emails = new String[10000];
        String[] names = new String[10000];
        AtomicReference<Integer> i = new AtomicReference<>();
        i.set(0);

        getStream(input)
                .forEach(line -> {
                    String name = line.substring(0, line.indexOf(';'));
                    String email = line.substring(line.lastIndexOf('@') + 1);

                    emails[i.get()] = email;
                    names[i.get()] = name;

                    i.set(i.get() + 1);
                });

        StringBuilder sb = new StringBuilder();

        for (int j = 0; names[j] != null; j++) {
            if (!emails[j].equals("")) {
                sb.append(emails[j]).append(" ==> ");

                String temp = emails[j];

                for (int k = 0; emails[k] != null; k++) {
                    if (emails[k].equals(temp)) {
                        sb.append(names[k]).append(", ");
                        emails[k] = "";
                    }
                }
                sb.delete(sb.length() - 2, sb.length());
                sb.append('\n');
            }
        }

        return sb.toString();
    }

    public static String convert4(String input) {
        String start = "Login;Name;Email;Password\n";
        String temp = deleteFirstLine(input);
        return start.concat(
                getStream(temp)
                        .map(s -> s.concat(";8023"))
                        .collect(Collectors.joining("\n"))
        );

    }

    private static String deleteFirstLine(String res) {
        return res.substring(res.indexOf('\n') + 1);
    }

    private static Stream<String> getStream(String str) {
        return new BufferedReader(new StringReader(str)).lines();
    }
}
