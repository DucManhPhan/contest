package com.manhpd;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://www.geeksforgeeks.org/sort-elements-frequency-set-4-efficient-approach-using-hash/?ref=lbp
 *
 * Print the elements of an array in the decreasing frequency if 2 numbers have the same frequency then print the one which came first.
 *
 * Examples:
 * Input : arr[] = {2, 5, 2, 8, 5, 6, 8, 8}
 * Output : arr[] = {8, 8, 8, 2, 2, 5, 5, 6}
 *
 * Input : arr[] = {2, 5, 2, 6, -1, 9999999, 5, 8, 8, 8}
 * Output : arr[] = {8, 8, 8, 2, 2, 5, 5, 6, -1, 9999999}
 *
 */
public class SortElementsByFrequency {

    private static int logicalTimer = 0;

    public static void main(String[] args) {
        int[] nums = {2, 5, 2, 8, 5, 6, 8, 8};
//        int[] nums = {2, 5, 2, 6, -1, 9999999, 5, 8, 8, 8};

//        int[] res = sortByFrequency(nums);
//        int[] res = sortByFrequencyV2(nums);
        int[] res = sortByFrequencyV3(nums);
        System.out.println(Arrays.toString(res));
    }

    /**
     * Using hashmap to calculate the occurrences of each number.
     * But didn't show the output array like the above array.
     *
     * @param nums
     * @return
     */
    private static int[] sortByFrequency(int[] nums) {
        Comparator<FrequencyNum> byFrequencyThenLogicalTimer = (f1, f2) -> {
            if (f1.count != f2.count) {
                return Integer.compare(f2.count, f1.count);
            } else {
                return Integer.compare(f1.logicalTimer, f2.logicalTimer);
            }
        };

        Map<Integer, FrequencyNum> frequencyNums = new HashMap<>();
        for (int num : nums) {
            if (!frequencyNums.containsKey(num)) {
                FrequencyNum frequencyNum = new FrequencyNum(num, 1, ++logicalTimer);
                frequencyNums.put(num, frequencyNum);
            } else {
                FrequencyNum oldFrequencyNum = frequencyNums.get(num);
                oldFrequencyNum.count++;
            }
        }

        int[] res = frequencyNums.values().stream()
                .sorted(byFrequencyThenLogicalTimer)
                .map(frequencyNum -> frequencyNum.value)
//                .mapToInt(num -> num)
                .mapToInt(Integer::intValue)
                .toArray();

        return res;
    }

    static class FrequencyNum {
        public int value;
        public int count;
        public int logicalTimer;

        public FrequencyNum(int value, int count, int logicalTimer) {
            this.value = value;
            this.count = count;
            this.logicalTimer = logicalTimer;
        }
    }

    /**
     * Using Map to contain all frequency of each element.
     * And creating the output array to maintain the order of elements.
     * Then use this map as the condition to sort elements.
     *
     * But in the Comparator interface, we use the wrong the comparison when using value to compare after these frequencies is equal.
     *
     * @param nums
     * @return
     */
    private static int[] sortByFrequencyV2(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        for (int current : nums) {
            int count = map.getOrDefault(current, 0);
            map.put(current, count + 1);

            res.add(current);
        }

        Comparator<Integer> byFrequency = (f1, f2) -> {
            int freqCompare = map.get(f2).compareTo(map.get(f1));
            int valueCompare = f1.compareTo(f2);

            if (freqCompare == 0) {
                return valueCompare;
            } else {
                return freqCompare;
            }
        };
        Collections.sort(res, byFrequency);

        return res.stream()
                  .mapToInt(Integer::intValue)
                  .toArray();
    }

    private static int[] sortByFrequencyV3(int[] nums) {
        List<Integer> res = Arrays.stream(nums).boxed()
                                  .collect(Collectors.toList());
        Map<Integer, Integer> mapCount = new HashMap<>();
        Map<Integer, Integer> mapOrder = new HashMap<>();

        for (int i = 0; i < nums.length; ++i) {
            if (mapCount.containsKey(nums[i])) {
                mapCount.put(nums[i], mapCount.get(nums[i]) + 1);
            } else {
                mapCount.put(nums[i], 1);
                mapOrder.put(nums[i], i);
            }
        }

        Comparator<Integer> byFrequencyThenOrder = (f1, f2) -> {
            int frequencyCompare = mapCount.get(f2).compareTo(mapCount.get(f1));
            int orderCompare = mapOrder.get(f1).compareTo(mapOrder.get(f2));

            if (frequencyCompare == 0) {
                return orderCompare;
            } else {
                return frequencyCompare;
            }
        };

        Collections.sort(res, byFrequencyThenOrder);
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

}
