package com.manhpd;

import java.util.Arrays;

/**
 * Refer: https://leetcode.com/problems/two-sum/description/
 *
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * Example 1:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 *
 * Example 2:
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 *
 * Example 3:
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 *
 *
 * Constraints:
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * Only one valid answer exists.
 *
 * Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
 *
 */
public class TwoSum {
    public static void main(String[] args) {
        // Case 1
//        int[] nums = {2,7,11,15};
//        int target = 9;
//        int[] expected = {0, 1};

        // Case 2
        int[] nums = {3, 2, 4};
        int target = 6;
        int[] expected = {1, 2};

        // Case 3
//        int[] nums = {3, 3};
//        int target = 6;
//        int[] expected = {0, 1};

        int[] res = twoSum(nums, target);
        System.out.println("Result: " + Arrays.toString(res) +
                ", expected: " + Arrays.toString(expected));
    }

    /**
     * Brute-force with 2 iteration
     *
     * @param nums
     * @param target
     * @return
     */
    private static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                int currentSum = nums[i] + nums[j];
                if (currentSum == target) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{-1, -1};
    }

    /**
     * Brute-force with Binary Search for the remaining item
     *
     * @param nums
     * @param target
     * @return
     */
    private static int[] twoSum1(int[] nums, int target) {



        return new int[]{-1, -1};
    }

    private static int binarySearch(int[] nums, int target) {

    }
}
