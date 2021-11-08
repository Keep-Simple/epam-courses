package com.epam.rd.java.basic.practice6.part3;

public class Part3 {

    public static void main(String[] args) {
        Parking parking = new Parking(10);

        parking.print();
        parking.arrive(1);
        parking.arrive(2);
        parking.print();
        parking.arrive(4);
        parking.arrive(7);
        parking.print();
        parking.arrive(9);
    }

}
