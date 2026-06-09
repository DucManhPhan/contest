package com.manhpd;

/**
 * Refer: https://leetcode.com/problems/merge-strings-alternately/description/?envType=study-plan-v2&envId=leetcode-75
 *
 * You are given two strings word1 and word2.
 * Merge the strings by adding letters in alternating order, starting with word1.
 * If a string is longer than the other, append the additional letters onto the end of the merged string.
 *
 * Return the merged string.
 *
 * Example 1:
 * Input: word1 = "abc", word2 = "pqr"
 * Output: "apbqcr"
 * Explanation: The merged string will be merged as so:
 * word1:  a   b   c
 * word2:    p   q   r
 * merged: a p b q c r
 *
 * Example 2:
 * Input: word1 = "ab", word2 = "pqrs"
 * Output: "apbqrs"
 * Explanation: Notice that as word2 is longer, "rs" is appended to the end.
 * word1:  a   b
 * word2:    p   q   r   s
 * merged: a p b q   r   s
 *
 * Example 3:
 * Input: word1 = "abcd", word2 = "pq"
 * Output: "apbqcd"
 * Explanation: Notice that as word1 is longer, "cd" is appended to the end.
 * word1:  a   b   c   d
 * word2:    p   q
 * merged: a p b q c   d
 *
 * Constraints:
 *  1 <= word1.length, word2.length <= 100
 *  word1 and word2 consist of lowercase English letters.
 *
 */
public class MergeStringsAlternatively {

    public static void main(String[] args) {
        // Example 1
//        String word1 = "abc";
//        String word2 = "pqr";
//        String expected = "apbqcr";

        // Example 2
        String word1 = "ab";
        String word2 = "pqrs";
        String expected = "apbqrs";

//        String result = mergeAlternately1(word1, word2);
        String result = mergeAlternately2(word1, word2);
        System.out.println("Result = " + result + ", expected = " + expected);
        System.out.println("Matched or not: " + expected.equals(result));
    }

    /**
     * Using brute-force solution.
     *
     * Iterate each strings and add them into result string.
     *
     * @param word1
     * @param word2
     * @return
     */
    private static String mergeAlternately1(String word1, String word2) {
        StringBuffer result = new StringBuffer();
        int minLength = Math.min(word1.length(), word2.length());

        for (int i = 0; i < minLength; ++i) {
            result.append(word1.charAt(i));
            result.append(word2.charAt(i));
        }

        String redundantString = word1.length() > minLength ? word1 : word2;
        for (int i = minLength; i < redundantString.length(); ++i) {
            result.append(redundantString.charAt(i));
        }

        return result.toString();
    }

    /**
     * Using two-pointers technique.
     *
     * @param word1
     * @param word2
     * @return
     */
    private static String mergeAlternately2(String word1, String word2) {
        int iWord1 = 0;
        int iWord2 = 0;
        int maxLength = Math.max(word1.length(), word2.length());

        StringBuffer result = new StringBuffer();
        while (iWord1 < maxLength || iWord2 < maxLength) {
            if (iWord1 < word1.length()) {
                result.append(word1.charAt(iWord1));
            }

            if (iWord2 < word2.length()) {
                result.append(word2.charAt(iWord2));
            }

            iWord1++;
            iWord2++;
        }

        return result.toString();
    }

    /**
     * Using stack to solve it.
     * This way is the same with the first way.
     *
     * @param word1
     * @param word2
     * @return
     */
    private static String mergeAlternately3(String word1, String word2) {
        

        return "";
    }
}
