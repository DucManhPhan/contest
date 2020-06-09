package com.manhpd;


/**
 * Given a string s and a string t, check if s is subsequence of t.
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).
 *
 * Follow up:
 * If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?
 *
 * Example 1:
 * Input: s = "abc", t = "ahbgdc"
 * Output: true
 *
 * Example 2:
 * Input: s = "axc", t = "ahbgdc"
 * Output: false
 *
 * Constraints:
 * 0 <= s.length <= 100
 * 0 <= t.length <= 10^4
 * Both strings consists only of lowercase characters.
 *
 */
public class IsSubsequence {

    public static void main(String[] args) {
        String s = "axc";
        String t = "ahbgdc";

        boolean b = isSubsequence(s, t);
        System.out.println(b);
    }

    public static boolean isSubsequence(String s, String t) {
        int lent = t.length();
        int lens = s.length();
        int previousIndex = 0;
        boolean found = false;

        for (int i = 0; i < lens; ++i) {
            for (int j = previousIndex; j < lent; ++j) {
                if (t.charAt(j) == s.charAt(i)) {
                    previousIndex = j + 1;
                    found = true;
                    break;
                }
            }

            if (!found) {
                return false;
            } else {
                found = false;
            }
        }

        return true;
    }

}
