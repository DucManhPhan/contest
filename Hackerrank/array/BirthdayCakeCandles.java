package com.manhpd;

/**
 * You are in charge of the cake for your niece's birthday and
 * have decided the cake will have one candle for each year of her total age.
 * When she blows out the candles, she’ll only be able to blow out the tallest ones.
 * Your task is to find out how many candles she can successfully blow out.
 *
 * Constraints: 1 <= n <= 10^5
 *              1 <= arr[i] <= 10^7
 *
 * Ex1: Input: 3 2 1 3
 *      Output: 2
 */
public class BirthdayCakeCandles {

    public static void main(String[] args) {
//        int[] ar = {3, 2, 1, 3};
        int[] ar = {4, 4, 1, 3};
        int num = birthdayCakeCandles(ar);
        System.out.println(num);
    }

    private static int birthdayCakeCandles(int[] ar) {
        int len = ar.length;
        int countHighestCandles = 1;
        int highestCandle = 0;

        // get the highest candle
        for (int i = 0; i < len; ++i) {
            if (highestCandle < ar[i]) {
                highestCandle = ar[i];
            } else if (highestCandle == ar[i]) {
                ++countHighestCandles;
            }
        }

        return countHighestCandles;
    }

}
