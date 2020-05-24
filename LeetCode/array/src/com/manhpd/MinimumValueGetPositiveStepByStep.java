package com.manhpd;


/**
 * Given an array of integers nums, you start with an initial positive value startValue.
 *
 * In each iteration, you calculate the step by step sum of startValue plus elements in nums (from left to right).
 *
 * Return the minimum positive value of startValue such that the step by step sum is never less than 1.
 *
 * Example 1:
 *
 * Input: nums = [-3,2,-3,4,2]
 * Output: 5
 * Explanation: If you choose startValue = 4, in the third iteration your step by step sum is less than 1.
 *                 step by step sum
 *                 startValue = 4 | startValue = 5 | nums
 *                   (4 -3 ) = 1  | (5 -3 ) = 2    |  -3
 *                   (1 +2 ) = 3  | (2 +2 ) = 4    |   2
 *                   (3 -3 ) = 0  | (4 -3 ) = 1    |  -3
 *                   (0 +4 ) = 4  | (1 +4 ) = 5    |   4
 *                   (4 +2 ) = 6  | (5 +2 ) = 7    |   2
 * Example 2:
 *
 * Input: nums = [1,2]
 * Output: 1
 * Explanation: Minimum start value should be positive.
 * Example 3:
 *
 * Input: nums = [1,-2,-3]
 * Output: 5
 *
 */
public class MinimumValueGetPositiveStepByStep {

    public static void main(String[] args) {
        int[] nums = {-3,2,-3,4,2};
//        int res = minStartValue(nums);
        int res = minStartValueKadane(nums);

        System.out.println(res);
    }

    /**
     * Using brute force algorithm
     *
     * Time complexity:
     *
     * @param nums
     * @return
     */
    public static int minStartValue(int[] nums) {
        int minStartValue = 1;

        while (true) {
            int startValue = minStartValue;
            boolean isMinValue = true;
            for (int i = 0; i < nums.length; ++i) {
                startValue += nums[i];
                if (startValue < 1) {
                    isMinValue = false;
                    break;
                }
            }

            if (isMinValue) {
                return minStartValue;
            }

            ++minStartValue;
        }
    }

    /**
     * Using Kadane algorithm
     *
     * @param nums
     * @return
     */
    public static int minStartValueKadane(int[] nums) {
        int min_so_far = 0;
        int min_elem = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            min_so_far += nums[i];
            min_elem = Math.min(min_elem, min_so_far);
        }
        if (min_elem <= 0) {
            return 1 - min_elem;
        } else
            return 1;
    }

}
