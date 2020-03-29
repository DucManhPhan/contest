package com.manhpd;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Refer: https://leetcode.com/problems/two-sum/
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

//        int[] results = twoSumNormal(nums, target);
        int[] results = twoSum(nums, target);
        System.out.println("Values: ");
        Arrays.stream(results).forEach(value -> {
            System.out.print(value + ", ");
        });
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int size = nums.length;
        for (int i = 0; i < size; ++i) {
            map.put(nums[i], i);
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            }
        }

        return new int[]{};
    }

    public static int[] twoSumNormal(int[] nums, int target) {
        int[] results = new int[2];
        int size = nums.length;
        for (int i = 0; i < size - 1; ++i) {
            for (int j = i + 1; j < size; ++j) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }

        return results;
    }

}
