package com.manhpd;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * Find the minimum element.
 *
 * You may assume no duplicate exists in the array.
 *
 * Example 1:
 *      Input: [3,4,5,1,2]
 *      Output: 1
 *
 * Example 2:
 *      Input: [4,5,6,7,0,1,2]
 *      Output: 0
 *
 */
public class FindMinOfRotatedArray {

    public static void main(String[] args) {
//        int[] nums = {3,4,5,1,2};
        int[] nums = {2, 1};
//        int[] nums = {1, 2};

//        int res = findMin(nums);
        int res = findMinV2(nums);
        System.out.println(res);
    }

    /**
     * Using template 2 of binary search
     *
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {
        if (nums == null) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }

    /**
     * Using template 3
     *
     * @param nums
     * @return
     */
    public static int findMinV2(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low + 1 < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[high]) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return nums[low] > nums[high] ? nums[high] : nums[low];
    }

}
