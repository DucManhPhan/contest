package com.manhpd;

import java.util.ArrayList;
import java.util.List;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * Note: Given n will be a positive integer.
 *
 * Example 1: Input: 2
 *            Output: 2
 *
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 * Example 2: Input: 3
 *            Output: 3
 *
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 *
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        int n = 2;
//        int n = 3;
//        int numOfSteps = climbStairsByBacktracking(n);
        int numOfSteps = climbStairsByBacktracking(n);
        System.out.println(numOfSteps);
    }

    /**
     * Using backtracking to get all possibilities of this case
     * Time complexity: O(n^2)
     *
     * @param n
     * @return
     */
    public static int climbStairsByBacktracking(int n) {
        List<Integer> stepClimbs = new ArrayList<>();
        int[] steps = new int[]{1, 2};
        int[] numOfWays = new int[1];

        findNumOfWaysClimbStairs(steps, n, stepClimbs, numOfWays, 0);
        return numOfWays[0];
    }

    public static void findNumOfWaysClimbStairs(int[] steps, int nSteps, List<Integer> stepClimbs, int[] numOfWays, int currentIndex) {// List<Integer> numOfWays, int currentIndex) {
        if (!stepClimbs.isEmpty()) {
            int sum = stepClimbs.stream().mapToInt(Integer::intValue).sum();
            if (sum == nSteps) {
                ++numOfWays[0];
                return;
            } else if (sum > nSteps) {
                return;
            }
        }

        for (int i = 0; i < steps.length; ++i) {
            stepClimbs.add(steps[i]);
            findNumOfWaysClimbStairs(steps, nSteps, stepClimbs, numOfWays, i);
            stepClimbs.remove(stepClimbs.size() - 1);
        }
    }

    /**
     * Using Dynamic Programming to solve it
     * Time complexity: O(n)
     * Space complexity: O(n)
     *
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if (n < 2) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; ++i) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

}
