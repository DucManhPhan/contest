package com.manhpd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Ref: https://leetcode.com/problems/minimum-size-subarray-sum/
 * <p>
 * Given an array of positive integers nums and a positive integer target,
 * return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal to target.
 * If there is no such subarray, return 0 instead.
 * <p>
 * Example 1:
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * <p>
 * Example 2:
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 * <p>
 * Example 3:
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 * <p>
 * Constraints:
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */
public class MinimumSizeSubarraySum {

    public static void main(String[] args) {
//        int[] nums = {2, 3, 1, 2, 4, 3};
//        int target = 7;

//        int[] nums = {1,1,1,1,1,1,1,1};
//        int target = 11;

        int[] nums = {2,16,14,15};
        int target = 20;

        int res = minSubArrayLen(target, nums);
        System.out.println("Result: " + res);
    }

    /**
     * Using brute-force algorithm --> backtracking to get all possible solutions.
     * Then, checking each solution to find the subarray that has minimum size.
     *
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int target, int[] nums) {
        List<List<Integer>> subs = new ArrayList<>();
        getAllSubarray(nums, target, 0, new ArrayList<>(), subs);

        if (subs.size() == 0) {
            return 0;
        }

        int minSize = Integer.MAX_VALUE;
        for (List<Integer> sub : subs) {
            if (sub.size() < minSize) {
                minSize = sub.size();
            }
        }

        return minSize;
    }

    /**
     * Using the wrong way because the problem is to find the contiguous subarrays that has sum >= target.
     * In getAllSubarray() method, use backtracking to get all subsets --> wrong when comparing the given problem.
     *
     * @param nums
     * @param target
     * @param idx
     * @param ints
     * @param subs
     */
    private static void getAllSubarray(int[] nums, int target, int idx, List<Integer> ints, List<List<Integer>> subs) {
        if (target <= 0) {
            subs.add(new ArrayList<>(ints));
            return;
        }

        for (int i = idx; i < nums.length; ++i) {
            ints.add(nums[i]);
            getAllSubarray(nums, target - nums[i], i + 1, ints, subs);
            ints.remove(ints.size() - 1);
        }
    }

    /**
     * Using Binary Search algorithm
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLenV2(int target, int[] nums) {

        return -1;
    }

}
