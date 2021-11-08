package com.epam.rd.java.basic.practice5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Spam {
    private Thread[] threads;
    public static String str;

    public Spam(final String[] messages, final int[] delays) {
        if ((messages != null) && (delays != null) && (messages.length == delays.length)) {
            threads = new Thread[messages.length];
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new Worker(messages[i], delays[i]);
            }
        }
    }

    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void stop() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    private static class Worker extends Thread {
        int delay;
        String message;

        public Worker(String message, int delay) {
            this.delay = delay;
            this.message = message;
        }

        @Override
        public void run() {
            while (!this.isInterrupted()){
                System.out.println(message);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Spam.class.getName()).log(Level.SEVERE, null, ex);
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(final String[] args) {
        String[] messages = new String[] { "afaafa", "sdfgfs" , "rthrtht", "asdfdfg", "asdfgagfsd", "LKDJF"};
        int[] times = new int[] { 232, 234, 567, 678, 908, 568  };
        Spam spam = new Spam(messages, times);
        spam.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            str = reader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Spam.class.getName()).log(Level.SEVERE, null, ex);
        }

        spam.stop();

        for (int i = 0; i < spam.threads.length; i++) {
            try {
                spam.threads[i].join();
            } catch (InterruptedException e) {
                Logger.getLogger(Spam.class.getName()).log(Level.SEVERE, null, e);
                Thread.currentThread().interrupt();
            }
        }
    }

}
