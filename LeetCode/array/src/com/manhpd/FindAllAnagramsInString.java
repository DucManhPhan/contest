package com.manhpd;

import java.util.*;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * The order of output does not matter.
 *
 * Ex1: Input: s: "cbaebabacd" p: "abc"
 *      Output: [0, 6]
 *
 *      Explanation:
 *              The substring with start index = 0 is "cba", which is an anagram of "abc".
 *              The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Ex2: Input: s: "abab" p: "ab"
 *      Output: [0, 1, 2]
 *
 *      Explanation:
 *              The substring with start index = 0 is "ab", which is an anagram of "ab".
 *              The substring with start index = 1 is "ba", which is an anagram of "ab".
 *              The substring with start index = 2 is "ab", which is an anagram of "ab".
 *
 */
public class FindAllAnagramsInString {

    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";

//        String s = "abab";
//        String p = "ab";

//        String s = "baa";
//        String p = "aa";

        List<Integer> results = findAnagrams(s, p);
        results.stream().forEach(System.out::println);
    }

    /**
     * Using sliding window but if pattern has multiple duplicated characters, this solution will be broken down.
     * Time complexity: O(n + m)
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagramsSomeCases(String s, String p) {
        int windowStart = 0;
        int matched = 0;
        Map<Character, Integer> frequencyCharMap = new HashMap<>();
        List<Integer> results = new ArrayList<>();

        for (char c : p.toCharArray()) {
            frequencyCharMap.put(c, frequencyCharMap.getOrDefault(c, 0) + 1);
        }

        for (int windowEnd = 0; windowEnd < s.length(); ++windowEnd) {
            char rightChar = s.charAt(windowEnd);
            if (frequencyCharMap.containsKey(rightChar)) {
                frequencyCharMap.put(rightChar, frequencyCharMap.get(rightChar) - 1);
                if (frequencyCharMap.get(rightChar) == 0) {
                    ++matched;
                }
            }

            if (matched == p.length()) {
                results.add(windowStart);
            }

            if (windowEnd >= p.length() - 1) {
                char leftChar = s.charAt(windowStart++);
                if (frequencyCharMap.containsKey(leftChar)) {
                    if (frequencyCharMap.get(leftChar) == 0) {
                        --matched;
                    }

                    frequencyCharMap.put(leftChar, frequencyCharMap.get(leftChar) + 1);
                }
            }
        }

        return results;
    }

    /**
     * Using sliding window with two hash map to save all frequencies of elements.
     * Time complexity: O(n + m)
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams(String s, String p) {
        int windowStart = 0;
        List<Integer> results = new ArrayList<>();
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> pMap = new HashMap<>();

        for (char pChar : p.toCharArray()) {
            pMap.put(pChar, pMap.getOrDefault(pChar, 0) + 1);
        }

        for (int windowEnd = 0; windowEnd < s.length(); ++windowEnd) {
            char sChar = s.charAt(windowEnd);
            sMap.put(sChar, sMap.getOrDefault(sChar, 0) + 1);

            if (windowEnd >= p.length()) {
                char leftChar = s.charAt(windowStart++);
                if (sMap.get(leftChar) == 1) {
                    sMap.remove(leftChar);
                } else {
                    sMap.put(leftChar, sMap.get(leftChar) - 1);
                }
            }

            if (sMap.equals(pMap)) {
                results.add(windowStart);
            }
        }

        return results;
    }

}
