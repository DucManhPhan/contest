package com.manhpd;

public class PermutationInString {

    public static void main(String[] args) {
//        String s1 = "adc";
        String s1 = "ccc";
//        String s2 = "dcda";
        String s2 = "cbac";

        boolean checkInclusion = checkInclusion(s1, s2);
        System.out.println(checkInclusion);
    }

    public static boolean checkInclusion(String s1, String s2) {
        int lengthS1 = s1.length();
        int lengthS2 = s2.length();

        for (int i = 0; i <= lengthS2 - lengthS1; ++i) {
            String sub = s2.substring(i, i + lengthS1);
            boolean isPermutation = isPermutation(s1, sub);
            if (isPermutation) return true;
        }

        return false;
    }

    public static boolean isPermutation(String s1, String s2) {
        int[] count = new int[26];

        for (char c : s2.toCharArray()) count[c - 'a']++;
        for (char c : s1.toCharArray()) count[c - 'a']--;

        for (int i : count) {
            if (i < 0) {
                return false;
            }
        }

        return true;
    }

}
