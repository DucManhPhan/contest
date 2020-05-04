package com.manhpd;

import java.util.stream.Stream;

/**
 * Write a function that reverses a string. The input string is given as an array of characters char[].
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * You may assume all the characters consist of printable ascii characters.
 *
 * Example 1:
 * Input: ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 *
 * Example 2:
 * Input: ["H","a","n","n',"a","h"]
 * Output: ["h","a","n","n","a","H"]
 *
 */
public class ReverseString {

    public static void main(String[] args) {
//        char[] s = {'h', 'e', 'l', 'l', 'o'};
        char[] s = {'H', 'a', 'n', 'n', 'a', 'h'};
//        reverseStringByIteration(s);
        reverseStringByRecursion(s);

        Stream.of(s).forEach(System.out::println);
    }

    public static void reverseStringByIteration(char[] s) {
        for (int begin = 0, end = s.length - 1; begin < end;) {
            char tmp = s[begin];
            s[begin] = s[end];
            s[end] = tmp;

            ++begin;
            --end;
        }
    }

    public static void reverseStringByRecursion(char[] s) {
        recursive(s, 0, s.length - 1);
    }

    public static void recursive(char[] s, int left, int right) {
        if (left > right) {
            return;
        }

        char tmp = s[right];
        s[right] = s[left];
        s[left] = tmp;

        recursive(s, left + 1, right - 1);
    }


}
