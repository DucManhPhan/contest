package com.manhpd;

public class MaxConsecutiveOnesII {

    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 1, 0};
        int res = findMaxConsecutiveOnes(nums);

        System.out.println(res);
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int len = nums.length;
        int windowStart = 0;
        int max = Integer.MIN_VALUE;
        int k = 1;
        int previousZeroPos = 0;

        for (int windowEnd = 0; windowEnd < len; ++windowEnd) {
            if (nums[windowEnd] == 0) {
                --k;
                if (k == 0) {
                    previousZeroPos = windowEnd;
                } else if (k < 0) {
                    windowStart = previousZeroPos + 1;
                    previousZeroPos = windowEnd;
                    ++k;
                }
            }

            max = Math.max(max, windowEnd - windowStart + 1);
        }

        return max;
    }

}
