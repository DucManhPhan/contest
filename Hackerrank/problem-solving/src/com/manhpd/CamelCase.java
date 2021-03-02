package com.manhpd;

public class CamelCase {

    public static void main(String[] args) {
//        String s = "saveChangesInTheEditor";
        String s = "oneTwoThree";

        System.out.println(camelCase(s));
    }

    public static int camelCase(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int count = 1;
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                count += 1;
            }
        }

        return count;
    }

}
