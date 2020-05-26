package com.manhpd;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * Given a String S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
 *
 * If possible, output any possible result. If not possible, return the empty string.
 *
 * Input: S = "aab"
 * Output: "aba"
 *
 */
public class RearrangeString {

    public static void main(String[] args) {
//        String s = "aab";
//        String s = "aaadbcf";
//        String s = "abbbccf";
//        String s = "aaab";
        String s = "aaabbccdd";
        String res = rearrange(s);
        if (res == "") {
            System.out.println("Empty string");
        }

        System.out.println(res);
    }

    public static String rearrange(String s) {
        if (s == null || s == "") {
            return "";
        }

        int length = s.length();
        char[] result = new char[length];
        result[0] = s.charAt(0);
        List<Character> mem = new LinkedList<>();

        int indexS = 1;
        int indexResult = 1;
        char c = 'a';
        while (indexS < length || !mem.isEmpty()) {
            if (indexS < length) {
                c = s.charAt(indexS);
            }

            if (mem.size() > 0) {
                char tmp = mem.get(mem.size() - 1);
                if (tmp != result[indexResult - 1]) {
                    result[indexResult++] = tmp;
                    mem.remove(mem.size() - 1);
                } else if (indexS > length && tmp == result[indexResult - 1]) { // duplicate elements
                    return "";
                }
            }

            if (indexS < length && c != result[indexResult - 1]) {
                result[indexResult++] = c;
            } else if (indexS < length) {
                mem.add(c);
            }

            ++indexS;
        }

        return new String(result);
    }

}
