package com.epam.rd.java.basic.practice2;

import org.junit.Assert;
import org.junit.Test;

public class StackImplTest {


    @Test
    public void testStack() {
        StackImpl stack = new StackImpl();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        Assert.assertEquals(3, stack.pop());

        Assert.assertEquals(2, stack.size());

        Assert.assertEquals(2, stack.top());
    }
}
