package com.manhpd;

import java.util.HashMap;
import java.util.Map;

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

//        boolean result = findPermutation(str, pattern);
        boolean result = findPermutationSlidingWindow(str, pattern);
        System.out.println("Result: " + result);
    }

    /**
     * Using Sliding window way
     *
     * Time complexity: O(n * k)
     *
     * @param str
     * @param pattern
     * @return
     */
    public static boolean findPermutation(String str, String pattern) {
        int windowStart = 0;
        int steps = pattern.length();

        for (int windowEnd = 0; windowEnd < str.length(); ++windowEnd) {
            if (windowEnd - windowStart + 1 > steps) {
                ++windowStart;
            }

            if (windowEnd - windowStart + 1 == steps) {
                String item = str.substring(windowStart, windowEnd + 1);
                boolean isAnagram = isAnagram(item, pattern);

                if (isAnagram) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Using normal way to calculate substr string and pattern string is anagram with each other
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

    /**
     * Using optimization sliding window to solve this problem.
     *
     * Time complexity: O(n + m)
     *
     * @param str
     * @param pattern
     * @return
     */
    public static boolean findPermutationSlidingWindow(String str, String pattern) {
        int windowStart = 0;
        int matched = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();

        for (char chr : pattern.toCharArray()) {
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);
        }

        for (int windowEnd = 0; windowEnd < str.length(); ++windowEnd) {
            char rightChar = str.charAt(windowEnd);
            if (charFrequencyMap.containsKey(rightChar)) {
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
                if (charFrequencyMap.get(rightChar) == 0) {
                    ++matched;
                }
            }

            if (matched == pattern.length()) {
                return true;
            }

            if (windowEnd - windowStart + 1 > pattern.length()) {
                char leftChar = str.charAt(windowStart++);
                if (charFrequencyMap.containsKey(leftChar)) {
                    --matched;
                    charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
                }
            }
        }

        return false;
    }

}
