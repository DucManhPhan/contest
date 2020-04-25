package com.manhpd;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a square matrix, calculate the absolute difference between the sums of its diagonals.
 *
 * Constraints: -100 >= arr[i][j] <= 100
 *
 * For example, the square matrix  is shown below:
 * 1 2 3
 * 4 5 6
 * 9 8 9
 *
 * Output: Print the absolute difference between the sums of the matrix's two diagonals as a single integer.
 *
 */
public class DiagonalDifference {

    public static void main(String[] args) {
        List<List<Integer>> arr = new ArrayList<>();
        arr.add(Arrays.asList(1, 2, 3));
        arr.add(Arrays.asList(4, 5, 6));
        arr.add(Arrays.asList(9, 8, 9));

        int res = diagonalDifference(arr);
        System.out.println(res);
    }

    public static int diagonalDifference(List<List<Integer>> arr) {
        if (arr == null) {
            return 0;
        }

        int len = arr.size();
        int sumRightDiagonal = 0;
        int sumLeftDiagonal = 0;
        for (int i = 0, j = len - 1; i < len && j >= 0; ++i, --j) {
            sumRightDiagonal += arr.get(i).get(i);
            sumLeftDiagonal += arr.get(i).get(j);
        }

        return Math.abs(sumLeftDiagonal - sumRightDiagonal);
    }

}
