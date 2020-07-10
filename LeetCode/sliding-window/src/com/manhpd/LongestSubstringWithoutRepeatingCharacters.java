package com.manhpd;

import java.util.Arrays;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *     Input: "abcabcbb"
 *     Output: 3
 *     Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 *     Input: "bbbbb"
 *     Output: 1
 *     Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 *     Input: "pwwkew"
 *     Output: 3
 *     Explanation: The answer is "wke", with the length of 3.
 *                  Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String s = "abcabcbb";
        int res = lengthOfLongestSubstring(s);
        System.out.println(res);
    }

    public static int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int[] ints = new int[26];
        int maxLength = Integer.MIN_VALUE;

        for (int size = 1; size < len; ++size) {
            for (int i = 0; i <= len - size; ++i) {
                boolean isDuplicated = false;
                String substring = s.substring(i, i + size);

                // input data into an ints array
                for (int j = 0; j < substring.length(); ++j) {
                    ints[substring.charAt(j) - 'a']++;
                }

                for (int j = 0; j < 26; ++j) {
                    if (ints[j] > 1) {
                        isDuplicated = true;
                        break;
                    }
                }

                if (!isDuplicated) {
                    maxLength = Math.max(maxLength, substring.length());
                }

                Arrays.fill(ints, 0);
            }
        }

        return maxLength;
    }

}
