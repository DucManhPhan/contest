package com.manhpd;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.
 * In one operation, you can choose any character of the string and change it to any other uppercase English character.
 * Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.
 *
 * Ex1: Input: s = "ABAB", k = 2
 *      Output: 4
 *      Explanation:
 *              Replace the two 'A's with two 'B's or vice versa.
 *
 * Ex2: Input: s = "AABABBA", k = 1
 *      Output: 4
 *      Explanation:
 *              Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 *              The substring "BBBB" has the longest repeating letters, which is 4.
 *
 */
public class LongestRepeatingCharacterReplacement {

    public static void main(String[] args) {
//        String s = "ABAB";
//        int k = 2;

        String s = "AABABBAaaaabaaa";
        int k = 1;

        int result = characterReplacementSlidingWindow(s, k);
//        int result = characterReplacementNormal(s, k);
        System.out.println("Result: " + result);
    }

    /**
     * Using sliding window way
     *
     * Time complexity: O(n)
     *
     * @param s
     * @param k
     * @return
     */
    public static int characterReplacementSlidingWindow(String s, int k) {
        int length = s.length();
        int windowStart = 0;
        int maxLengthOfConsecutiveNumber = 0;
        int maxLengthSubString = 0;
        Map<Character, Integer> map = new HashMap<>();

        for (int windowEnd = 0; windowEnd < length; ++windowEnd) {
            char c = s.charAt(windowEnd);
            map.put(c, map.getOrDefault(c, 0) + 1);

            maxLengthOfConsecutiveNumber = Math.max(maxLengthOfConsecutiveNumber, map.get(c));
            if (windowEnd - windowStart + 1 - maxLengthOfConsecutiveNumber > k) {
                char leftCharacter = s.charAt(windowStart);
                map.put(leftCharacter, map.get(leftCharacter) - 1);

                ++windowStart;
            }

            maxLengthSubString = Math.max(maxLengthSubString, windowEnd - windowStart + 1);
        }

        return maxLengthSubString;
    }

    /**
     * Using brute force way
     * Time complexity: (O(26 * n^2))
     * @return
     */
    public static int characterReplacementBruteForce(String s, int k) {
        if (s.length() == 0) return 0;
        int ret = 1;
        for (int i = 0 ; i < s.length() ; i++) {
            int[] hist = new int[26];
            for (int j = i ; j < s.length() ; j++) {
                hist[s.charAt(j) - 'A']++;
                int mostFreqNumCount = compuateMaxFreqCount(hist);
                int len = j - i + 1;
                if (len - mostFreqNumCount > k) {
                    break;
                }
                ret = Math.max(ret, len);
            }
        }
        return ret;
    }

    private static int compuateMaxFreqCount(int[] hist) {
        int max = 0;
        for (int c : hist) {
            max = Math.max(c, max);
        }
        return max;
    }

    /**
     * Using brute force but don't need to keep calling compuateMaxFreqCount
     * beats 2.43% of java submissions
     *
     * @return int - the maximum length of substring after replaced characters
     */
    public static int characterReplacementBruteForceII(String s, int k) {
        if (s.length() == 0) return 0;
        int ret = 1;
        int[] hist = new int[26];
        int mostFreqNumCount = 0;
        for (int i = 0 ; i < s.length() ; i++) {
            for (int j = i ; j < s.length() ; j++) {
                hist[s.charAt(j) - 'A']++;
                mostFreqNumCount = Math.max(hist[s.charAt(j) - 'A'], mostFreqNumCount);
                int len = j - i + 1;
                if (len - mostFreqNumCount > k) {
                    hist[s.charAt(i) - 'A']--;
                    i++;
                    continue;
                }
                ret = Math.max(ret, len);
            }
        }
        return ret;
    }

    /**
     * Using sliding window but improve performance.
     * runtime beats 51.59% of java submissions.
     *
     * Time complexity: O(n)
     *
     * @param s
     * @param k
     * @return
     */
    public static int characterReplacementSlidingWindowII(String s, int k) {
        if (s.length() == 0) return 0;
        int ret = 1;
        int[] hist = new int[26];
        int mostFreqNumCount = 0;
        int i = 0, j = 0;
        while (j < s.length()) {
            hist[s.charAt(j) - 'A']++;
            int currMostFreqNumCount = Math.max(hist[s.charAt(j) - 'A'], mostFreqNumCount);
            int len = j - i + 1;
            while (len - currMostFreqNumCount > k) {
                hist[s.charAt(i) - 'A']--;
                currMostFreqNumCount = Math.max(hist[s.charAt(i) - 'A'], mostFreqNumCount);
                i++;
                len = j - i + 1;
            }
            mostFreqNumCount = Math.max(mostFreqNumCount, currMostFreqNumCount);
            ret = Math.max(ret, len);
            j++;
        }
        return ret;
    }

}
