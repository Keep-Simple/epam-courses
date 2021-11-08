package com.epam.rd.java.basic.practice6.part4;

import com.epam.rd.java.basic.practice6.part2.Part2;
import org.junit.Assert;
import org.junit.Test;

public class RangeTest {
    @Test
    public void test1() {
        Range range = new Range(2, 99, true);
        Range range1 = new Range(2, 92);
        Part4 part1 = new Part4();

        range.iterator().forEachRemaining(integer -> integer++);
        range.iterator().hasNext();
        range.iterator().next();

        Part4.main(null);
        Assert.assertTrue(true);
    }
}
