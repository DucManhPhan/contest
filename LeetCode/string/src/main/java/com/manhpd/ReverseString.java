package com.manhpd;

/**
 * Write a function that reverses a string. The input string is given as an array of characters char[].
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * You may assume all the characters consist of printable ascii characters.
 *
 * Ex1: Input: ['h','e','l','l','o']
 *      Output: ['o','l','l','e','h']
 *
 * Ex2: Input: ['H','a','n','n','a','h']
 *      Output: ['h','a','n','n','a','H']
 *
 */
public class ReverseString {

    public static void main(String[] args) {
//        char[] s = {'h','e','l','l','o'};
        char[] s = {'H','a','n','n','a','h'};

//        reverseStringIteration(s);
        reverseStringRecursion(s);
    }

    public static void reverseStringIteration(char[] s) {
        for (int begin = 0, end = s.length - 1; begin < end;) {
            char tmp = s[begin];
            s[begin] = s[end];
            s[end] = tmp;

            ++begin;
            --end;
        }

        System.out.println(s);
    }

    public static void reverseStringRecursion(char[] s) {
        exchangeCharacter(s, 0, s.length - 1);
        System.out.println(s);
    }

    private static void exchangeCharacter(char[] s, int begin, int end) {
        if (begin >= end) {
            return;
        }

        // do swap two value
        char tmp = s[begin];
        s[begin] = s[end];
        s[end] = tmp;

        exchangeCharacter(s, ++begin, --end);
    }

}
