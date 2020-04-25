package com.manhpd;

/**
 * Given an array of integers, calculate the fractions of its elements that are positive, negative, and are zeros.
 * Print the decimal value of each fraction on a new line.
 *
 * Constraints: 0 < n < 100
 *              -100 <= arr[i] <= 100
 *
 * Ex1: Input: -4 3 -9 0 4 1
 *      Output: 0.500000
 *              0.333333
 *              0.166667
 */
public class PlusMinus {

    public static void main(String[] args) {
        int[] arr = {-4, 3, -9, 0, 4, 1};
        plusMinus(arr);
    }

    private static void plusMinus(int[] arr) {
        int countPos = 0;
        int countNeg = 0;
        int countZero = 0;

        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] > 0) {
                ++countPos;
            } else if (arr[i] < 0) {
                ++countNeg;
            } else {
                ++countZero;
            }
        }

        System.out.format("%.6f%n%.6f%n%.6f%n", (float)countPos/arr.length, (float)countNeg/arr.length, (float)countZero/arr.length);
    }

}
