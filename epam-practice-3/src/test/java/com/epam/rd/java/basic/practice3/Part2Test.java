package com.epam.rd.java.basic.practice3;

import org.junit.Assert;
import org.junit.Test;

public class Part2Test {

    @Test
    public void test() {
        String res = Part1.convert1(
                "Login;Name;Email\n" +
                        "ivanov;Ivan Ivanov;ivanov@mail.com");

        Assert.assertEquals("ivanov: ivanov@mail.com\n", res);
    }

}
