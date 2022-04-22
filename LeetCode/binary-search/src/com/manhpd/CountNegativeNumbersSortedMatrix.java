package com.manhpd;

import java.util.Arrays;
import java.util.Collections;

/**
 * Ref: https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/
 * Given a m * n matrix grid which is sorted in non-increasing order both row-wise and column-wise.
 * Return the number of negative numbers in grid.
 *
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * -100 <= grid[i][j] <= 100
 *
 * Ex1: Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
 * Output: 8
 * Explanation: There are 8 negatives number in the matrix.
 *
 * Ex2: Input: grid = [[3,2],[1,0]]
 * Output: 0
 *
 * Ex3: Input: grid = [[1,-1],[-1,-1]]
 * Output: 3
 *
 * Ex4: Input: grid = [[-1]]
 * Output: 1
 *
 */
public class CountNegativeNumbersSortedMatrix {

    public static void main(String[] args) {
//        int[][] grid = {{4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}};
//        int[][] grid = {{3, 2}, {1, 0}};
        int[][] grid = {{-1}};
//        int res = countNegatives(grid);
        int res = countNegativesV2(grid);

        System.out.println(res);
    }

    /**
     * Using brute force solution
     *
     * @param grid
     * @return
     */
    public static int countNegatives(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int countNegative = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] < 0) {
                    ++countNegative;
                }
            }
        }

        return countNegative;
    }

    /**
     * Using Binary Search algorithm
     *
     * @param grid
     * @return
     */
    public static int countNegativesV2(int[][] grid) {
        int countNegativeNums = 0;
        for (int i = 0; i < grid.length; ++i) {
            int[] rowsth = grid[i];
            int rowsLength = rowsth.length;

            int firstIdxNegativeNum = binarySearchNonIncreasing(rowsth, 0);
            if (firstIdxNegativeNum == rowsLength || firstIdxNegativeNum < 0) {
                continue;
            }

            countNegativeNums += rowsLength - firstIdxNegativeNum;
        }

        return countNegativeNums;
    }

    private static int binarySearchNonIncreasing(int[] arr, int key) {
        int left = 0;
        int right = arr.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == key) {
                return mid + 1;
            }

            if (arr[mid] < key) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if (left >= 0 && arr[left] < key) {
            return left;
        }

        if (right < arr.length && arr[right] < key) {
            return right;
        }

        return -1;
    }

}
