package com.manhpd;

import java.util.Stack;

/**
 * https://www.hackerrank.com/challenges/reduced-string/problem
 */
public class SuperReducedString {

    public static void main(String[] args) {
//        String s = "aaabccddd";
//        String s = "abba";
        String s = "zztqooauhujtmxnsbzpykwlvpfyqijvdhuhiroodmuxiobyvwwxupqwydkpeebxmfvxhgicuzdealkgxlfmjiucasokrdznmtlwh";
        String result = "tqauhujtmxnsbzpykwlvpfyqijvdhuhirdmuxiobyvxupqwydkpbxmfvxhgicuzdealkgxlfmjiucasokrdznmtlwh";
        System.out.println(superReducedString1(s));

        System.out.println(superReducedString1(s).equals(result));
    }

    public static String superReducedString(String s) {
        StringBuilder sb = new StringBuilder(s);

        for (int i = 1; i < sb.length(); i++) {
            if (sb.charAt(i) == sb.charAt(i - 1)) {
                sb.delete(i - 1, i + 1);
                i = 0;
            }
        }

        if (sb.length() == 0) {
            return "Empty String";
        }

        return sb.toString();
    }

    /**
     * Using stack data structure
     *
     * @param s
     * @return
     */
    public static String superReducedString1(String s) {
        Stack<Character> stk = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (!stk.isEmpty()) {
                char topChar = stk.peek();
                if (topChar == c) {
                    stk.pop();
                } else {
                    stk.push(c);
                }
            } else {
                stk.push(c);
            }
        }

        if (stk.size() == 0) {
            return "Empty String";
        }

        stk.forEach(c -> {
            sb.append(c);
        });

        return sb.toString();
    }

}
