package com.manhpd;

/**
 * Given a string as an input.
 * We need to write a program that will print all non-empty substrings of that given string.
 *
 * Example 1:
 * Input :  abcd
 * Output :  a
 *           b
 *           c
 *           d
 *           ab
 *           bc
 *           cd
 *           abc
 *           bcd
 *           abcd
 *
 */
public class PrintAllSubstring {

    public static void main(String[] args) {
        String s = "abcd";
//        subStringByIndexes(s);
        subStringByLength(s);
    }

    /**
     * Using two start index and last index to scan substring
     *
     * @param str
     */
    public static void subStringByIndexes(String str) {
        int n = str.length();

        for (int start = 0; start < n; ++start) {
            for (int end = start + 1; end <= n; ++end) {
                String subString = str.substring(start, end);
                System.out.println(subString);
            }
        }
    }

    public static void subStringByLength(String str) {
        int n = str.length();

        for (int len = 1; len <= n; ++len) {
            for (int start = 0; start <= n - len; ++start) {
                String subString = str.substring(start, start + len);
                System.out.println(subString);
            }
        }
    }

}
