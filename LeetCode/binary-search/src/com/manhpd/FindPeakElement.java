package com.manhpd;

import java.util.ArrayList;
import java.util.List;

/**
 * A peak element is an element that is greater than its neighbors.
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 *
 * You may imagine that nums[-1] = nums[n] = -∞.
 *
 * Ex1: Input: nums = [1,2,3,1]
 *      Output: 2
 *      Explanation: 3 is a peak element and your function should return the index number 2.
 *
 * Ex2: Input: nums = [1,2,1,3,5,6,4]
 *      Output: 1 or 5
 *      Explanation: Your function can return either index number 1 where the peak element is 2,
 *              or index number 5 where the peak element is 6.
 */
public class FindPeakElement {

    public static void main(String[] args) {
//        int[] nums = {1, 2, 1, 3, 5, 6, 4};
        int[] nums = {1, 2, 3, 1};
//        int[] nums = {2, 1};
//        int[] nums = {1, 2};
//        int res = findPeakElement(nums);
        int res = findPeakElementBruteForce(nums);
//        int res = findPeakElementBsIterative(nums);
        System.out.println(res);
    }

    public static int findPeakElementBruteForce(int[] nums) {
        List<Integer> peaks = new ArrayList<>();
        peaks.add(nums[nums.length - 1]);

        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] > nums[i + 1]) {
                peaks.add(nums[i]);
            }
        }

        return peaks.get(peaks.size() - 1);
    }

    public static int findPeakElementBsIterative(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] > nums[mid + 1])
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }

    public static int findPeakElementBsRecursive(int[] nums) {
        return -1;
    }

    /**
     * Verbose way that using Binary Search
     * Time complexity: O(log(n))
     * Space complexity: O(1)
     *
     * @param nums
     * @return
     */
    public static int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (left == mid && right == mid) {      // increased or decreased array
                return mid;
            }

            if (nums[mid] <= nums[mid + 1]) {
                if (mid < 1) {
                    return mid + 1;
                } else {
                    if (nums[mid] >= nums[mid - 1]) {
                        left = mid + 1;
                    } else if (nums[mid] <= nums[mid - 1]) {
                        right = mid - 1;
                    }
                }
            } else if (nums[mid] > nums[mid + 1]) {
                if (mid >= 1) {
                    if (nums[mid] > nums[mid - 1]) {
                        return mid;
                    } else {        // decreased array
                        right = mid - 1;
                    }
                } else {
                    return mid;
                }
            }
        }

        return -1;
    }

}
