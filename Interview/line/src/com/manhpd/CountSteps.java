package com.manhpd;

/**
 * Ref: https://leetcode.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/
 * <p>
 * Given an integer under the binary format written as a string: "011100"
 * <p>
 * We will do two operations:
 * - if an integer is even, divide to 2.
 * - if an integer is odd, subtract to 1.
 * <p>
 * Then, we will do these operations until a result is 0. Calculate how many steps of these operations?
 * <p>
 * The length of string can be [1, 1000,000]
 * <p>
 * Example 1:
 * Input: s = "011100"
 * Output: 7
 */
public class CountSteps {

    public static void main(String[] args) {
//        String s = "011100";
        String s = "0111001111111111111111";
        int res = countNumberToZero(s);

        System.out.println("Result: " + res);
    }

    public static int countNumberToZero(String s) {
        int countSteps = 0;
        int carry = 0;

        for (int i = s.length() - 1; i >= 1; i--) {
            int rightMostBit = s.charAt(i) - '0';
            if ((rightMostBit + carry) == 1) {
                carry = 1;
                countSteps += 2;
            } else {
                countSteps++;
            }
        }

        return countSteps + carry;
    }

}
