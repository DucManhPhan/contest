package com.manhpd;

/**
 * Ref: https://leetcode.com/problems/maximum-count-of-positive-integer-and-negative-integer/description/
 *
 * Given an array nums sorted in non-decreasing order, return the maximum between the number of positive integers and the number of negative integers.
 * In other words, if the number of positive integers in nums is pos and the number of negative integers is neg, then return the maximum of pos and neg.
 * Note that 0 is neither positive nor negative.
 *
 * Example 1:
 * Input: nums = [-2,-1,-1,1,2,3]
 * Output: 3
 * Explanation: There are 3 positive integers and 3 negative integers. The maximum count among them is 3.
 *
 * Example 2:
 * Input: nums = [-3,-2,-1,0,0,1,2]
 * Output: 3
 * Explanation: There are 2 positive integers and 3 negative integers. The maximum count among them is 3.
 *
 * Example 3:
 * Input: nums = [5,20,66,1314]
 * Output: 4
 * Explanation: There are 4 positive integers and 0 negative integers. The maximum count among them is 4.
 *
 *
 * Constraints:
 * 1 <= nums.length <= 2000
 * -2000 <= nums[i] <= 2000
 * nums is sorted in a non-decreasing order.
 *
 */
public class MaxCountPosNegInteger {

    public static void main(String[] args) {
//        int[] nums = {-2,-1,-1,1,2,3};  // res = 3
//        int[] nums = {-3,-2,-1,0,0,1,2};  // res = 3
        int[] nums = {5,20,66,1314};  // res = 4
//        int[] nums = {0, 0, 0, 0, 0, 0};  // res = 0
//        int[] nums = {-1};  // res = 1

//        int res = maximumCount(nums);
        int res = maximumCountV1(nums);
        System.out.println(res);
    }

    /**
     * Using Brute-force algorithm
     *
     * @param nums
     * @return
     */
    public static int maximumCount(int[] nums) {
        int lastIdxNeg = -1;
        int firstIdxPos = nums.length;

        // find the last index of the negative element
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] < 0) {
                lastIdxNeg++;
            }
        }

        System.out.println("last idex of negative element: " + lastIdxNeg);

        // find the first index of the positive element
        for (int i = nums.length - 1; i >= 0; --i) {
            if (nums[i] > 0) {
                firstIdxPos--;
            }
        }

        System.out.println("first idex of positive element: " + firstIdxPos);
        return Math.max(lastIdxNeg + 1, nums.length - firstIdxPos);
    }

    /**
     * Using Binary Search algorithm
     *
     * @param nums
     * @return
     */
    public static int maximumCountV1(int[] nums) {
        int lowerPoint = lowerBound(nums, 0);
        int upperPoint = upperBound(nums, 0);

        return Math.max(upperPoint + 1, nums.length - lowerPoint);
    }

    private static int lowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if (nums[left] > target) {
            return left;
        }

        if (right < nums.length && nums[right] > target) {
            return right;
        }

        return -1;
    }

    private static int upperBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (right < nums.length && nums[right] < target) {
            return right;
        }

        if (nums[left] < target) {
            return left;
        }

        return -1;
    }


}
