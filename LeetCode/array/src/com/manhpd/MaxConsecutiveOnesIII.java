package com.manhpd;

/**
 * Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
 * Return the length of the longest (contiguous) subarray that contains only 1s.
 *
 * 1 <= A.length <= 20000
 * 0 <= K <= A.length
 * A[i] is 0 or 1
 *
 * Example 1:
 *      Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 *      Output: 6
 *      Explanation:
 *          [1,1,1,0,0,1,1,1,1,1,1]
 *          Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
 *
 * Example 2:
 *      Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 *      Output: 10
 *      Explanation:
*           [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 *          Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.
 */
public class MaxConsecutiveOnesIII {

    public static void main(String[] args) {
        int[] A = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int K = 2;

//        int[] A = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
//        int K = 3;

        int result = longestOnes(A, K);
        System.out.println("Result: " + result);
    }

    public static int longestOnes(int[] A, int K) {
        int length = A.length;
        int windowStart = 0;
        int maxSubLength = 0;
        int currentSubLength1s = 0; // the number of 1s

        for (int windowEnd = 0; windowEnd < length; ++windowEnd) {
            int currentValue = A[windowEnd];
            if (currentValue == 1) {
                ++currentSubLength1s;
            }

            if (windowEnd - windowStart + 1 - currentSubLength1s > K) {
                if (A[windowStart] == 1) {
                    --currentSubLength1s;
                }

                ++windowStart;
            }

            maxSubLength = Math.max(maxSubLength, windowEnd - windowStart + 1);
        }

        return maxSubLength;
    }

}
