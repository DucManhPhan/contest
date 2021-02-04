package com.manhpd;

public class ReverseStringII {

    public static void main(String[] args) {
//        String s = "abcdefg";
//        int k = 2;

//        String s = "a";
//        int k = 2;

//        String s = "abcdefg";
//        int k = 4;

        String s = "hyzqyljrnigxvdtneasepfahmtyhlohwxmkqcdfehybknvdmfrfvtbsovjbdhevlfxpdaovjgunjqlimjkfnqcqnajmebeddqsgl";
        int k = 39;

        ReverseStringII solution = new ReverseStringII();
        System.out.println(solution.reverseStr(s, k));
    }

    public String reverseStr(String s, int k) {
        char[] str = s.toCharArray();

        for (int i = 0; i < str.length;) {
            if (i < (2 * k + i)|| (i >= 2*k && i < 3 * k)) {
                this.reverse(str, i, i + k - 1);
                i = i + 2 * k;
            }

            if (str.length - i < k) {
                break;
            }
        }

        return String.valueOf(str);
    }

    private void reverse(char[] str, int start, int end) {
        if (end - start + 1 >= str.length) {
            end = str.length - 1;
        }

        while (start < end) {
            char tmp = str[start];
            str[start] = str[end];
            str[end] = tmp;

            ++start;
            --end;
        }
    }

}
