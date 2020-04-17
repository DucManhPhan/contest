package com.manhpd;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Refer: https://leetcode.com/problems/jewels-and-stones/
 */
public class JewelsAndStones {

    public static void main(String[] args) {
        String jewels = "aA";
        String stones = "aAAbbbb";
//        String jewels = "z";
//        String stones = "ZZ";
        int result = numJewelsInStonesHashmap(jewels, stones);
        System.out.println("Result of hashmap: " + result);

        result = numJewelsInStonesHashset(jewels, stones);
        System.out.println("Result of hash set: " + result);

        result = numJewelsInStonesArray(jewels, stones);
        System.out.println("Result of array: " + result);
    }

    public static int numJewelsInStonesHashmap(String J, String S) {
        long startMs = System.currentTimeMillis();
        Map<Character, Integer> count = new HashMap<>();

        // process Jewels
        for (int i = 0; i < J.length(); ++i) {
            Character tmp = Character.valueOf(J.charAt(i));
            count.put(tmp, count.getOrDefault(tmp, 0));
        }

        //process Stones
        for (int i = 0; i < S.length(); ++i) {
            Character tmp = Character.valueOf(S.charAt(i));

            if (count.containsKey(tmp)) {
                int value = count.get(tmp);
                count.put(tmp, value + 1);
            }
        }

        int result = count.values().stream().mapToInt(x -> x).sum();
        long duration = System.currentTimeMillis() - startMs;
        System.out.println("Duration using hash map: " + duration + " ms");

        return result;
    }

    public static  int numJewelsInStonesHashset(String J, String S) {
        long startMs = System.currentTimeMillis();
        Set<Character> jewelsSet = new HashSet<>();
        for (int i = 0; i < J.length(); ++i)
            jewelsSet.add(J.charAt(i));
        int res = 0;
        for (int i = 0; i < S.length(); ++i) {
            char ch = S.charAt(i);
            if (jewelsSet.contains(ch))
                ++res;
        }

        long duration = System.currentTimeMillis() - startMs;
        System.out.println("Duration using hash set: " + duration + " ms");
        return res;
    }

    public static  int numJewelsInStonesArray(String J, String S) {
        long startMs = System.currentTimeMillis();
        int[] map = new int[128];
        for(char j : J.toCharArray()) map[j] = 1;
        int count = 0;
        for(char s : S.toCharArray()) if(map[s] == 1) count++;

        long duration = System.currentTimeMillis() - startMs;
        System.out.println("Duration using array: " + duration + " ms");
        return count;
    }

}
