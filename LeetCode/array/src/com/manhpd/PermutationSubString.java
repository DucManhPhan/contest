package com.manhpd;

/**
 * Given a string and a pattern, find out if the string contains any permutation of the pattern.
 *
 * Ex1: Input: String="oidbcaf", Pattern="abc"
 * Output: true
 * Explanation: The string contains "bca" which is a permutation of the given pattern.
 *
 * Ex2: Input: String="odicf", Pattern="dc"
 * Output: false
 * Explanation: No permutation of the pattern is present in the given string as a substring.
 *
 * Ex3: Input: String="bcdxabcdy", Pattern="bcdyabcdx"
 * Output: true
 * Explanation: Both the string and the pattern are a permutation of each other.
 *
 * Ex4: Input: String="aaacb", Pattern="abc"
 * Output: true
 * Explanation: The string contains "acb" which is a permutation of the given pattern.
 *
 */
public class PermutationSubString {

    public static void main(String[] args) {
        String str = "oidbcaf";
        String pattern = "abc";

        boolean result = findPermutation(str, pattern);
        System.out.println("Result: " + result);
    }

    public static boolean findPermutation(String str, String pattern) {



        return false;
    }

    /**
     * Using normal way to calculate substr string and pattern string is anagram each other
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param subStr
     * @param pattern
     * @return
     */
    private static boolean isAnagram(String subStr, String pattern) {
        if (subStr.length() != pattern.length()) {
            return false;
        }

        int[] char_counts = new int[26];
        for (int i = 0; i < subStr.length(); ++i) {
            char_counts[subStr.charAt(i) - 'a']++;
            char_counts[pattern.charAt(i) - 'a']--;
        }

        for (int count : char_counts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

}
