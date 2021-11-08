package com.epam.rd.java.basic.practice5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part4 {
    private static final String FILE = "part4.txt";

    public static void main(final String[] args) {
        File file = new File(Demo.FILE, FILE);

        if (!file.exists()) {
            createFile();
        }

        int[][] intArr = readFile(file);

        int max = Integer.MIN_VALUE;

        ExecutorService executor = Executors.newFixedThreadPool(intArr.length);
        List<Future<Integer>> list = new ArrayList<>();

        long timeMs = System.currentTimeMillis();

        for (int[] ints : intArr) {
            OwnCallable callThread = new OwnCallable();
            callThread.setMatrix(ints);

            Future<Integer> future = executor.submit(callThread);
            list.add(future);
        }

        for(Future<Integer> fut : list){
            try {
                int a = fut.get();
                if (a > max) max = a;
                Demo.sleepFor(1);
            } catch (InterruptedException | ExecutionException e) {
                Logger.getLogger(Part4.class.getName()).log(Level.SEVERE, null, e);
                Thread.currentThread().interrupt();
            }
        }

        timeMs = System.currentTimeMillis() - timeMs;
        System.out.println(max);
        System.out.println(timeMs);
        executor.shutdown();

        max = Integer.MIN_VALUE;
        timeMs = System.currentTimeMillis();

        for (int[] ints : intArr) {
            for (int anInt : ints) {
                if (max < anInt) max = anInt;
                Demo.sleepFor(1);
            }
        }

        timeMs = System.currentTimeMillis() - timeMs;

        System.out.println(max);
        System.out.println(timeMs);
    }

    private static void createFile(){
        int m = Demo.randomNum(30) + 1;
        int n = Demo.randomNum(200) + 1;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(Demo.randomNum(998)+1).append(' ');
            }
            sb.deleteCharAt(sb.length()-1).append(System.lineSeparator());
        }

        Demo.write(FILE, sb.toString().trim());
    }

    private static int[][] readFile(File file) {
        char[] arr = new char[0];
        try (InputStream fileInputStream = new FileInputStream(file);
             Reader reader = new InputStreamReader(fileInputStream, Demo.ENCODING);) {
            arr = new char[(int)file.length()];
            int pointer = reader.read();
            for (int i = 0; (pointer > 0) && (i < arr.length) ; i++) {
                arr[i] = (char)pointer;
                pointer = reader.read();
            }
        } catch (IOException ex) {
            Logger.getLogger(Part1.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[] lines = new String(arr).split(System.lineSeparator());

        String[][] matrix = new String[lines.length][];
        int maxLengthOfElementsInLine = 0;
        for (int j = 0; j < matrix.length; j++) {
            matrix[j] = lines[j].split(" ");
            if (maxLengthOfElementsInLine < matrix[j].length) maxLengthOfElementsInLine = matrix[j].length;
        }

        int[][] matrixArr = getMatrix(matrix.length, maxLengthOfElementsInLine);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length ; j++) {
                try {
                    matrixArr[i][j] = Integer.parseInt(matrix[i][j]);
                } catch (NumberFormatException e) {
                    matrixArr[i][j] = Integer.MIN_VALUE;
                }
            }
        }
        return matrixArr;
    }

    private static int[][] getMatrix(int height, int width){
        int[][] matrixArr = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matrixArr[i][j] = Integer.MIN_VALUE;
            }
        }

        return matrixArr;
    }


    static class OwnCallable implements Callable<Integer> {
        int[] matrix;

        public void setMatrix(int[] matrix) {
            this.matrix = matrix;
        }

        @Override
        public Integer call() {
            int max = Integer.MIN_VALUE;
            if ((matrix!=null)&&(matrix.length>0)){
                for (int b : matrix){
                    if (b > max) max = b;
                }
            }
            return max;
        }
    }

}
