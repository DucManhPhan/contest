package com.manhpd;

public class ShuffleString {

    public static void main(String[] args) {
        String s = "codeleet";
        int[] indices = {4,5,6,7,0,2,1,3};

        ShuffleString solution = new ShuffleString();
        System.out.println(solution.restoreString(s, indices));
    }

    public String restoreString(String s, int[] indices) {
        int len = indices.length;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; ++i) {
            sb.append(s.charAt(indices[i]));
        }

        return sb.toString();
    }
}

