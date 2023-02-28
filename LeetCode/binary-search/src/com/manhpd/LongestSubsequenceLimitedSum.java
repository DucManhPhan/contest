package com.manhpd;

import java.util.*;

/**
 * Ref: https://leetcode.com/problems/longest-subsequence-with-limited-sum/
 *
 * You are given an integer array nums of length n, and an integer array queries of length m.
 *
 * Return an array answer of length m where answer[i] is the maximum size of a subsequence that
 * you can take from nums such that the sum of its elements is less than or equal to queries[i].
 *
 * A subsequence is an array that can be derived from another array by deleting some or
 * no elements without changing the order of the remaining elements.
 *
 * Example 1:
 * Input: nums = [4,5,2,1], queries = [3,10,21]
 * Output: [2,3,4]
 * Explanation: We answer the queries as follows:
 * - The subsequence [2,1] has a sum less than or equal to 3. It can be proven that 2 is the maximum size of such a subsequence, so answer[0] = 2.
 * - The subsequence [4,5,1] has a sum less than or equal to 10. It can be proven that 3 is the maximum size of such a subsequence, so answer[1] = 3.
 * - The subsequence [4,5,2,1] has a sum less than or equal to 21. It can be proven that 4 is the maximum size of such a subsequence, so answer[2] = 4.
 *
 * Example 2:
 * Input: nums = [2,3,4,5], queries = [1]
 * Output: [0]
 * Explanation: The empty subsequence is the only subsequence that has a sum less than or equal to 1, so answer[0] = 0.
 *
 *
 * Constraints:
 * n == nums.length
 * m == queries.length
 * 1 <= n, m <= 1000
 * 1 <= nums[i], queries[i] <= 10^6
 *
 */
public class LongestSubsequenceLimitedSum {

    public static void main(String[] args) {
        int[] nums = {4, 5, 2, 1};
        int[] queries = {3, 10, 21};

//        int[] nums = {9, 8, 10, 13, 7};
//        int[] queries = {18};

//        int[] nums = {2, 3, 4, 5};
//        int[] queries = {1};

//        int[] res = answerQueries(nums, queries);
//        int[] res = answerQueriesV1(nums, queries);
//        int[] res = answerQueriesV2(nums, queries);
//        int[] res = answerQueriesV3(nums, queries);
        int[] res = answerQueriesV4(nums, queries);
        System.out.println(Arrays.toString(res));
    }

    /**
     * Using brute-force algorithm
     *
     * @param nums
     * @param queries
     * @return
     */
    public static int[] answerQueries(int[] nums, int[] queries) {
        List<Integer> subsequences = new ArrayList<>();
        int[] res = new int[queries.length];

        maxLengthOfSubsequences(nums, queries, 0, res, subsequences);
        return res;
    }

    private static void maxLengthOfSubsequences(int[] nums, int[] queries, int idx, int[] res, List<Integer> subsequences) {
        if (idx >= nums.length) {
            saveMaxLength(queries, res, subsequences);
            return;
        }

        if (subsequences.size() != 0) {
            saveMaxLength(queries, res, subsequences);
        }

        for (int i = idx; i < nums.length; ++i) {
            subsequences.add(nums[i]);
            maxLengthOfSubsequences(nums, queries, i + 1, res, subsequences);
            subsequences.remove(subsequences.size() - 1);
        }
    }

    private static void saveMaxLength(int[] queries, int[] res, List<Integer> subsequences) {
        int currentSum = subsequences.stream()
                              .reduce(0, Integer::sum);

        int i = 0;
        for (int val : queries) {
            if (currentSum <= val) {
                res[i] = Math.max(res[i], subsequences.size());
            }

            ++i;
        }
    }

    /**
     * Using Priority Queue
     *
     * @param nums
     * @param queries
     * @return
     */
    public static int[] answerQueriesV1(int[] nums, int[] queries) {
        int[] res = new int[queries.length];
        int k = 0;

        for (int max : queries) {
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
            int sum = 0;

            for (int n : nums) {
                if (sum + n > max) {
                    if (!pq.isEmpty() && pq.peek() > n) {
                        sum -= pq.remove();
                        sum += n;
                        pq.add(n);
                    }
                } else {
                    sum += n;
                    pq.add(n);
                }
            }

            res[k++] = pq.size();
        }

        return res;
    }

    /**
     * Using Binary Search algorithm
     *
     * @param nums
     * @param queries
     * @return
     */
    public static int[] answerQueriesV2(int[] nums, int[] queries) {
        // sort nums array
        Arrays.sort(nums);

        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];

        // calculate prefix sum array
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }

        int[] res = new int[queries.length];
        int i = 0;
        for (int sum : queries) {
            int pos = upperBound(prefixSum, sum);
            if (pos == -1) {
                res[i++] = 0;
            } else {
                res[i++] = pos + 1;
            }
        }

        return res;
    }

    /**
     * Find an element's index that is less or equal than target
     */
    private static int upperBound(int[] prefixSum, int target) {
        int left = 0;
        int right = prefixSum.length;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (prefixSum[mid] == target) {
                return mid;
            }

            if (prefixSum[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (prefixSum[left] <= target) {
            return left;
        }

        return -1;
    }

    /**
     * Optimize the way of using Binary Search algorithm
     *
     * @param nums
     * @param queries
     * @return
     */
    public static int[] answerQueriesV3(int[] nums, int[] queries) {
        Arrays.sort(nums);

        // calculate the prefix sum in-place
        for (int i = 1; i < nums.length; ++i) {
            nums[i] += nums[i - 1];
        }

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int j = 0;

            if (queries[i] >= nums[nums.length - 1]) {
                res[i] = nums.length;
            } else {
                while (nums[j] <= queries[i]) {
                    ++j;
                }

                res[i] = j;
            }
        }

        return res;
    }

    /**
     * Using TreeMap
     *
     * @param nums
     * @param queries
     * @return
     */
    public static int[] answerQueriesV4(int[] nums, int[] queries) {
        Arrays.sort(nums);

        int sum = 0;
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(0, 0);

        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            treeMap.put(sum, i + 1);
        }

        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            int key = treeMap.floorKey(queries[i]);
            res[i] = treeMap.get(key);
        }

        return res;
    }

}
