package com.manhpd;

/**
 * Write an efficient program to find the sum of contiguous subarray within a one-dimensional array of numbers which has the largest sum.
 *
 * Example: {-2, -3, 4, -1, -2, 1, 5, -3}
 *
 */
public class KadaneAlgorithm {

    public static void main(String[] args) {
        int[] nums = {-2, -3, 4, -1, -2, 1, 5, -3};
        int res = maxSubArraySum(nums);
        System.out.println(res);
    }

    public static int maxSubArraySum(int[] nums) {
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
