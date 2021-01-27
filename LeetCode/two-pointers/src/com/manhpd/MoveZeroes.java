package com.manhpd;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/move-zeroes/
 */
public class MoveZeroes {

    public static void main(String[] args) {
//        int[] nums = {0, 1, 0, 3, 12};
        int[] nums = {1, 0};
//        int[] nums = {1, 0, 1};

        MoveZeroes solution = new MoveZeroes();
        solution.moveZeroes(nums);

        System.out.println(Arrays.toString(nums));
    }

    /**
     * Using extra space
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param nums
     */
    public void moveZeroesI(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];

        for (int i = 0, resIdx = 0; i < len; ++i) {
            if (nums[i] != 0) {
                res[resIdx++] = nums[i];
            }
        }

        // copy the res array to input array
        for (int i = 0; i < len; ++i) {
            nums[i] = res[i];
        }
    }

    /**
     * Using two pointers techniques
     *
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int nonPointer = 0;
        int zeroPointer = 0;

        while (true) {
            while (nonPointer < nums.length && nums[nonPointer] == 0) {
                ++nonPointer;
            }

            while (zeroPointer < nums.length && nums[zeroPointer] != 0) {
                ++zeroPointer;
            }

            if (nonPointer >= nums.length || zeroPointer >= nums.length) {
                return;
            }

            if (nonPointer > zeroPointer) {
                int tmp = nums[nonPointer];
                nums[nonPointer] = nums[zeroPointer];
                nums[zeroPointer] = tmp;
            }

            ++nonPointer;
        }
    }

}
