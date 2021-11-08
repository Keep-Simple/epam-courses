package com.epam.rd.java.basic.practice4;


import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Part6 {

    private static final Map<ValueTypes, List<String>> store = new EnumMap<>(ValueTypes.class);
    private static final Pattern latinType = Pattern.compile("[a-zA-Z]+");
    private static final Pattern cyrillicType = Pattern.compile("\\p{IsCyrillic}+");
    private static final Logger log = Logger.getLogger(Part6.class.getName());
    private static Scanner scanner = new Scanner(System.in);

    private static ValueTypes defineType(String value) {
        if (cyrillicType.matcher(value).matches()) {
            return ValueTypes.CYRL;
        }

        if (latinType.matcher(value).matches())
            return ValueTypes.LATN;

        return null;
    }

    public static void main(String[] args) {
        initialize();

        while (scanner.hasNext()) {
            String input = scanner.next();
            ValueTypes type;

            try {
                type = ValueTypes.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                type = null;
            }


            if (type == ValueTypes.STOP) {
                scanner.close();
                return;
            }

            if (type == null) System.out.println("smth: Incorrect input");
            else System.out.println(printList(store.get(type), type));

        }
    }

    private static void initialize() {
        try (Stream<String> lines = Files.lines(Paths.get("part6.txt"), Charset.forName("cp1251"))) {
            scanner = new Scanner(System.in);

            store.put(ValueTypes.CYRL, new LinkedList<>());
            store.put(ValueTypes.LATN, new LinkedList<>());

            lines
                    .map(l -> l.split("[^\\p{L}]"))
                    .flatMap(Stream::of)
                    .filter(w -> w.length() > 0)
                    .forEach(s -> store.get(defineType(s)).add(s));

        } catch (Exception e) {
            log.log(Level.SEVERE, null, e);
        }
    }

    private static String printList(List<String> list, ValueTypes type) {
        StringBuilder sb = new StringBuilder();
        sb.append(type == ValueTypes.CYRL ? "Cyrl: " : "Latn: ");
        list.forEach(w -> sb.append(w).append(' '));
        return sb.toString();
    }

    enum ValueTypes {LATN, CYRL, STOP}
}
