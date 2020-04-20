package com.manhpd;

import java.util.Arrays;

/**
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 *
 * Ex1: Input: s = "anagram", t = "nagaram"
 *      Output: true
 *
 * Ex2: Input: s = "rat", t = "car"
 *      Output: false
 *
 */
public class ValidAnagram {

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";

        boolean result = isAnagram(s, t);
        System.out.println("Result: " + result);
    }

    /**
     * Using norway to iterate all elements of string s, t.
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] char_counts = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            char_counts[s.charAt(i) - 'a']++;
            char_counts[t.charAt(i) - 'a']--;
        }

        for (int count : char_counts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Using sorting to sort all characters of string s and t.
     *
     * Time complexity: O(n*log(n))
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagramBySorting(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();

        Arrays.sort(ss);
        Arrays.sort(tt);

        for (int i = 0; i < ss.length; ++i) {
            if (ss[i] != tt[i]) {
                return false;
            }
        }

        return true;
    }

}
