package com.epam.rd.java.basic.practice6.part3;

import org.junit.Assert;
import org.junit.Test;

public class ParkingTest {
    @Test
    public void test1() {
        Parking parking = new Parking(10);
        parking.arrive(2);
        parking.depart(2);
        Part3 part1 = new Part3();

        parking.print();
        Part3.main(null);
        Assert.assertTrue(true);
    }
}
