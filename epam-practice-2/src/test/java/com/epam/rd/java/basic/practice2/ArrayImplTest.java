package com.epam.rd.java.basic.practice2;

import org.junit.Assert;
import org.junit.Test;

public class ArrayImplTest {

    @Test
    public void testArray() {
        ArrayImpl ar = new ArrayImpl();
        ar.add(1);
        ar.add(2);
        ar.add(3);

        Assert.assertEquals(3, ar.get(2));
        ar.remove(0);

        Assert.assertEquals(2, ar.get(0));
    }
}
