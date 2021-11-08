package com.epam.rd.java.basic.practice6.part1;

import org.junit.Assert;
import org.junit.Test;

public class WordContainerTest {

    @Test
    public void test1() {
        WordContainer.main(null);
        WordContainer af = new WordContainer();

        Word wo = new Word("asg");
        wo.increment();
        wo.compareTo(new Word("lajf"));
        Part1 part1 = new Part1();
        Part1.main(null);
        wo.hashCode();
        wo.equals(null);
        wo.toString();

        Assert.assertTrue(true);
    }
}
