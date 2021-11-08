package com.epam.rd.java.basic.practice4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Part5 {

    private static final Logger log = Logger.getLogger(Part5.class.getName());

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (!sc.hasNext("\\bstop\\b")) {
            String keyword = sc.next();
            String locale = sc.next();

            System.out.println(getValue(keyword, locale).orElse("Nothing found"));
        }

        sc.close();
    }

    private static Optional<String> getValue(String keyword, String locale) {
        String str = String.format("resources_%s.properties", locale);
        Path read = Paths.get(System.getProperty("user.dir") + "/src/main/" + str);

        try (Stream<String> lines = Files.lines(read)) {

            return lines
                    .filter(l -> l.contains(keyword))
                    .findFirst()
                    .map(s -> s.replaceFirst("\\w+?\\s?=\\s?", ""))
                    .map(s -> {
                        if (s.charAt(0) != '\\') {
                            return s;
                        }

                        String[] arr = s.replace("\\", "").split("u");
                        StringBuilder text = new StringBuilder();
                        for (int i = 1; i < arr.length; i++) {
                            int hexVal = Integer.parseInt(arr[i], 16);
                            text.append((char) hexVal);
                        }

                        return text.toString();
                    });

        } catch (IOException e) {
            log.log(Level.SEVERE, null, e);
        }

        return Optional.empty();
    }

}
