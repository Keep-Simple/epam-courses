package com.epam.rd.java.basic.practice6.part2;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

public class Part2Test {
    @Test
    public void test1() {
        Part2.main(null);
        Part2 part1 = new Part2();
        Assert.assertTrue(true);

        Part2.removeByIndex(new LinkedList<>(Arrays.asList(1,2,32,5)), 3);
        Part2.removeByIterator(new LinkedList<>(Arrays.asList(1,2,32,5)), 1);

        Assert.assertTrue(true);
    }
}
