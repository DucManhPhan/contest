package com.manhpd;

import java.util.*;

/**
 * Given an array of strings, group anagrams together.
 *
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 *
 * Ex1: Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 */
public class GroupAnagrams {

    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        List<List<String>> results = groupAnagrams(strs);
        results.stream().forEach(items -> {
            for (String item : items) {
                System.out.println(item);
            }

            System.out.println();
        });
    }

    /**
     * Time complexity: O(n^2 * k)
     *
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        boolean[] isProcessed = new boolean[strs.length];
        List<List<String>> results = new ArrayList<>();

        for (int i = 0; i < strs.length; ++i) {
            if (isProcessed[i]) {
                continue;
            }

            List<String> result = new ArrayList<>();
            result.add(strs[i]);
            for (int j = i + 1; j < strs.length; ++j) {
                boolean isAnagram = isAnagram(strs[i], strs[j]);
                if (isAnagram) {
                    result.add(strs[j]);
                    isProcessed[j] = true;
                }
            }

            if (!result.isEmpty()) {
                results.add(result);
            }
        }

        return results;
    }

    private static boolean isAnagram(String s, String p) {
        if (s.length() != p.length()) {
            return false;
        }

        int[] char_counts = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            char_counts[s.charAt(i) - 'a']++;
            char_counts[p.charAt(i) - 'a']--;
        }

        for (int count : char_counts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Using sort for string to get the common string as key in hash map
     *
     * Time complexity: O(n * k * log(k))
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagramsSorting(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }

    /**
     * Using HashMap, build the common key for the other anagrams.
     * Time complexity: O(n*k), with k = the maximum length of string
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagramsHashMap(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<String, List>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) count[c - 'a']++;

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList());
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }

}
