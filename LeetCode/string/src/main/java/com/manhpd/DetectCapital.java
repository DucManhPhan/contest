package com.manhpd;

/**
 * Given a word, you need to judge whether the usage of capitals in it is right or not.
 *
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 *
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "leetcode".
 * Only the first letter in this word is capital, like "Google".
 * Otherwise, we define that this word doesn't use capitals in a right way.
 *
 * Example 1:
 *
 * Input: "USA"
 * Output: True
 *
 *
 * Example 2:
 *
 * Input: "FlaG"
 * Output: False
 *
 */
public class DetectCapital {

    public static void main(String[] args) {
        String word = "ggg";
        boolean res = detectCapitalUse(word);
        System.out.println(res);
    }

    /**
     * Using improved version of the brute force algorithm
     *
     * @param word
     * @return
     */
    public static boolean detectCapitalUse(String word) {
        if (word == null || "".equals(word)) {
           return false;
        }

        boolean isCapitalFirstChar = false;
        Boolean isAnotherCapitalChar = null;
        if (Character.isUpperCase(word.charAt(0))) {
            isCapitalFirstChar = true;
        }

        for (int i = 1; i < word.length(); ++i) {
            char tmp = word.charAt(i);
            if (Character.isUpperCase(tmp)) {
                if (isCapitalFirstChar && (isAnotherCapitalChar != null) && !isAnotherCapitalChar) {
                    return false;
                }

                isAnotherCapitalChar = true;
            } else {
                if (isAnotherCapitalChar != null && isAnotherCapitalChar) {
                    return false;
                }

                isAnotherCapitalChar = false;
            }

            if (!isCapitalFirstChar && isAnotherCapitalChar) {
                return false;
            }
        }

        return true;
    }

}
