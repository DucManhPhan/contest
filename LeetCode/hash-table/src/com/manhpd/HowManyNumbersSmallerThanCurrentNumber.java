package com.manhpd;


import java.util.Arrays;

/**
 * Refer: https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
 */
public class HowManyNumbersSmallerThanCurrentNumber {

    public static void main(String[] args) {
//        int[] nums = {8, 1, 2, 2, 3};
//        int[] nums = {6, 5, 4, 8};
        int[] nums = {7, 7, 7, 7};

        int[] results = smallerNumbersThanCurrent(nums);
        Arrays.stream(results).forEach(value -> {
            System.out.println(value + ", ");
        });
    }

    /**
     * Time complexity: O(n)
     *
     * @param nums
     * @return
     */
    public static int[] smallerNumbersThanCurrent(int[] nums) {

        return null;
    }

    /**
     * Time complexity: O(n^2)
     *
     * @param nums
     * @return
     */
    public static int[] smallerNumbersThanCurrentNormal(int[] nums) {

        return null;
    }

}
