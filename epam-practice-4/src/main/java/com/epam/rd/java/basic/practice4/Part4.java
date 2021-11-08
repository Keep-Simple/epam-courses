package com.epam.rd.java.basic.practice4;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part4 implements Iterable<String> {

    private static final Pattern pattern = Pattern.compile("\\p{Lu}[^.]*\\.");
    private static final Logger log = Logger.getLogger(Part4.class.getName());
    private static Matcher matcher;
    private static String input;

    public static void main(String[] args) {
        initialize();
        new Part4().forEach(System.out::println);
    }

    private static void initialize() {
        try (Stream<String> lines = Files.lines(Paths.get("part4.txt"), Charset.forName("cp1251"))) {
            input = lines.collect(Collectors.joining(" "));
            matcher = pattern.matcher(input);
        } catch (IOException e) {
            log.log(Level.SEVERE, null, e);
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return matcher.find();
            }

            @Override
            public String next() {
                if (matcher.hitEnd()) throw new NoSuchElementException("Iterating beyond collection");

                return input.substring(matcher.start(), matcher.end());
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
