package com.manhpd;

public class LongestSubArray {

    public static void main(String[] args) {
//        int[] nums = {10,1,2,4,7,2};  // expected = 4
//        int limit = 5;

//        int[] nums = {8,2,4,7};   // expected = 2
//        int limit = 4;

//        int[] nums = {4,2,2,2,4,4,2,2};   // expected = 3
//        int limit = 0;

        int[] nums = {4,8,5,1,7,9}; // expected = 3
        int limit = 6;

        int res = longestSubarray(nums, limit);
        System.out.println(res);
    }

    /**
     * Wrong answer
     *
     * @param nums
     * @param limit
     * @return
     */
    public static int longestSubarray(int[] nums, int limit) {
        int windowStart = 0;
        int len = 0;

        for (int windowEnd = 0; windowEnd < nums.length; ++windowEnd) {
            if (Math.abs(nums[windowEnd] - nums[windowStart]) <= limit) {
                if (Math.abs(nums[windowStart] - nums[windowStart + 1]) > limit) {
                    windowStart = windowEnd;
                }

                int size = windowEnd - windowStart + 1;

                if (size > len) {
                    len = size;
                }
            } else {
                ++windowStart;
            }
        }

        return len;
    }

}
