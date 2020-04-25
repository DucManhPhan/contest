package com.manhpd;

/**
 * Given five positive integers, find the minimum and maximum values that can be calculated by summing exactly four of the five integers.
 * Then print the respective minimum and maximum values as a single line of two space-separated long integers.
 *
 * 1 <= arr[i] <= 10^9
 *
 * Ex1: Input: 1 2 3 4 5
 *      Output: 10 14
 *
 * Ex2: Input: 1 3 5 7 9
 *      Output: 16 24
 *
 */
public class MinMaxSum {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9};
        miniMaxSum(arr);
    }

    /**
     * Due to get 4 elements per 5 elements, so we need to calculate sum of 5 elements.
     * Then, get the maximum or minimum element.
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param arr
     */
    private static void miniMaxSum(int[] arr) {
        long max, min, sum;
        sum = max = min = arr[0];

        for(int i = 1; i < 5; i++){
            long temp = arr[i];
            sum += temp;
            if(max > temp){
                if(min > temp) {
                    min = temp;
                }
            } else {
                max = temp;
            }
        }

        System.out.print((sum -max) + " " + (sum - min));
    }

}
