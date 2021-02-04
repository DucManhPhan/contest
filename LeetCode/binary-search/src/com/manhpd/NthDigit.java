package com.manhpd;

import java.util.Arrays;

public class NthDigit {

    public static void main(String[] args) {
        int n = 197;

        NthDigit solution = new NthDigit();
        System.out.println(solution.findNthDigitII(n));
    }

    /**
     * Use brute force algorithm
     *
     * @param n
     * @return
     */
    public int findNthDigitI(int n) {
        int currentLength = 0;

        for (int i = 1; i < Integer.MAX_VALUE; ++i) {
            String value = String.valueOf(i);

            if (currentLength + value.length() >= n) {
                int len = 0;
                while (len < value.length()) {
                    ++currentLength;
                    if (currentLength == n) {
                        return Character.digit(value.charAt(len), 10);
                    }

                    ++len;
                }
            }

            currentLength += value.length();
        }

        return -1;
    }

    /**
     * Using binary search algorithm
     *
     * @param n
     * @return
     */
    public int findNthDigitII(int n) {
        int posOfBaseLength = Arrays.binarySearch(baseLengths, n);
        int digits = posOfBaseLength < 0 ? -posOfBaseLength : posOfBaseLength;

        int diff = n - baseLengths[digits - 1];
        int num = (int) Math.pow(10, digits - 1) + n/digits;
        int idx = n % digits;

        return this.findNthDigitII(num, idx);
    }

    private int findNthDigitII(int number, int idx) {

    }

    /**
     * The number of digits
     */
    private static int[] baseLengths = {0, 9, 189, 2889, 38889, 488889, 588889, 68888889, 788888889};

}