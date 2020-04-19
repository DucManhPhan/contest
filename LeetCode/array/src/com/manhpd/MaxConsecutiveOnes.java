package com.manhpd;

/**
 * Given a binary array, find the maximum number of consecutive 1s in this array.
 * The input array will only contain 0 and 1.
 * The length of input array is a positive integer and will not exceed 10,000
 * <p>
 * Ex1: Input: [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive 1s.
 * The maximum number of consecutive 1s is 3.
 */
public class MaxConsecutiveOnes {

    public static void main(String[] args) {
//        int[] nums = {1, 1, 0, 1, 1, 1};
//        int[] nums = {1, 1, 0, 1};
//        int[] nums = {0, 1, 1, 0, 1, 1, 1, 0, 1};
        int[] nums = {0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1};
//        int[] nums = {0, 0, 0};
//        int result = findMaxConsecutiveOnesSlidingWindow(nums);
//        int result = findMaxConsecutiveOnesNormal(nums);
        int result = findMaxConsecutiveOnesSlidingWindowI(nums);

        System.out.println("Result: " + result);
    }

    /**
     * Using Sliding window
     *
     * Time complexity: O(n)
     *
     * @param nums
     * @return
     */
    private static int findMaxConsecutiveOnesSlidingWindow(int[] nums) {
        int windowStart = 0;        // point to the first current element has value = 1
        int length = nums.length;
        int maxCount = 0;

        for (int windowEnd = 0; windowEnd < length; ++windowEnd) {
            while (windowEnd < length && nums[windowEnd] != 1) {
                ++windowEnd;
                windowStart = windowEnd;
            }

            if (windowEnd == length) {
                break;
            }

            maxCount = Math.max(maxCount, windowEnd - windowStart + 1);
        }

        return maxCount;
    }

    /**
     * Using Sliding window with standard perspective.
     *
     * Time complexity: O(n)
     *
     * @param nums
     * @return
     */
    private static int findMaxConsecutiveOnesSlidingWindowI(int[] nums) {
        int length = nums.length;
        int maxLength = 0;
        int windowStart = 0;    // always point to the index of current element with value 1

        for (int windowEnd = 0; windowEnd < length; ++windowEnd) {
            if (nums[windowEnd] != 1) {
                windowStart = windowEnd;
                ++windowStart;
            }

            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }

    /**
     * Using normay way to solve it
     *
     * @param nums
     * @return
     */
    private static int findMaxConsecutiveOnesNormal(int[] nums) {
        int length = nums.length;
        int maxLength = 0;
        int currentLength = 0;

        for (int i = 0; i < length; ++i) {
            if (nums[i] == 1) {
                ++currentLength;
            } else {
                currentLength = 0;
            }

            maxLength = Math.max(maxLength, currentLength);
        }

        return maxLength;
    }

}
