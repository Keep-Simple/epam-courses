package com.epam.rd.java.basic.practice2;

import org.junit.Assert;
import org.junit.Test;

public class QueueImplTest {

    @Test
    public void testQueue() {
        QueueImpl queue = new QueueImpl();

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        Assert.assertEquals(1, queue.top());
        queue.dequeue();

        Assert.assertEquals(2, queue.top());

        queue.clear();

        Assert.assertEquals(0, queue.size());
    }
}
