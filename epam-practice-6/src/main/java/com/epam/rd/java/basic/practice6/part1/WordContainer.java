package com.epam.rd.java.basic.practice6.part1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordContainer {

	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
        Map<String, Word> words = new HashMap<>();

	    while (sc.hasNext()) {
	        String cur = sc.next();

	        if (cur.equalsIgnoreCase("stop")) {
	            break;
            }

	        if (words.containsKey(cur)) {
	            words.get(cur).increment();
            } else {
	            words.put(cur, new Word(cur));
            }
        }

	    words.values()
                .stream()
                .sorted()
                .forEach(System.out::println);
	}

}
