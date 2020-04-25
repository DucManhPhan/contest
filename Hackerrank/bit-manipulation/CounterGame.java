package com.manhpd;

/**
 * Louise and Richard have developed a numbers game.
 * They pick a number and check to see if it is a power of 2.
 * If it is, they divide it by 2.
 * If not, they reduce it by the next lower number which is a power of 2.
 * Whoever reduces the number to 1 wins the game. Louise always starts.
 *
 * Given an initial value, determine who wins the game.
 *
 * Ex1: Input: n = 6
 *      Output: Richard
 *      Explanation: 6 is not power of 2 so Louise reduces it by the largest power of 2  less than 6: 6 - 4 = 2
 *      2 is a power of 2 so Richard divides by 2 to get 1 and wins the game.
 *
 * Ex2: Input: n = 132
 *      Output: Louise
 */
public class CounterGame {

    public static void main(String[] args) {
//        long n = 6;
        long n = 132;
        String winner = counterGame(n);
//        String winner = counterGameUsingSetBit(n);
        System.out.println(winner);
    }

    public static String counterGame(long n) {
        int counter = 1;    // Louise first

        while (true) {
            boolean isPower = isPowerOfTwo(n);
            if (isPower) {
                n = n / 2;
            } else {
                long msbSetBit = getMSBSetBit(n);
                long tmp = 1 << msbSetBit;
                n -= tmp;
            }

            ++counter;
            if (n == 1) {
                if ((counter & 2) == 0) {
                    return "Louise";
                } else {
                    return "Richard";
                }
            }
        }
    }

    public static boolean isPowerOfTwo(long n) {
        return n != 0 && ((n & (n - 1)) == 0);
    }

    public static long getMSBSetBit(long n) {
        return (long) (Math.log(n) / Math.log(2));
    }

    /**
     * Explanation for this solution:
     * First of all, for any number N to be power of two it must be of the format 100..00 (in binary)
     * i.e. single 1 followed by all 0s.
     * Now, consider the two operations allowed :
     *
     * 1) If N is not a power of 2, reduce the counter by the largest power of 2 less than N :
     * This is equivalent to removing the first 1 until the number N is power of 2.
     * Thus number of such operations would eqaute to count of all 1s before the last 1.
     *
     * Example : If N=11010, the largest power of 2 less than N would be 10000,
     * reducing N by it we get 1010, which is equivalent to removing first 1.
     *
     * 2) If N is a power of 2, reduce the counter by half of N :
     * This operation is equivalent to removing trailing zeros, or right shift.
     * Number of such operations would be equal to count of all 0s after the final 1.
     *
     * Combining the above two steps : we need to count "1s before last 1" and "0s after last 1".
     * To bring uniformity in action, we subtract 1 from N, which will flip all the trailing zeros (and also last 1).
     * Now, the only operation required, is to count 1s in the number i.e. to get popcount for (n-1).
     *
     * @param n
     * @return
     */
    public static String counterGameUsingSetBit(long n) {
        if ((setBits(n-1) & 1) != 0) {
            return "Louise" ;
        } else {
            return "Richard";
        }
    }

    public static int setBits(long n) {
        int count = 0 ;
        while(n != 0) {
            n &= (n-1);
            ++count;
        }

        return count ;
    }

}
