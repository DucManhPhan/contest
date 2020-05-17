package com.manhpd;

/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 *
 * Ex1: Input: 16
 * Output: true
 *
 * Ex2: Input: 14
 * Output: false
 *
 */
public class ValidPerfectSquare {

    public static void main(String[] args) {
        int num = 808201;
        boolean isPerfectSquare = isPerfectSquare(num);

        System.out.println(isPerfectSquare);
    }

    public static boolean isPerfectSquare(int num) {
        long val = sqrt(num);
        return (val * val) == num;
    }

    public static long sqrt(int num) {
        long left = 0;
        long right = num / 2;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            long square = mid * mid;

            if (square == num) {
                return mid;
            } else if (square < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

}
