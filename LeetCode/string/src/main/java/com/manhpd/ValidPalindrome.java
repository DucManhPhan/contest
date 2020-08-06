package com.manhpd;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 *
 * Example 1: Input: "A man, a plan, a canal: Panama"
 *            Output: true
 *
 * Example 2: Input: "race a car"
 *            Output: false
 *
 */
public class ValidPalindrome {

    public static void main(String[] args) {
//        String s = "A man, a plan, a canal: Panama";
//        String s = "race a car";
        String s = "Marge, let's \"went.\" I await news telegram.";
        boolean res = isPalindrome0(s);
//        boolean res = isPalindrome(s);

        System.out.println(res);
    }

    public static boolean isPalindrome(String s) {
        if ("".equals(s)) {
            return true;
        }

        String specialChars = " ,!%$^&*()~`+=;:.@#-?'\"";
        for (int left = 0, right = s.length() - 1; left <= right; ++left, --right) {
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);

            while (specialChars.contains("" + s.charAt(left))) {
                ++left;
            }

            while (specialChars.contains("" + s.charAt(right))) {
                --right;
            }

            if (Character.toLowerCase(s.charAt(left)) !=
                    Character.toLowerCase(s.charAt(right))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isPalindrome0(String s) {
        int left = 0;
        int right = s.length() - 1;

        return isPalindrome0(s, left, right);
    }

    public static boolean isPalindrome0(String s, int left, int right) {
        if (left >= right) {
            return true;
        }

        while (!Character.isLetterOrDigit(s.charAt(left))) {
            ++left;
        }

        while (!Character.isLetterOrDigit(s.charAt(right))) {
            --right;
        }

        if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
            return false;
        }

        return isPalindrome0(s, ++left, --right);
    }

}
