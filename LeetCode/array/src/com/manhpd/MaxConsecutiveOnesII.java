package com.manhpd;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary array, find the maximum number of consecutive 1s in this array if you can flip at most one 0.
 *
 * The input array will only contain 0 and 1.
 * The length of input array is a positive integer and will not exceed 10,000
 *
 * Ex1: Input: [1,0,1,1,0]
 *      Output: 4
 *      Explanation: Flip the first zero will get the the maximum number of consecutive 1s.
 *                   After flipping, the maximum number of consecutive 1s is 4.
 */
public class MaxConsecutiveOnesII {

    public static void main(String[] args) {
        int[] nums = {1, 0, 1, 1, 0};
//        int[] nums = {1, 0, 1, 1, 0, 1, 1, 1};
//        int result = findMaxConsecutiveOnes(nums);
//        int result = findMaxConsecutiveOnesDP(nums);
        int result = findMaxConsecutiveOnesNormal1(nums);

        System.out.println("Result: " + result);
    }

    /**
     * Using Sliding window way
     * Time complexity: O(n)
     *
     * @param nums
     * @return
     */
    public static int findMaxConsecutiveOnes(int[] nums) {
        int maxLength = 0;
        int windowStart = 0;
        int maxFlipOperations = 1;
        int maxConsecutive1s = 0;

        for (int windowEnd = 0; windowEnd < nums.length; ++windowEnd) {
            if (nums[windowEnd] == 1) {
                ++maxConsecutive1s;
            }

            if (windowEnd - windowStart + 1 - maxConsecutive1s > maxFlipOperations) {
                if (nums[windowStart] == 1) {
                    --maxConsecutive1s;
                }

                ++windowStart;
            }

            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    /**
     * Using brute force way
     *
     * @param nums
     * @return
     */
    private static int getMaxUtil(int[] nums) {
        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                max = Math.max(max, count);
                count = 0;
            }
        }
        return Math.max(max, count);
    }

    public static int findMaxConsecutiveOnesNormal(int[] nums) {
        int len = nums.length;
        List<Integer> zeroIdx = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) zeroIdx.add(i);
        }

        if (zeroIdx.isEmpty()) {
            return getMaxUtil(nums);
        }

        int result = 0;
        for (Integer i : zeroIdx) {
            nums[i] = 1;
            result = Math.max(result, getMaxUtil(nums));
            nums[i] = 0;
        }

        return result;
    }

    /**
     * Use brute force way
     *
     * Time complexity: O(n^2)
     *
     * @param nums
     * @return
     */
    public static int findMaxConsecutiveOnesNormal1(int[] nums) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; ++i) {
            int length = 0;
            int k = 1;  // the number of 0s
            for (int j = i; j < nums.length; ++j) {
                if (nums[j] == 1) {
                    ++length;
                } else {
                    --k;
                    if (k == 0) {
                        ++length;
                    } else {
                        break;
                    }
                }
            }

            max = Math.max(max, length);
        }

        System.out.println(max);
        return max;
    }

    /**
     * Using Dynamic programming
     * Time complexity: O(n)
     *
     * Explanation:
     * s1[i+1] is a size of 1s from i+1 position with 1 flip
     * s0[i+1] is a size of 1s from i+1 without any flip
     *
     * DP equations are:
     * s1[i+1] =
     * s1[i] +1 : if nums[i+1] == 1
     * s0[i] +1 : if nums[i+1] == 0
     *
     * s0[i+1] =
     * s0[i] + 1 : if nums[i] == 1
     * 0 : if nums[i] == 0
     *
     * smax[i+1] = max(smax[i], s1[i+1])
     *
     */
    public static int findMaxConsecutiveOnesDP(int[] nums) {
        int s0 = 0;
        int s1 = 0;
        int smax = 0;

        for (Integer n : nums) {
            s1 = n > 0 ? 1 + s1 : 1 + s0;
            s0 = n > 0 ? 1 + s0 : 0;
            smax = Math.max(smax, s1);
        }

        return smax;
    }

}
