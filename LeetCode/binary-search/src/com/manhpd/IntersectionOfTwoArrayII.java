package com.manhpd;

import java.util.*;

/**
 * Ref: https://leetcode.com/problems/intersection-of-two-arrays-ii/
 *
 * Given two integer arrays nums1 and nums2, return an array of their intersection.
 * Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.
 *
 * Example 1:
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 *
 * Example 2:
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Explanation: [9,4] is also accepted.
 *
 *
 * Constraints:
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 *
 *
 * Follow up:
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 *
 */
public class IntersectionOfTwoArrayII {

    public static void main(String[] args) {
//        int[] nums1 = {1, 2, 2, 1};
//        int[] nums2 = {2, 2};

        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};

        int[] res = intersection(nums1, nums2);
        System.out.println("Result: " + Arrays.toString(res));
    }

    /**
     * This way will still remain the number of elements in each array. Not remove the same element.
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        Map<Integer, Integer> countNumbers = new HashMap<>();
        for (int i = 0; i < nums1.length; ++i) {
            countNumbers.put(nums1[i], countNumbers.getOrDefault(nums1[i], 0) + 1);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums2.length; ++i) {
            if (countNumbers.containsKey(nums2[i])) {
                int num = countNumbers.get(nums2[i]);

                if (num == 0) {
                    continue;
                }

                res.add(nums2[i]);
                countNumbers.put(nums2[i], num - 1);
            }
        }

        return res.stream().mapToInt(t -> t).toArray();
    }

    /**
     * Using binary search
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersectionV2(int[] nums1, int[] nums2) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;

        while(i < nums1.length && j < nums2.length) {
            if(nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            }
            else if(nums1[i] < nums2[j]) {
                i++;
            }
            else {
                j++;
            }
        }

        int k = 0;
        int ans[] = new int[list.size()];
        for(int l:list) {
            ans[k] = l;
            k++;
        }

        return ans;
    }

}
