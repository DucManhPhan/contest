package com.manhpd;

import java.util.*;

/**
 * Ref: https://leetcode.com/problems/intersection-of-two-arrays/
 *
 * Given two integer arrays nums1 and nums2, return an array of their intersection.
 * Each element in the result must be unique and you may return the result in any order.
 *
 * Example 1:
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 *
 * Example 2:
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 * Explanation: [4,9] is also accepted.
 *
 * Constraints:
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 *
 */
public class IntersectionOfTwoArray {

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};

//        int[] res = intersectionV2(nums1, nums2);
        int[] res = intersection(nums1, nums2);
        System.out.println("Result: " + Arrays.toString(res));
    }

    /**
     * This way uses two Sets to check an element existed or not.
     *
     * Time complexity: O(m + n) with m, n is corresponding the size of each array
     * Space complexity: O(m + n)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        Set<Integer> subSets = new HashSet<>();
        for (int i = 0; i < nums1.length; ++i) {
            subSets.add(nums1[i]);
        }

        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < nums2.length; ++i) {
            if (subSets.contains(nums2[i])) {
                res.add(nums2[i]);
            }
        }

        return res.stream().mapToInt(t -> t).toArray();
    }

    /**
     * This way will sort a longest array. And based on the smaller array, use binary search for that array.
     * m is the length of the longest array, and the other is n.
     *
     * Time complexity: O((m + n) * log(m))
     * Space complexity: O(n)
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersectionV2(int[] nums1, int[] nums2) {
        return new int[0];
    }

}
