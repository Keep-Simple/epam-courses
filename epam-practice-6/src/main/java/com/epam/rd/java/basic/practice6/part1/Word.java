package com.epam.rd.java.basic.practice6.part1;

import java.util.Objects;

public class Word implements Comparable<Word> {

	private final String content;

	private int frequency;

    public Word(String content) {
        this.frequency = 1;
        this.content = content;
    }

    @Override
    public int compareTo(Word o) {
        return o.frequency - this.frequency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        return Objects.equals(content, word.content);
    }

    @Override
    public int hashCode() {
        return content != null ? content.hashCode() : 0;
    }

    @Override
    public String toString() {
        return content + " : " + frequency;
    }

    public void increment() {
        this.frequency++;
    }
}
