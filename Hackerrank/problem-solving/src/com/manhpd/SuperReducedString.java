package com.manhpd;

/**
 * https://www.hackerrank.com/challenges/reduced-string/problem
 */
public class SuperReducedString {

    public static void main(String[] args) {
//        String s = "aaabccddd";
        String s = "abba";
        System.out.println(superReducedString(s));
    }

    public static String superReducedString(String s) {
        char[] chars = s.toCharArray();
        int start = 0;
        int end = 1;
        int excludedChars = 0;

        while (end < chars.length - excludedChars) {
            if (chars[start] == chars[end] && end <= chars.length - 1) {
                int i = start;
                int j = end;
                excludedChars += 2;
                while (i < chars.length - excludedChars) {
                    chars[i] = chars[j + 1];
                    ++i;
                    ++j;
                }
            } else {
                ++start;
                ++end;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length - excludedChars; ++i) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }

}
