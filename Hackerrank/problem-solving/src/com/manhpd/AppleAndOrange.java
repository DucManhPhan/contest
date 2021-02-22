package com.manhpd;

/**
 * https://www.hackerrank.com/challenges/apple-and-orange/problem
 */
public class AppleAndOrange {

    public static void main(String[] args) {
//        int a = 4;  // position of apple tree
//        int b = 12; // position of orange tree
//        int s = 7;
//        int t = 10;     // positions of Sam's house
//
//        int[] apples = {2, 3, -4};
//        int[] oranges = {3, -2, -4};

        int a = 1;  // position of apple tree
        int b = 5; // position of orange tree
        int s = 2;
        int t = 3;     // positions of Sam's house

        int[] apples = {2};
        int[] oranges = {-2};

        countApplesAndOranges(s, t, a, b, apples, oranges);
    }

    public static void countApplesAndOranges(int s, int t, int a, int b, int[] apples, int[] oranges) {
        System.out.println(count(a, s, t, apples));
        System.out.println(count(b, s, t, oranges));
    }

    public static int count(int a, int s, int t, int[] apples) {
        int count = 0;
        for (int posApple : apples) {
            int curPos = posApple + a;
            if (curPos >= s && curPos <= t) {
                ++count;
            }
        }

        return count;
    }

}
