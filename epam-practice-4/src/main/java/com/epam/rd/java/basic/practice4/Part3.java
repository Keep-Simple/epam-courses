package com.epam.rd.java.basic.practice4;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Part3 {

    private static final Map<ValueTypes, List<String>> store = new EnumMap<>(ValueTypes.class);

    private static final Pattern charType = Pattern.compile("\\p{L}");
    private static final Pattern intType = Pattern.compile("-?\\d+");
    private static final Pattern doubleType = Pattern.compile("-?(\\d+)?(\\.\\d+)?");
    private static final Pattern stringType = Pattern.compile("\\p{L}{2,}");

    private static final Logger log = Logger.getLogger(Part3.class.getName());

    private static ValueTypes defineType(String value) {
        if (charType.matcher(value).matches())
            return ValueTypes.CHAR;

        if (intType.matcher(value).matches())
            return ValueTypes.INT;

        if (doubleType.matcher(value).matches())
            return ValueTypes.DOUBLE;

        if (stringType.matcher(value).matches())
            return ValueTypes.STRING;

        return null;
    }

    public static void main(String[] args) {
        initialize();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input = sc.next();
            ValueTypes type;

            try {
                type = ValueTypes.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                type = null;
            }


            if (type == ValueTypes.STOP) break;

            if (type == null) System.out.println("Incorrect input");
            else System.out.println(printList(store.get(type)));

        }
    }

    private static void initialize() {
        try (Stream<String> lines = Files.lines(Paths.get("part3.txt"), Charset.forName("cp1251"))) {
            store.put(ValueTypes.CHAR, new LinkedList<>());
            store.put(ValueTypes.STRING, new LinkedList<>());
            store.put(ValueTypes.INT, new LinkedList<>());
            store.put(ValueTypes.DOUBLE, new LinkedList<>());


            lines.parallel()
                    .map(l -> l.split(" "))
                    .flatMap(Stream::of)
                    .forEach(s -> store.get(defineType(s)).add(s));

        } catch (IOException e) {
            log.log(Level.SEVERE, null, e);
        }
    }

    private static String printList(List<String> list) {
        StringBuilder sb = new StringBuilder();
        list.forEach(w -> sb.append(w).append(' '));
        return sb.toString();
    }

    enum ValueTypes {CHAR, INT, DOUBLE, STRING, STOP}
}
