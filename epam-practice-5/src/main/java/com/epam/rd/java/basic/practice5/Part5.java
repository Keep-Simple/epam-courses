package com.epam.rd.java.basic.practice5;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part5 {
    public static final String FILE = "part5.txt";
    public static int k = 10;
    public static Thread[] threads = new Thread[k];
    public static RandomAccessFile file;

    public static void main(final String[] args) {
        File file = new File(Demo.FILE, FILE);

        if (file.exists()) {
            try {
                Files.deleteIfExists(Paths.get(file.getPath()));
            } catch (IOException e) {
                Logger.getLogger(Part5.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        for (int i = 0; i < k; i++) {
            threads[i] = new MyTread();
            threads[i].setName(String.valueOf(i));
        }

        try {
            Part5.file = new RandomAccessFile(file, "rw");
        } catch (IOException e) {
            Logger.getLogger(Part5.class.getName()).log(Level.SEVERE, null, e);
        }

        for (int i = 0; i < k; i++) {
            threads[i].start();
            Demo.sleepFor(1);
        }

        for (int i = 0; i < k; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                Logger.getLogger(Part5.class.getName()).log(Level.SEVERE, null, e);
                Thread.currentThread().interrupt();
            }
        }

        try {
            Part5.file.seek(0);

            for (int i = 0; i < Part5.file.length(); i++) {
                System.out.print((char) Part5.file.read());
            }

            Part5.file.close();
        } catch (IOException e) {
            Logger.getLogger(Part5.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    static class MyTread extends Thread {
        @Override
        public void run() {
            synchronized (file) {
                try {
                    for (int i = 0; i < 20; i++) {
                        file.writeBytes(this.getName());
                    }
                    file.writeBytes(System.lineSeparator());
                } catch (IOException e) {
                    Logger.getLogger(Part5.class.getName()).log(Level.SEVERE, null, e);
                }}
        }
    }

}
