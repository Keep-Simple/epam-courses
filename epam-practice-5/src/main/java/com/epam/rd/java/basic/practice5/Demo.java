package com.epam.rd.java.basic.practice5;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Demo {
    static final String FILE = System.getProperty("user.dir");
    static final String ENCODING = "Cp1251";

    static void write(String nameFile, String text) {
        File file = new File(FILE, nameFile);
        try (PrintWriter pw = new PrintWriter(file, ENCODING)) {
            pw.write(text);
        } catch (IOException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static int randomNum(int n){
        return new SecureRandom().nextInt(n);
    }

    public static void sleepFor(long milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Part1.main(new String[]{});
    }

}
