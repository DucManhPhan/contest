package com.manhpd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeArray {

    public static void main(String[] args) {
        int[] tmpa = {1, 5, 7, 7};
        int[] tmpb = {0, 1, 2, 3};

        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();

        for (int i = 0; i < tmpa.length; ++i) {
            a.add(tmpa[i]);
        }

        for (int i = 0; i < tmpb.length; ++i) {
            b.add(tmpb[i]);
        }

        List<Integer> c = mergeArrays(a, b);
        System.out.println(c.toString());
    }

    /**
     * The complexity of this solution:
     * - Time complexity: O(m) with m is the max length of two arrays.
     * - Space complexity: O(m + n).
     */
    public static List<Integer> mergeArrays(List<Integer> a, List<Integer> b) {
        // Write your code here
        List<Integer> c = new ArrayList<>();
        int aIdx = 0;
        int bIdx = 0;

        // get the elements from both array with using their smallest length
        while (aIdx < a.size() && bIdx < b.size()) {
            int aTmp = a.get(aIdx);
            int bTmp = b.get(bIdx);
            if (aTmp < bTmp) {
                c.add(aTmp);
                ++aIdx;
            } else if (aTmp >= bTmp) {
                c.add(bTmp);
                ++bIdx;
            }
        }

        for(int i = aIdx; i < a.size(); ++i) {
            c.add(a.get(i));
        }

        for(int i = bIdx; i < b.size(); ++i) {
            c.add(b.get(i));
        }

        return c;
    }

}
