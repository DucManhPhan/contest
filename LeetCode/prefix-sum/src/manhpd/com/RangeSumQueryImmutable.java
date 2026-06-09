package com.manhpd;

import java.util.Arrays;

/**
 * 303. Range Sum Query - Immutable
 * https://leetcode.com/problems/range-sum-query-immutable/description/?envType=problem-list-v2&envId=prefix-sum
 *
 * Given an integer array nums, handle multiple queries of the following type:
 *
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * Implement the NumArray class:
 *
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 *
 *
 * Example 1:
 *
 * Input
 * ["NumArray", "sumRange", "sumRange", "sumRange"]
 * [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
 * Output
 * [null, 1, -1, -3]
 *
 * Explanation
 * NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
 * numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
 * numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
 * numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -105 <= nums[i] <= 105
 * 0 <= left <= right < nums.length
 * At most 104 calls will be made to sumRange.
 *
 */
public class RangeSumQueryImmutable {

    private int[] nums;
    private int[] prefixSum;

    public RangeSumQueryImmutable(int[] nums) {
        this.nums = nums;

        this.prefixSum = this.calculatePrefixSum();
        System.out.println(Arrays.toString(this.prefixSum));
    }

    /**
     * Using for loop way.
     *
     * @param left
     * @param right
     * @return
     */
    public int sumRange_ForLoop(int left, int right) {
        if (left < 0 || right >= nums.length || left > right) {
            return -1;
        }

        int result = 0;
        for (int i = left; i <= right; ++i) {
            result += nums[i];
        }

        System.out.println("Sum range from " + left + " to " + right + ": " + result);
        return result;
    }

    private int[] calculatePrefixSum() {
        int[] prefixSum = new int[this.nums.length + 1];
        prefixSum[0] = 0;

        for (int i = 0; i < nums.length; ++i) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        return prefixSum;
    }

    /**
     * Using prefix sum to reduce the time that calculate the sum multiple times.
     *
     * @param left
     * @param right
     * @return
     */
    public int sumRange_PrefixSum(int left, int right) {
        if (left < 0 || right >= nums.length || left > right) {
            return -1;
        }

        int result = this.prefixSum[right + 1] - this.prefixSum[left];

        System.out.println("Sum range from " + left + " to " + right + ": " + result);
        return 0;
    }

    public static void main(String[] args) {
        RangeSumQueryImmutable rangeSumQueryImmutable = new RangeSumQueryImmutable(new int[]{-2, 0, 3, -5, 2, -1});

//        rangeSumQueryImmutable.sumRange_ForLoop(0, 2);
//        rangeSumQueryImmutable.sumRange_ForLoop(2, 5);
//        rangeSumQueryImmutable.sumRange_ForLoop(0, 5);

        rangeSumQueryImmutable.sumRange_PrefixSum(0, 2);
        rangeSumQueryImmutable.sumRange_PrefixSum(2, 5);
        rangeSumQueryImmutable.sumRange_PrefixSum(0, 5);
    }
}