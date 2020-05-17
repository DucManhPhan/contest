package com.manhpd;

/**
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 *
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 *
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 */
public class SubarraySumEqualsK {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int k = 3;

//        int[] nums = {1};
//        int k = 0;

        int res = subarraySum(nums, k);
        System.out.println(res);
    }

    public static int subarraySum(int[] nums, int k) {
        int windowStart = 0;
        int len = nums.length;
        int sum = 0;
        int count = 0;

        for (int windowEnd = 0; windowEnd < len; ++windowEnd) {
            sum += nums[windowEnd];

            if (sum == k) {
                ++count;
            }

            while (sum > k) {
                sum -= nums[windowStart];
                ++windowStart;

                if (sum == k) {
                    ++count;
                    break;
                }
            }
        }

        return count;
    }

}
