package com.manhpd;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * Return the max sliding window.
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 *
 * Follow up:
 * Could you solve it in linear time?
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 *
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 */
public class SlidingWindowMaximum {

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        int[] res = maxSlidingWindow(nums, k);
        IntStream.of(res).forEach(System.out::println);
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int windowStart = 0;
        int max = -1;
        List<Integer> maxElems = new ArrayList<>();

        for (int windowEnd = 0; windowEnd < nums.length; ++windowEnd) {
            int item = nums[windowEnd];
            int steps = windowEnd - windowStart + 1;

            if (steps <= k) {
                max = Math.max(max, item);
            }

            if (steps == k) {
                maxElems.add(max);
                max = -1;
                ++windowStart;
            }
        }

        return new int[]{};
    }

}
