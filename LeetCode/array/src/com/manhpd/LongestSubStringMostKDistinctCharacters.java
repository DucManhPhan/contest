package com.manhpd;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given a string, find the length of the longest substring T that contains at most k distinct characters.
 *
 * Ex1: Input: s = "eceba", k = 2
 *      Output: 3
 *      Explanation: T is "ece" which its length is 3.
 *
 * Ex2: Input: s = "aa", k = 1
 *      Output: 2
 *      Explanation: T is "aa" which its length is 2.
 *
 */
public class LongestSubStringMostKDistinctCharacters {

    public static void main(String[] args) {
        String s = "eceba";
        int k = 2;

//        String s = "aa";
//        int k = 1;

//        int result = lengthOfLongestSubstringKDistinct(s, k);
        int result = lengthOfLongestSubstringKDistinctTreeMap(s, k);
        System.out.println("Result: " + result);
    }

    /**
     * Using Sliding Window
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param s
     * @param k
     * @return
     */
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        int maxLength = 0;
        int windowStart = 0;
        Map<Character, Integer> indexCharMap = new HashMap<>();

        for (int windowEnd = 0; windowEnd < s.length(); ++windowEnd) {
            char rightCharacter = s.charAt(windowEnd);
            indexCharMap.put(rightCharacter, indexCharMap.getOrDefault(rightCharacter, 0) + 1);

            while (indexCharMap.size() > k) {
                char leftCharacter = s.charAt(windowStart);
                indexCharMap.put(leftCharacter, indexCharMap.get(leftCharacter) - 1);

                if (indexCharMap.get(leftCharacter) == 0) {
                    indexCharMap.remove(leftCharacter);
                }

                ++windowStart;
            }

            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    /**
     * Using TreeMap to save the last occurrence of element in array
     *
     * @param s
     * @param k
     * @return
     */
    public static int lengthOfLongestSubstringKDistinctTreeMap(String s, int k) {
        if(s.length() == 0 || k == 0) return 0;
        int ret = 0;
        int start = 0;
        TreeMap<Integer, Character> tm = new TreeMap<>();
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(map.size() == k && !map.containsKey(c)) {
                char evict = tm.pollFirstEntry().getValue();
                start = map.get(evict) + 1;
                map.remove(evict);
            }

            if(map.containsKey(c)) tm.remove(map.get(c));
            map.put(c, i);
            tm.put(i, c);
            ret = Math.max(ret, i - start + 1);
        }

        return ret;
    }

    /**
     * Using LinkedHashMap way
     *
     * @param s
     * @param k
     * @return
     */
    public int lengthOfLongestSubstringKDistinctLinkedHashMap(String s, int k) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>(16, 0.75f, true);
        char[] array = s.toCharArray();
        int max = 0;
        int cur = 0;
        for(int i = 0; i < array.length; i++){
            map.put(array[i], i);
            cur ++;
            if(map.size() > k){
                char keyToDelete = map.keySet().iterator().next();
                int index = map.get(keyToDelete);
                cur = i - index;
                map.remove(keyToDelete);
            }
            max = Math.max(max, cur);

        }
        return max;
    }

}
