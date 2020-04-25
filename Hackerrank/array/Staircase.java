package com.manhpd;

/**
 * Consider a staircase of size n = 4:
 *
 *    #
 *   ##
 *  ###
 * ####
 *
 * 0 < n <= 100
 *
 * Observe that its base and height are both equal to n, and the image is drawn using # symbols and spaces.
 * The last line is not preceded by any spaces.
 *
 * Write a program that prints a staircase of size n.
 * The last line must have 0 spaces in it.
 *
 */
public class Staircase {

    public static void main(String[] args) {
        int n = 4;
        staircase(n);
    }

    private static void staircase(int n) {
        for (int i = n - 1; i >= 0; --i) {
            StringBuilder space = new StringBuilder();
            StringBuilder symbol = new StringBuilder();

            int j = 0;
            for (; j < i; ++j) {
                space.append(" ");
            }

            for (int k = n - j; k > 0; --k) {
                symbol.append("#");
            }

            System.out.println(space.append(symbol).toString());
        }
    }

}
