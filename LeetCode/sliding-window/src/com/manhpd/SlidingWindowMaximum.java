package com.manhpd;

import java.util.*;
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

//        int[] res = maxSlidingWindow(nums, k);
//        int[] res = maxSlidingWindowIterative(nums, k);
        int[] res = maxSlidingWindowDeque(nums, k);
        IntStream.of(res).forEach(System.out::println);
    }

    public static int[] maxSlidingWindowIterative(int[] nums, int k) {
        int[] maxElems = new int[nums.length - k + 1];
        int count = 0;

        for (int i = 0; i < nums.length - k + 1; ++i) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i + k; ++j) {
                max = Math.max(max, nums[j]);
            }

            maxElems[count++] = max;
        }

        return maxElems;
    }

    /**
     * Using sliding window technique with priority queue
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int windowStart = 0;
        int max = Integer.MIN_VALUE;
        int[] maxElems = new int[nums.length - k + 1];
        int count = 0;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(nums.length, Collections.reverseOrder());

        for (int windowEnd = 0; windowEnd < nums.length; ++windowEnd) {
            int item = nums[windowEnd];
            int steps = windowEnd - windowStart + 1;
            priorityQueue.add(item);

            if (steps <= k) {
                max = priorityQueue.peek();     // Math.max(max, item);
            }

            if (steps == k) {
                maxElems[count++] = max;

                // remove the element at windowStart index
                priorityQueue.remove(nums[windowStart]);
                ++windowStart;
            }
        }

        return maxElems;
    }

    public static int[] maxSlidingWindowDeque(int[] nums, int k) {
        int len = nums.length;
        if (len == 0 || k == 0) {
            return new int[0];
        }

        int[] result = new int[len - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < len; ++i) {
            // remove indices that are out of bound - subarray with size = k
            while (deque.size() > 0 && deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            // remove indices whose corresponding values are less than nums[i]
            while (deque.size() > 0 && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // add nums[i]
            deque.offerLast(i);

            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

}
