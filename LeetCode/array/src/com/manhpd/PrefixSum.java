package com.manhpd;

import java.util.stream.IntStream;

public class PrefixSum {

    public static void main(String[] args) {
        int[] nums = {3, 4, 2, 6, 8, 9};
        int[] prefixSum = new int[nums.length];

        calculatePrefixSum(nums, prefixSum);
        IntStream.of(prefixSum).forEach(item -> System.out.print(item + " --> "));
        System.out.println(" null ");

        int res = sumSubarrayWithPrefixSum(prefixSum, 0, 2);
        System.out.println(res);
    }

    public static int bruteForceSumSubarray(int[] nums, int start, int end) {
        if (nums == null) {
            return 0;
        }

        int sum = 0;
        for (int i = start; i <= end; ++i) {
            sum += nums[i];
        }

        return sum;
    }

    public static int sumSubarrayWithPrefixSum(int[] prefixSum, int start, int end) {
        int previousSum = start == 0 ? 0 : prefixSum[start - 1];
        return prefixSum[end] - previousSum;
    }

    public static void calculatePrefixSum(int[] nums, int[] prefixSum) {
        if (nums == null || prefixSum == null) {
            return;
        }

        int length = nums.length;
        if (length == 0) {
            return;
        }

        prefixSum[0] = nums[0];
        for (int i = 1; i < length; ++i) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
    }

}
