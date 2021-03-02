package com.manhpd;

/**
 * https://www.hackerrank.com/challenges/strong-password/problem
 */
public class StrongPassword {

    public static void main(String[] args) {
        String password = "2bb#A";
//        String password = "#HackerRank";
//        String password = "2bbbb";
        int n = 5;

        System.out.println(minimumNumber(n, password));
    }

    public static int minimumNumber(int n, String password) {
        String numbers = "0123456789";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String specialCharacters = "!@#$%^&*()-+";

        int numDigits = 0;
        int numLowerCase = 0;
        int numUpperCase = 0;
        int numSpecialCharacters = 0;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                numDigits++;
            } else if (Character.isLowerCase(c)) {
                numLowerCase++;
            } else if (Character.isUpperCase(c)) {
                numUpperCase++;
            } else if (specialCharacters.contains(String.valueOf(c))) {
                numSpecialCharacters++;
            }
        }

        int numRes = 0;

        if (numDigits >= 1) {
            numRes++;
        }

        if (numLowerCase >= 1) {
            numRes++;
        }

        if (numUpperCase >= 1) {
            numRes++;
        }

        if (numSpecialCharacters >= 1) {
            numRes++;
        }

        if (numRes < 4) {
            if (4 - numRes + password.length() >= 6) {
                return 4 - numRes;
            } else {
                return 6 - password.length();
            }
        }

        return 0;
    }

}
