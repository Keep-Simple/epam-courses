package com.epam.rd.java.basic.practice5;

import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part2 {

    private static final InputStream IN = System.in;

    public static void main(final String[] args) {
        System.setIn(new InputStr());
        Thread t = new Thread(() -> Spam.main(new String[]{}));

        t.start();

        try {
            t.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Part2.class.getName()).log(Level.SEVERE, null, ex);
            Thread.currentThread().interrupt();
        }

        System.setIn(IN);
    }

    static class InputStr extends InputStream {
        @Override
        public int read() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Part2.class.getName()).log(Level.SEVERE, null, ex);
                Thread.currentThread().interrupt();
            }

            return -1;
        }

        @Override
        public int read(byte[] b, int off, int len) {
            return -1;
        }
    }

}
