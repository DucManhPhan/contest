package com.manhpd;

public class CircularArray {

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 4, 5, 6};
        int index = 2;

//        printElementsTill(nums, index);
        printElementsTillNormal(nums, index);
    }

    private static void printElementsTillNormal(int[] nums, int index) {
        int length = nums.length;

        // create new char array
        int[] newNums = new int[2 * length];

        for (int i = 0; i < length; ++i) {
            newNums[i] = newNums[length + i] = nums[i];
        }

        for (int i = index; i < length + index; ++i) {
            System.out.println(newNums[i]);
        }
    }

    private static void printElementsTill(int[] nums, int index) {
        int length = nums.length;
        for (int i = index; i < length + index; ++i) {
            System.out.println(nums[i % length]);
        }
    }

}
