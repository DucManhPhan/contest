package com.manhpd;

import java.util.Arrays;

public class SortArrayByParity {

    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 4};
//        int[] nums = {0, 1};

        SortArrayByParity solution = new SortArrayByParity();
        int[] res = solution.sortArrayByParityII(nums);
        System.out.println(Arrays.toString(res));
    }

    /**
     * Using two-pointers technique
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParity(int[] A) {
        int len = A.length;
        int first = 0;
        int second = len - 1;

        while (first < second) {
            while (first < len && ((A[first] & 1) == 0)) {    // even
                ++first;
            }

            while (second >= 0 && ((A[second] & 1) != 0)) {   // odd
                --second;
            }

            if (first >= len || second < 0 || first > second) {
                break;
            }

            int tmp = A[first];
            A[first] = A[second];
            A[second] = tmp;

            ++first;
            --second;
        }

        return A;
    }

    /**
     * Refactoring the way that using the two-pointers technique
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParityII(int[] A) {
        int left = 0;
        int right = A.length -1;

        while(left < right)
        {
            if(A[left] % 2 == 1 && A[right] % 2 == 0)
            {
                int tmp = A[left];
                A[left] = A[right];
                A[right] = tmp;

                left++;
                right--;
            }

            if(A[left] % 2 == 0) left++;
            if(A[right] % 2 == 1) right--;
        }

        return A;
    }


    /**
     * Using recursion to solve it
     *
     * @param A
     * @return
     */
    public int[] sortArrayByParityI(int[] A) {
        int len = A.length;

        for (int i = 0; i < len; ++i) {
            this.sortArrayByParityI(A, i, i);
            this.sortArrayByParityI(A, i, i + 1);
        }

        return A;
    }

    public void sortArrayByParityI(int[] A, int i, int j) {
        while (!((i < 0 || i >= A.length) || (j < 0 || j >= A.length))) {
            if (((A[j] & 1) == 0) && ((A[i] & 1) != 0)) {
                int tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }

            --i;
            ++j;
        }
    }
}
