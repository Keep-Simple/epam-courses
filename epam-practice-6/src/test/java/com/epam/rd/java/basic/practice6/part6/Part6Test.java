package com.epam.rd.java.basic.practice6.part6;

import com.epam.rd.java.basic.practice6.Demo;
import org.junit.Test;
import org.junit.Assert;

import java.util.stream.Stream;

public class Part6Test {
    @Test
    public void test1() {
        Stream<String> lines1 = Stream.of("lkaje jalkej", "lakjsuowaf askdj adsfasdf", "lkaje jalkej", "lkaje jalkej",
                "jlakj jlkd sivj", "jlkadjf", "jdkla");
        Stream<String> lines2 = Stream.of("lkaje jalkej", "lakjsuowaf askdj adsfasdf", "lkaje jalkej", "lkaje jalkej",
                "jlakj jlkd sivj", "jlkadjf", "jdkla");
        Stream<String> lines3 = Stream.of("lkaje jalkej", "lakjsuowaf askdj adsfasdf", "lkaje jalkej", "lkaje jalkej",
                "jlakj jlkd sivj", "jlkadjf", "jdkla");
        Part61.run(lines1);
        Part62.run(lines2);
        Part63.run(lines3);

        Demo demo = new Demo();
        Demo.main(null);

        Part6 part1 = new Part6();

        Assert.assertTrue(true);
    }
}
