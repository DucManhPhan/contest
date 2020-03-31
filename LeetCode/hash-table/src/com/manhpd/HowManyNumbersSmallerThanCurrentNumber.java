package com.manhpd;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Refer: https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
 */
public class HowManyNumbersSmallerThanCurrentNumber {

    public static void main(String[] args) {
        int[] nums = {8, 1, 2, 2, 3};
//        int[] nums = {6, 5, 4, 8};
//        int[] nums = {7, 7, 7, 7};

        int[] results = smallerNumbersThanCurrent(nums);
//        int[] results = smallerNumbersThanCurrentNormal(nums);
        Arrays.stream(results).forEach(value -> {
            System.out.println(value + ", ");
        });
    }

    /**
     * Time complexity: O(n)
     * Using counting sort and prefix sum
     *
     * @param nums
     * @return
     */
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[] count = new int[101];   // since nums[i] >= 0 and <= 100
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }
        int[] prefixSum = new int[102];   // the prefixSum of the first number is 0, because no number is previous of it
        for (int i = 1; i < prefixSum.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + count[i - 1];
        }
        int[] ans = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            ans[i] = prefixSum[nums[i]];
        }
        return ans;
    }

    /**
     * Time complexity: O(n^2)
     *
     * @param nums
     * @return
     */
    public static int[] smallerNumbersThanCurrentNormal(int[] nums) {
        int size = nums.length;
        int[] results = new int[size];

        for (int i = 0; i < size; ++i) {
            int count = 0;

            for (int j = 0; j < size; ++j) {
                if (i != j && nums[j] < nums[i]) {
                    ++count;
                }
            }

            results[i] = count;
        }

        return results;
    }

    /**
     * Using HashMap
     * Time complexity: O(nlogn)
     */
    public static int[] smallerNumbersThanCurrentHashmap(int[] nums) {
        int[] temp = Arrays.copyOf(nums,nums.length);
        Arrays.sort(temp);

        Map<Integer,Integer> map = new HashMap<>();
        int count = 0;
        for(int num : temp) {
            if(!map.containsKey(num)) {
                map.put(num, count);
            }
            count++;
        }

        int[] output = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            output[i] = map.get(nums[i]);
        }

        return output;
    }

    /**
     * Using binary search
     *
     */
    public int[] smallerNumbersThanCurrentBinarySearch(int[] nums) {
        int[] numsCopy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(numsCopy);
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = binarySearch(numsCopy, nums[i]);
        }
        return res;
    }

    private int binarySearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    /**
     * Using Priority Queue and HashMap
     */
    public int[] smallerNumbersThanCurrentPriorityQueue(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b-a);
        for(int num : nums) pq.offer(num);

        Map<Integer, Integer> map = new HashMap<>();
        while(! pq.isEmpty()){
            int num = pq.poll();
            while(!pq.isEmpty() && pq.peek() == num) pq.poll();
            map.put(num, pq.size());
        }
        int[] result = new int[nums.length];
        for(int index = 0; index < nums.length; index++) result[index] = map.get(nums[index]);
        return result;
    }

}
