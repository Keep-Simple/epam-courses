package com.epam.rd.java.basic.practice2;


import org.junit.Assert;
import org.junit.Test;

public class ListImplTest {

    @Test
    public void testList() {
        ListImpl list = new ListImpl();
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);

        Assert.assertEquals(1, list.getFirst());
        list.removeFirst();

        Assert.assertEquals(2, list.getFirst());
    }

}
