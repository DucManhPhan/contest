package com.manhpd;

/**
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
        int[][] grid = {{4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}};
        int res = countNegatives(grid);

        System.out.println(res);
    }

    public static int countNegatives(int[][] grid) {


        return 0;
    }

}
