package com.epam.rd.java.basic.practice6.part6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Part6 {

    static Map<String, String> map = new HashMap<>();

    public static void main(String[] args) {
        map.put(args[0], args[1]);
        map.put(args[2], args[3]);
        String fileName = map.get("-i") != null ? map.get("-i") : map.get("--input");
        String taskName = map.get("-t") != null ? map.get("-t") : map.get("--task");
        map.clear();

        try (Stream<String> stream = new BufferedReader(new FileReader(fileName)).lines()) {
            switch (taskName) {
                case "length":
                    Part62.run(stream);
                    break;
                case "duplicates":
                    Part63.run(stream);
                    break;
                default:
                    Part61.run(stream);
            }
        } catch (FileNotFoundException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage());
        }
    }

}
