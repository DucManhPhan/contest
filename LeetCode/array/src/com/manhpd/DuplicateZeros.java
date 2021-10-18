package com.manhpd;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Given a fixed-length integer array arr, duplicate each occurrence of zero, shifting the remaining elements to the right.
 *
 * Note that elements beyond the length of the original array are not written. Do the above modifications to the input array in place and do not return anything.
 *
 * Example 1:
 *
 * Input: arr = [1,0,2,3,0,4,5,0]
 * Output: [1,0,0,2,3,0,0,4]
 * Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
 * Example 2:
 *
 * Input: arr = [1,2,3]
 * Output: [1,2,3]
 * Explanation: After calling your function, the input array is modified to: [1,2,3]
 *
 * Constraints:
 *
 * 1 <= arr.length <= 104
 * 0 <= arr[i] <= 9
 *
 */
public class DuplicateZeros {

    public static void main(String[] args) {
//        int[] arr = {1, 0, 2, 3, 0, 4, 5, 0};
        int[] arr = {1, 2, 3};
        duplicateZeros(arr);
    }

    public static void duplicateZeros(int[] arr) {
        int length = arr.length;

        for (int i = 0; i < length;) {
            if (i < length - 1 && arr[i] == 0) {
                for (int j = length - 1; j > i + 1; --j) {
                    arr[j] = arr[j - 1];
                }

                arr[i + 1] = 0;
                i += 2;
            } else {
                ++i;
            }
        }

        Arrays.stream(arr).forEach(System.out::println);
    }
}
