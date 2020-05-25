package com.manhpd;

/**
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 *
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 *
 * Example 2:
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 *
 * Example 3:
 * Input: num = "10", k = 2
 * Output: "0"
 * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 *
 */
public class RemoveKDigits {

    public static void main(String[] args) {
//        String num = "10";
        String num = "1432219";
//        String num = "10200";
//        String num = "112";
        int k = 3;
//        int k = 1;

        String res = removeKdigits(num, k);
        System.out.println(res);
    }

    public static String removeKdigits(String num, int k) {
        int originKDigits = k;
        int len = num.length();
        if (len - k <= 0) {
            return "0";
        }

        char[] res = new char[len - k];
        int current = -1;
        for (int i = 0; i < len; ++i) {
            char c = num.charAt(i);


        }

        StringBuilder sb = new StringBuilder();
        for (char c : res) {
            if (c != '\u0000') {
                sb.append(c);
            }
        }

        return sb.toString();
    }

}
