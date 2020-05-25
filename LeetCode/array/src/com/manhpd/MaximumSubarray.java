package com.manhpd;

/**
 * Given an integer array nums,
 * find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 *
 * Example:
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 */
public class MaximumSubarray {

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        int[] nums = {-1};
//        int res = maxSubArray(nums);
        int res = kadaneAlgorithm(nums);

        System.out.println(res);
    }

    /**
     * Using brute force algorithm
     *
     * Time complexity: O(n^3)
     * Space complexity: O(1)
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int len = nums.length;
        int max = Integer.MIN_VALUE;

        for (int start = 0; start < len; ++start) {
            for (int end = start + 1; end <= len; ++end) {
                int sum = 0;
                for (int i = start; i < end; ++i) {
                    sum += nums[i];
                }

                max = Math.max(max, sum);
            }
        }

        return max;
    }

    /**
     * Using Kadane algorithm
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param nums
     * @return
     */
    public static int kadaneAlgorithm(int[] nums) {
        int len = nums.length;
        int max = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0; i < len; ++i) {
            sum += nums[i];
            max = Math.max(max, sum);
            sum = Math.max(sum, 0);
        }

        return max;
    }

}
