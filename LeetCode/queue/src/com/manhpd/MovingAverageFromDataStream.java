package com.manhpd;

/**
 * Ref: https://leetcode.com/problems/moving-average-from-data-stream/
 *
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * Example:
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 *
 * 1st way:
 * - Using circular queue --> O(n) with n is the number of elements in that queue
 *
 * 2nd way:
 * - each time we call the next() method, we will calculate sum right now. It is the same with prefix-sum problem.
 */
public class MovingAverageFromDataStream {

    private int[] stream;

    private int windowSize;

    private int currentIdx;

    private int currentCount;

    private int sum;

    public MovingAverageFromDataStream(int windowSize) {
        this.stream = new int[windowSize];
        this.windowSize = windowSize;
        this.currentCount = 0;
        this.currentIdx = 0;
    }

    public double next(int value) {
        sum -= this.stream[this.currentIdx];

        this.stream[currentIdx]= value;
        this.currentIdx = (this.currentIdx + 1) % this.windowSize;

        this.sum += value;
        this.currentCount = (this.currentCount == this.windowSize) ? this.windowSize : currentCount + 1;

        return (double) this.sum / this.currentCount;
    }

    public static void main(String[] args) {
        int size = 3;
        MovingAverageFromDataStream obj = new MovingAverageFromDataStream(size);

        double currentAvg = obj.next(1);
        System.out.println("Current average: " + currentAvg);

        currentAvg = obj.next(10);
        System.out.println("Current average: " + currentAvg);

        currentAvg = obj.next(3);
        System.out.println("Current average: " + currentAvg);

        currentAvg = obj.next(5);
        System.out.println("Current average: " + currentAvg);
    }

}
