package com.manhpd;

import java.util.HashMap;
import java.util.Map;

/**
 * Refer: https://leetcode.com/problems/single-number/
 */
public class SingleNumber {

    public static void main(String[] args) {
//        int[] nums = {2, 2, 1};
        int[] nums = {4, 1, 2, 1, 2};

        int result = singleNumber(nums);
        System.out.println("Result: " + result);
    }

    public static int singleNumber(int[] nums) {
        Map<Integer, Integer> numMap = new HashMap<>();
        int size = nums.length;

        // mark the number of frequency
        for (int i = 0; i < size; ++i) {
            numMap.put(nums[i], numMap.getOrDefault(nums[i], 0) + 1);
        }

        return numMap.keySet().stream().filter(key -> numMap.get(key) == 1).findFirst().get();
    }

}
