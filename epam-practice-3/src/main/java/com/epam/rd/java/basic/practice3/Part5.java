package com.epam.rd.java.basic.practice3;

import java.util.Arrays;

public class Part5 {

    public static void main(String[] args) {
        // noting
    }

    public static String decimal2Roman(int number) {
        char[] c = new char[101];
        int i = 0;

        if (number == 100) {
            return "C";
        }
        if (number >= 90) {
            i = subDigit('X', 'C', i, c);
            number = number % 10;
        }
        if (number >= 50) {
                i = digit('L', number / 50, i, c);
                number = number % 50;
        }
        if (number >= 40) {
            i = subDigit('X', 'L', i, c);
            number = number % 10;
        }
        if (number >= 10) {
            i = digit('X', number / 10, i, c);
            number = number % 10;
        }
        if (number == 9) {
            i = subDigit('I', 'X', i, c);
            number = 0;
        }
        if (number >= 5) {
            i = digit('V', number / 5, i, c);
            number = number % 5;
        }
        if (number == 4) {
            i = subDigit('I', 'V', i, c);
            number = 0;
        }
        if (number > 0) {
            digit('I', number, i, c);
        }

        int count = 0;
        for (char a : c) {
            if (a != '\u0000') {
                count++;
            }
        }

        c = Arrays.copyOfRange(c, 0, count);

        return String.valueOf(c);

    }

    public static int roman2Decimal(String str) {
        int res = 0;
        int i;

        for (i = 0; i < str.length(); i++) {
            int s1 = value(str.charAt(i));

            if (i + 1 < str.length()) {
                int s2 = value(str.charAt(i + 1));

                if (s1 >= s2) {
                    res = res + s1;
                } else {
                    res = res + s2 - s1;
                    i++;
                }
            } else {
                res = res + s1;
                i++;
            }
        }
        return res;
    }

    private static int subDigit(char num1, char num2, int i, char[] c) {
        c[i++] = num1;
        c[i++] = num2;
        return i;
    }

    private static int digit(char ch, int n, int i, char[] c) {
        for (int j = 0; j < n; j++) {
            c[i++] = ch;
        }
        return i;
    }

    private static int value(char r) {
        if (r == 'I')
            return 1;
        if (r == 'V')
            return 5;
        if (r == 'X')
            return 10;
        if (r == 'L')
            return 50;
        if (r == 'C')
            return 100;
        return -1;
    }

}
