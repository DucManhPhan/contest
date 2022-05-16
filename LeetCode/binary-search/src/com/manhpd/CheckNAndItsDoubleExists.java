package com.manhpd;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Ref: https://leetcode.com/problems/check-if-n-and-its-double-exist/
 *
 * Given an array arr of integers, check if there exists two integers N and M such that N is the double of M ( i.e. N = 2 * M).
 *
 * More formally check if there exists two indices i and j such that :
 * i != j
 * 0 <= i, j < arr.length
 * arr[i] == 2 * arr[j]
 *
 * Example 1:
 * Input: arr = [10,2,5,3]
 * Output: true
 * Explanation: N = 10 is the double of M = 5,that is, 10 = 2 * 5.
 *
 * Example 2:
 * Input: arr = [7,1,14,11]
 * Output: true
 * Explanation: N = 14 is the double of M = 7,that is, 14 = 2 * 7.
 *
 * Example 3:
 * Input: arr = [3,1,7,11]
 * Output: false
 * Explanation: In this case does not exist N and M, such that N = 2 * M.
 *
 *
 * Constraints:
 * 2 <= arr.length <= 500
 * -10^3 <= arr[i] <= 10^3
 *
 */
public class CheckNAndItsDoubleExists {

    public static void main(String[] args) {
//        int[] arr = {10, 2, 5, 3};
//        int[] arr = {7, 1, 14, 11};
//        int[] arr = {3, 1, 7, 11};
//        int[] arr = {3, 2, 1, 1};
//        int[] arr = {-2, 0, 10, -19, 4, 6, -8};
        int[] arr = {0, 0};

//        boolean res = checkIfExistV3(arr);
        boolean res = checkIfExistV2(arr);
        System.out.println("Result: " + res);
    }

    /**
     * Using Set to check an element exists or not.
     *
     * @param arr
     * @return
     */
    public static boolean checkIfExist(int[] arr) {
        Arrays.sort(arr);
        Set<Integer> data = new HashSet<>();

        for (int i = 0; i < arr.length; ++i) {
            int currentValue = arr[i];

            if (i > 0 && currentValue != 0 && currentValue == arr[i - 1]) {
                continue;
            }

            if (data.contains(2 * currentValue) || data.contains(currentValue)) {
                return true;
            }

            data.add(currentValue);
            data.add(2 * currentValue);
        }

        return false;
    }

    /**
     * Using Binary Search algorithm.
     *
     * @param arr
     * @return
     */
    public static boolean checkIfExistV2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return false;
        }

        Arrays.sort(arr);

        for (int i = 0; i < arr.length; ++i) {
            int currentValue = arr[i];
            int idx = Arrays.binarySearch(arr, 2 * currentValue);

            if (idx >= 0 && idx != i) {
                return true;
            }
        }

        return false;
    }

    /**
     * Using brute-force algorithm
     *
     * @param arr
     * @return
     */
    public static boolean checkIfExistV3(int[] arr) {
        int length = arr.length;

        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
                if (i != j) {
                    if (arr[i] == 2 * arr[j]) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
