package com.epam.rd.java.basic.practice5;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Part1 {
    public static void main(String[] args) {
        try {
            for (int i = 0; i < 2; i++) {
                Thread child1 = startAndReturnSecondThread();
                Thread.sleep(2000);
                child1.interrupt();
                child1.join();
            }

        } catch (InterruptedException e) {
            Logger.getLogger(Part1.class.getName()).log(Level.SEVERE, null, e);
            Thread.currentThread().interrupt();
        }
    }

    private static Thread startAndReturnSecondThread() {
        Thread child = new Thread() {
            @Override
            public void run() {
                while (!this.isInterrupted()) {
                    System.out.println(getName());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Logger.getLogger(Part1.class.getName()).log(Level.SEVERE, null, e);
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };

        child.start();
        return child;
    }
}
