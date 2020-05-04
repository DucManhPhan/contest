package com.manhpd;

/**
 * Given an arbitrary ransom note string and another string containing letters from all the magazines,
 * write a function that will return true if the ransom note can be constructed from the magazines ;
 * otherwise, it will return false.
 * Each letter in the magazine string can only be used once in your ransom note.
 *
 * Note:
 * You may assume that both strings contain only lowercase letters.
 *
 * canConstruct("a", "b") -> false
 * canConstruct("aa", "ab") -> false
 * canConstruct("aa", "aab") -> true
 *
 */
public class RansomNote {

    public static void main(String[] args) {
        String rasomNote = "aa";
        String magazine = "aab";

//        String rasomNote = "fffbfg";
//        String magazine = "effjfggbffjdgbjjhhdegh";

        boolean isContain = canConstruct(rasomNote, magazine);
        System.out.println(isContain);
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] letters = new int[128];
        for (char c : ransomNote.toCharArray()) ++letters[c - 'a'];
        for (char c : magazine.toCharArray()) --letters[c - 'a'];

        for (int i = 0; i < letters.length; ++i) {
            if (letters[i] > 0) {
                return false;
            }
        }

        return true;
    }

}
