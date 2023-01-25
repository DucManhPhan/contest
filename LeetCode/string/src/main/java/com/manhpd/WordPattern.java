package com.manhpd;

import java.util.*;

/**
 * https://leetcode.com/problems/word-pattern/
 */
public class WordPattern {

    public static void main(String[] args) {
        String pattern = "abba";
//        String s = "dog cat cat dog";
//        String s = "dog cat cat fish";

        // incident case
        String s = "dog dog dog dog";

        WordPattern wordPattern = new WordPattern();
        System.out.println("Result: " + wordPattern.wordPattern(pattern, s));
        System.out.println("Result: " + wordPattern.wordPatternV1(pattern, s));
        System.out.println("Result: " + wordPattern.wordPatternV2(pattern, s));
    }

    /**
     * Using one hash map to get the mapping from a character to a string.
     * And use Sets for pattern and string s to check their length.
     *
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> mapper = new HashMap<>();
        Character[] characters = pattern.chars()
                .mapToObj(c -> (char) c)
                .toArray(Character[]::new);
        String[] words = s.split(" ");

        if (invalidLength(characters, words)) {
            return false;
        }

        int commonLength = characters.length;
        for (int i = 0; i < commonLength; ++i) {
            char c = characters[i];
            String word = words[i];

            if (mapper.containsKey(c)) {
                String tmp = mapper.get(c);
                if (!word.equals(tmp)) {
                    return false;
                }
            }

            mapper.put(c, word);
        }

        return true;
    }

    private boolean invalidLength(Character[] charsPattern, String[] words) {
        if (charsPattern.length != words.length) {
            return true;
        }

        Set<Character> charsSet = new HashSet<>();
        Collections.addAll(charsSet, charsPattern);

        Set<String> wordsSet = new HashSet<>(Arrays.asList(words));
        return charsSet.size() != wordsSet.size();
    }

    /**
     * Using two hash maps for bi-ejection
     *
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPatternV1(String pattern, String s) {
        Map<Character, String> charToWord = new HashMap<>();
        Map<String, Character> wordToChar = new HashMap<>();

        char[] chars = pattern.toCharArray();
        String[] words = s.split(" ");

        if (chars.length != words.length) {
            return false;
        }

        int i = 0;
        for (String word : words) {
            char c = chars[i];
            if (charToWord.containsKey(c) && !word.equals(charToWord.get(c))) {
                return false;
            } else if (wordToChar.containsKey(word) && c != wordToChar.get(word)) {
                return false;
            }

            charToWord.put(c, word);
            wordToChar.put(word, c);
            ++i;
        }

        return true;
    }

    /**
     * Using only one Hash Map without two sets data structure to check lengths of patterns and string s.
     *
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPatternV2(String pattern, String s) {
        Map<Character, String> charToWord = new HashMap<>();
        char[] chars = pattern.toCharArray();
        String[] words = s.split("\\s+");

        if (words.length != pattern.length()) {
            return false;
        }

        int i = 0;
        for (char c : chars) {
            if (charToWord.containsKey(c)) {
                if (!charToWord.get(c).equals(words[i++])) {
                    return false;
                }
            } else {
                if (charToWord.containsValue(words[i])) {
                    return false;
                }

                charToWord.put(c, words[i++]);
            }
        }

        return true;
    }

    /**
     * Using an alphabetical array that contains 26 characters in ASCII.
     * <p>
     * Saving spaces for using HashMap.
     *
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPatternV3(String pattern, String s) {
        String[] words = s.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }

        String[] alphabets = new String[26];
        for (int i = 0; i < words.length; i++) {
            alphabets[pattern.charAt(i) - 'a'] = words[i];
        }

        for (int i = 0; i < words.length; i++) {
            if (!alphabets[pattern.charAt(i) - 'a'].equals(words[i])) {
                return false;
            }
        }

        for (int i = 0; i < 26; i++) {
            for (int j = i + 1; j < 26; j++) {
                if (alphabets[i] != null && alphabets[j] != null && alphabets[i].equals(alphabets[j])) {
                    return false;
                }
            }
        }

        return true;
    }

}
