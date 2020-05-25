package com.manhpd;

import java.util.HashMap;
import java.util.Map;

/**
 * Refer: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubStringWithoutRepeated {

    public static void main(String[] args) {
//        String s = "abcabcbb";
//        String s = "bbbbb";
//        String s = "pwwkew";
//        String s = "abccde";
//        String s = "dvdf";
        String s = "abbbb";
        int length = lengthOfLongestSubstring(s);

        System.out.println("Max length of the longest substring is: " + length);
    }

    public static int lengthOfLongestSubstring(String s) {
        int windowStart = 0;
        int size = s.length();
        Map<Character, Integer> mpCountCharacters = new HashMap<>();
        int maxLength = 0;

        for (int windowEnd = 0; windowEnd < size; ++windowEnd) {
            char rightChar = s.charAt(windowEnd);
            if (mpCountCharacters.containsKey(rightChar)) {
                windowStart = Math.max(windowStart, mpCountCharacters.get(rightChar) + 1);
            }

            mpCountCharacters.put(rightChar, windowEnd);
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

}
