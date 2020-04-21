package com.manhpd;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 *
 * Ex1: Input: S = "ADOBECODEBANC", T = "ABC"
 *      Output: "BANC"
 *
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        String result = minWindow(s, t);
        System.out.println("Result: " + result);
    }

    public static String minWindow(String s, String t) {
        int windowStart = 0;

        for (int windowEnd = 0; windowEnd < s.length(); ++windowEnd) {
            char rightCharacter = s.charAt(windowEnd);

        }

        return "";
    }

}
