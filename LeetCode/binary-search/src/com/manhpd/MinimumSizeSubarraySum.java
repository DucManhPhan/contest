package com.manhpd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Ref: https://leetcode.com/problems/minimum-size-subarray-sum/
 * <p>
 * Given an array of positive integers nums and a positive integer target,
 * return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr] of which the sum is greater than or equal to target.
 * If there is no such subarray, return 0 instead.
 * <p>
 * Example 1:
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * <p>
 * Example 2:
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 * <p>
 * Example 3:
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 * <p>
 * Constraints:
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */
public class MinimumSizeSubarraySum {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int target = 7;

//        int[] nums = {1, 1, 1, 1, 7};
//        int target = 7;

//        int[] nums = {1,1,1,1,1,1,1,1};
//        int target = 11;

//        int[] nums = {2,16,14,15};
//        int target = 20;

//        int res = minSubArrayLen(target, nums);
//        int res = minSubArrayLenV3(target, nums);
//        int res = minSubArrayLenV2(target, nums);
        int res = minSubArrayLenV5(target, nums);
        System.out.println("Result: " + res);
    }

    /**
     * Using brute-force algorithm --> backtracking to get all possible solutions.
     * Then, checking each solution to find the subarray that has minimum size.
     *
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen(int target, int[] nums) {
        List<List<Integer>> subs = new ArrayList<>();
        getAllSubarray(nums, target, 0, new ArrayList<>(), subs);

        if (subs.size() == 0) {
            return 0;
        }

        int minSize = Integer.MAX_VALUE;
        for (List<Integer> sub : subs) {
            if (sub.size() < minSize) {
                minSize = sub.size();
            }
        }

        return minSize;
    }

    /**
     * Using the wrong way because the problem is to find the contiguous subarrays that has sum >= target.
     * In getAllSubarray() method, use backtracking to get all subsets --> wrong when comparing the given problem.
     *
     * @param nums
     * @param target
     * @param idx
     * @param ints
     * @param subs
     */
    private static void getAllSubarray(int[] nums, int target, int idx, List<Integer> ints, List<List<Integer>> subs) {
        if (target <= 0) {
            subs.add(new ArrayList<>(ints));
            return;
        }

        for (int i = idx; i < nums.length; ++i) {
            ints.add(nums[i]);
            getAllSubarray(nums, target - nums[i], i + 1, ints, subs);
            ints.remove(ints.size() - 1);
        }
    }

    /**
     * Using Binary Search algorithm with Sliding window
     *
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLenV2(int target, int[] nums) {
        int low = 0;
        int high = nums.length + 1;
        boolean enter = false;

        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (greaterThanTarget(nums, target, mid)) {
                enter = true;
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        if (!enter) {
            return 0;
        }

        return high;
    }

    private static boolean greaterThanTarget(int[] nums, int target, int window) {
        int sums = 0;

        for (int i = 0; i < window; ++i) {
            sums += nums[i];
        }

        for (int i = 0; i < nums.length - window; ++i) {
            if (sums >= target) {
                return true;
            }

            sums -= nums[i];
            sums += nums[i + window];
        }

        if (sums >= target) {
            return true;
        }

        return false;
    }

    /**
     * Find the first element that is greater than or equal to target
     *
     * @param prefixSum
     * @param target
     * @return
     */
    private static int lowerBound(int[] prefixSum, int target) {
        int low = -1;
        int high = prefixSum.length;

        while (low + 1 != high) {
            int mid = low + (high - low) / 2;

            if (prefixSum[mid] < target) {
                low = mid;
            } else {
                high = mid;
            }
        }

        if (high < prefixSum.length) {
            return high;
        }

        return -1;
    }

    private static int[] prefixSum(int[] nums) {
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];

        for (int i = 1; i < nums.length; ++i) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        return prefixSum;
    }

    /**
     * Using brute-force to iterate all contiguous arrays.
     *
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLenV3(int target, int[] nums) {
        int minSize = Integer.MAX_VALUE;
        int outerIdx = -1;
        int innerIdx = -1;

        for (outerIdx = 0; outerIdx < nums.length; ++outerIdx) {
            int sum = 0;
            for (innerIdx = outerIdx; innerIdx < nums.length; ++innerIdx) {
                sum += nums[innerIdx];

                if (sum >= target) {
                    minSize = Math.min(minSize, innerIdx - outerIdx + 1);
                    break;
                }
            }
        }

        if (minSize == Integer.MAX_VALUE) {
            return 0;
        }

        return minSize;
    }

    /**
     * Using prefix-sum to reduce our working when calculating sum of each sub-array in the brute-force solution
     *
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLenV4(int target, int[] nums) {
        int size = nums.length;
        if (size == 0) {
            return 0;
        }

        int minSubarraySize = Integer.MAX_VALUE;
        int[] prefixSum = new int[size];
        prefixSum[0] = nums[0];

        for (int i = 1; i < size; ++i) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        for (int i = 0; i < size; ++i) {
            for (int j = i; j < size; ++j) {
                int sum = prefixSum[j] - prefixSum[i] + nums[i];
                if (sum >= target) {
                    minSubarraySize = Math.min(minSubarraySize, j - i + 1);
                    break;
                }
            }
        }

        return (minSubarraySize != Integer.MAX_VALUE) ? minSubarraySize : 0;
    }

    /**
     * Using Binary Search algorithm for prefix-sum array
     *
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLenV5(int target, int[] nums) {
        int size = nums.length;
        if (size == 0) {
            return 0;
        }

        int[] sums = new int[size + 1];
        Arrays.fill(sums, 0);

        // sums[0]=0 : Meaning that it is the sum of first 0 elements
        // sums[1]=A[0] : Sum of first 1 elements
        // ans so on...
        for (int i = 1; i <= size; ++i) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        System.out.println("Sums: " + Arrays.toString(sums));
        int minSubArraySize = Integer.MAX_VALUE;

        for (int i = 1; i <= size; ++i) {
            int value = target + sums[i - 1];
            int idx = lowerBound(sums, value);
            if (idx != -1) {
                minSubArraySize = Math.min(minSubArraySize, idx - i + 1);
            }
        }

        return (minSubArraySize != Integer.MAX_VALUE) ? minSubArraySize : 0;
    }

    /**
     * Using two-pointers
     *
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLenV6(int target, int[] nums) {
        int i = 0;
        int minSize = Integer.MAX_VALUE;
        int sum = 0;

        for (int j = 0; j < nums.length; ++i) {
            sum += nums[i];

            while (sum >= target) {
                minSize = Math.min(minSize, j - i + 1);
                sum -= nums[i];
                ++i;
            }
        }

        return minSize;
    }

    /**
     * Using Binary Search with prefix sum
     *
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLenV7(int target, int[] nums) {
        int[] prefixSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        int minSize = 0;
        for (int i = 0; i < prefixSum.length; ++i) {
            int right = Arrays.binarySearch(prefixSum, i, prefixSum.length, prefixSum[i] + target);
            if (right < 0) {
                right = -(right + 1);
            }

            if (right == prefixSum.length) {
                continue;
            }

            minSize = (minSize == 0) ? right - i : Math.min(minSize, right - i);
        }

        return minSize;
    }


}
