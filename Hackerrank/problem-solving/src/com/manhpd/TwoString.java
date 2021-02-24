package com.manhpd;

/**
 * https://www.hackerrank.com/challenges/two-strings/problem
 */
public class TwoString {

    public static void main(String[] args) {
        String s1 = "hello";
        String s2 = "world";

        System.out.println(twoStrings1(s1, s2));
    }

    /**
     * Using trie data structure
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String twoStrings(String s1, String s2) {
        return "";
    }

    /**
     * Using hash map data structure
     *
     * @param s1
     * @param s2
     * @return
     */
    public static String twoStrings1(String s1, String s2) {
        int[] chars = new int[26];

        for (char c : s1.toCharArray()) {
            chars[c - 'a']++;
        }

        for (char c : s2.toCharArray()) {
            if (chars[c - 'a'] != 0) {
                return "YES";
            }
        }

        return "NO";
    }
}
