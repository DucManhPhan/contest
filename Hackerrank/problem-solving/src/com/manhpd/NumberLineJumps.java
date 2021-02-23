package com.manhpd;

import java.util.HashSet;
import java.util.Set;

/**
 * https://www.hackerrank.com/challenges/kangaroo/problem
 */
public class NumberLineJumps {

    public static void main(String[] args) {
        int x1 = 0, v1 = 3;
        int x2 = 4, v2 = 2;

//        int x1 = 21, v1 = 6;
//        int x2 = 47, v2 = 3;
        System.out.println(kangaroo(x1, v1, x2, v2));
    }

    public static String kangaroo(int x1, int v1, int x2, int v2) {
        return ((x2 - x1) % (v1 - v2) == 0) ? "YES" : "NO";
    }

}
