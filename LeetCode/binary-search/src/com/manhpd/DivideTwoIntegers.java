package com.manhpd;

/**
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 * Return the quotient after dividing dividend by divisor.
 * The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.
 *
 * Example 1:
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Explanation: 10/3 = truncate(3.33333..) = 3.
 *
 * Example 2:
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Explanation: 7/-3 = truncate(-2.33333..) = -2.
 * Note:
 *
 * Both dividend and divisor will be 32-bit signed integers.
 * The divisor will never be 0.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−2^31,  2^31 − 1].
 * For the purpose of this problem, assume that your function returns 2^31 − 1 when the division result overflows.
 *
 */
public class DivideTwoIntegers {

    public static void main(String[] args) {
        int dividend = 10;
        int divisor = 3;

//        int res = divideUsingRecursion(dividend, divisor);
        int res = divideUsingBinarySearch(dividend, divisor);
        System.out.println(res);
    }

    /**
     * Using recursive version
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public static int divideUsingRecursion(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == -1) return Integer.MAX_VALUE;
        }

        long x = dividend < 0 ? -(long) dividend : (long) dividend;
        long y = divisor < 0 ? -(long) divisor : (long) divisor;

        int res = recurse(x, y, 1);
        if (dividend < 0 && divisor < 0) return res;
        if (dividend < 0 || divisor < 0) return -res;

        return res;
    }

    public static int recurse(long dividend, long divisor, int count) {
        if(dividend <= 0 || count == 0) return 0;
        if(divisor > dividend) return recurse(dividend, divisor >>> 1, count >>> 1); //overshot, so divide and try again.
        else return recurse(dividend - divisor, divisor + divisor, count + count)+count;
    }

    /**
     * Using Binary search algorithm
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public static int divideUsingBinarySearch(int dividend, int divisor) {
        if (divisor == -1 && dividend == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }

        if (divisor == -1) return -dividend;
        if (divisor == 1) return dividend;

        long left = 0;
        long right = Math.abs((long) dividend);
        long sign = dividend < 0 ^ divisor < 0 ? -1 : 1;

        while (left < right) {
            long mid = left + (right - left) / 2;

            if (Math.abs(divisor * mid) > Math.abs((long)dividend)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return (int) (sign * (left - 1));
    }

}
