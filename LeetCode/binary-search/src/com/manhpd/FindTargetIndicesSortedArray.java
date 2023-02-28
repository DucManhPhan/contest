package com.manhpd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Ref: https://leetcode.com/problems/find-target-indices-after-sorting-array/
 *
 * You are given a 0-indexed integer array nums and a target element target.
 * A target index is an index i such that nums[i] == target.
 *
 * Return a list of the target indices of nums after sorting nums in non-decreasing order.
 * If there are no target indices, return an empty list. The returned list must be sorted in increasing order.
 *
 * Example 1:
 * Input: nums = [1,2,5,2,3], target = 2
 * Output: [1,2]
 * Explanation: After sorting, nums is [1,2,2,3,5].
 * The indices where nums[i] == 2 are 1 and 2.
 *
 * Example 2:
 * Input: nums = [1,2,5,2,3], target = 3
 * Output: [3]
 * Explanation: After sorting, nums is [1,2,2,3,5].
 * The index where nums[i] == 3 is 3.
 *
 * Example 3:
 * Input: nums = [1,2,5,2,3], target = 5
 * Output: [4]
 * Explanation: After sorting, nums is [1,2,2,3,5].
 * The index where nums[i] == 5 is 4.
 *
 *
 * Constraints:
 * 1 <= nums.length <= 100
 * 1 <= nums[i], target <= 100
 *
 */
public class FindTargetIndicesSortedArray {

    public static void main(String[] args) {
//        int[] nums = {1,2,5,2,3};
//        int target = 2;

//        int[] nums = {1,2,5,2,3};
//        int target = 3;

        int[] nums = {1,2,5,2,3};
        int target = 5;

//        List<Integer> indices = targetIndices(nums, target);
//        List<Integer> indices = targetIndicesV1(nums, target);
        List<Integer> indices = targetIndicesV2(nums, target);
        System.out.println(indices.toString());
    }

    /**
     * Using brute-force algorithm
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);

        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                indices.add(i);
            }
        }

        return indices;
    }

    /**
     * Using Binary Search algorithm
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<Integer> targetIndicesV1(int[] nums, int target) {
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));

        List<Integer> indices = new ArrayList<>();

        int lowerPoint = lowerBound(nums, target);
        if (lowerPoint == -1) {
            return indices;
        }

        int upperPoint = upperBound(nums, target);
        IntStream.rangeClosed(lowerPoint, upperPoint)
                 .forEach(idx -> indices.add(idx));

        return indices;
    }

    private static int lowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if (nums[left] == target) {
            return left;
        }

        if (right < nums.length && nums[right] == target) {
            return right;
        }

        return -1;
    }

    private static int upperBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (nums[left] == target) {
            return left;
        }

        if (right < nums.length && nums[right] == target) {
            return right;
        }

        return -1;
    }

    /**
     * Not using sorting algorithm.
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<Integer> targetIndicesV2(int[] nums, int target) {
        List<Integer> indices = new ArrayList<>();

        int count = 0;
        int lessThan = 0;

        for (int n : nums) {
            if (n == target) {
                count++;
                continue;
            }

            if (n < target) {
                lessThan++;
            }
        }

        for (int i = 0; i < count; ++i) {
            indices.add(lessThan++);
        }

        return indices;
    }

}
