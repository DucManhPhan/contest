package manhpd.com;

import java.util.HashMap;
import java.util.Map;

/**
 * Refer: https://leetcode.com/problems/contiguous-array/description/
 */
public class ContiguousArray {
    public static void main(String[] args) {
        int[] nums = {0, 1};
        int expected = 2;

//        int[] nums = {0, 1, 0};
//        int expected = 2;

//        int[] nums = {0, 1, 1, 1, 1, 1, 0, 0, 0};
//        int expected = 6;

//        int[] nums = {1, 1, 1, 1, 1, 1, 1, 1};
//        int expected = 0;

//        int res = findMaxLength(nums);
//        int res = findMaxLength1(nums);
        int res = findMaxLength2(nums);
        System.out.println("Result: " + res);
    }

    /**
     * Using Prefix Sum + Hash Map
     *
     * @param nums
     * @return
     */
    private static int findMaxLength(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }

        int[] prefixSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        Map<Integer, Integer> mp = new HashMap<>();
        int maxLength = 0;

        for (int i = 0; i < prefixSum.length; ++i) {
            int currentSum = prefixSum[i];

            if (mp.containsKey(currentSum)) {
                maxLength = Math.max(maxLength, i - mp.get(currentSum));
            }

            mp.putIfAbsent(currentSum, i);
        }

        return maxLength;
    }

    private static int findMaxLength1(int[] nums) {
        int maxLength = 0;

        for (int start = 0; start < nums.length; ++start) {
            for (int end = start + 1; end <= nums.length; ++end) {
                int numOf0s = 0;
                int numOf1s = 0;

                for (int i = start; i < end; ++i) {
                    if (nums[i] == 0) {
                        ++numOf0s;
                    } else {
                        ++numOf1s;
                    }
                }

                if (numOf0s == numOf1s) {
                    maxLength = Math.max(maxLength, end - start);
                }
            }
        }

        return maxLength;
    }

    private static int findMaxLength2(int[] nums) {
        int maxLength = 0;

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }

        int[] prefixSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        for (int start = 0; start < nums.length; ++start) {
            for (int end = start + 1; end <= nums.length; ++end) {
                int sum = prefixSum[end] - prefixSum[start];

                if (sum == 0) {
                    maxLength = Math.max(maxLength, end - start);
                }
            }
        }

        return maxLength;
    }
}
