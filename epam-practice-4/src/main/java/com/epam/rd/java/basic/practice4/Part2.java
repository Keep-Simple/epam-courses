package com.epam.rd.java.basic.practice4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part2 {

    private static final Logger log = Logger.getLogger(Part2.class.getName());

    public static void main(String[] args) {
        writeRandomNumber();
        readAndWriteSortedNumber();
    }

    private static void writeRandomNumber() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File("part2.txt")))) {

            String res = new SecureRandom()
                    .ints(10, 0, 50)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" "));

            System.out.println("input ==> " + res);
            writer.write(res);

        } catch (IOException e) {
            log.log(Level.SEVERE, null, e);
        }
    }

    private static void readAndWriteSortedNumber() {
        try (BufferedWriter newWriter = new BufferedWriter(new FileWriter(new File("part2_sorted.txt")));
             Stream<String> lines = Files.lines(Paths.get("part2.txt"))) {


            Optional<String> toWrite = lines.findFirst()
                    .map(l -> Arrays
                            .stream(l.split(" "))
                            .sorted(Comparator.comparingInt(Integer::parseInt))
                            .collect(Collectors.joining(" ")));

            toWrite.ifPresent(r -> {
                try {
                    newWriter.write(r);
                    System.out.println("output ==> " + r);
                } catch (IOException e) {
                    log.log(Level.SEVERE, null, e);
                }
            });

        } catch (IOException e) {
            log.log(Level.SEVERE, null, e);
        }
    }

}
