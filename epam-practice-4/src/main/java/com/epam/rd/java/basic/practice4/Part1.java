package com.epam.rd.java.basic.practice4;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {

    public static void main(String[] args) {

        try (Stream<String> lines = Files.lines(Paths.get("part1.txt"), Charset.forName("cp1251"))) {

            String result = lines.map(l -> Arrays
                    .stream(l.split(" "))
                    .map(w -> w.length() > 3 ? w.substring(2) : w)
                    .collect(Collectors.joining(" "))
            ).collect(Collectors.joining("\n"));

            System.out.println(result);

        } catch (IOException e) {
            Logger.getLogger(Part1.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
