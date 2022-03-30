package com.manhpd;

import java.util.*;

/**
 * Ref: https://leetcode.com/problems/number-of-recent-calls/
 * <p>
 * You have a RecentCounter class which counts the number of recent requests within a certain time frame.
 * <p>
 * Implement the RecentCounter class:
 * - RecentCounter() Initializes the counter with zero recent requests.
 * - int ping(int t) Adds a new request at time t, where t represents some time in milliseconds,
 * and returns the number of requests that has happened in the past 3000 milliseconds (including the new request).
 * Specifically, return the number of requests that have happened in the inclusive range [t - 3000, t].
 * <p>
 * It is guaranteed that every call to ping uses a strictly larger value of t than the previous call.
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["RecentCounter", "ping", "ping", "ping", "ping"]
 * [[], [1], [100], [3001], [3002]]
 * Output
 * [null, 1, 2, 3, 3]
 * <p>
 * Explanation:
 * RecentCounter recentCounter = new RecentCounter();
 * recentCounter.ping(1);     // requests = [1], range is [-2999,1], return 1
 * recentCounter.ping(100);   // requests = [1, 100], range is [-2900,100], return 2
 * recentCounter.ping(3001);  // requests = [1, 100, 3001], range is [1,3001], return 3
 * recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002], range is [2,3002], return 3
 * <p>
 * Constraints:
 * 1 <= t <= 109
 * Each test case will call ping with strictly increasing values of t.
 * At most 10^4 calls will be made to ping.
 */
public class NumberOfRecentCalls {

    /**
     * Using queue to contain requests
     *
     */
    public static class RecentCounter {

        private final LinkedList<Integer> timeFrames;

        private int count;

        public RecentCounter() {
            this.timeFrames = new LinkedList<>();
        }

        /**
         * This way was Time Limit Exceeded Error.
         *
         * @param t
         * @return
         */
        public int ping(int t) {
            this.timeFrames.offer(t);
            ++this.count;

            int oldestTime = t - 3000;
            if (oldestTime < 0) {
                return this.count;
            }

            int lessElementsCount = 0;
            Iterator<Integer> it = this.timeFrames.iterator();
            while (it.hasNext()) {
                int currentTime = it.next();

                if (currentTime >= oldestTime) {
                    break;
                }

                ++lessElementsCount;
            }

            return this.count - lessElementsCount;
        }
    }

    /**
     * Using binary search
     */
    public static class RecentCounterV2 {

        private List<Integer> timeFrames;

        public RecentCounterV2() {
            this.timeFrames = new ArrayList<>();
        }

        public int ping(int t) {
            this.timeFrames.add(t);

            int oldestTime = t - 3000;
            if (oldestTime < 0) {
                return this.timeFrames.size();
            }

            int currentIdx = Collections.binarySearch(this.timeFrames, oldestTime);
            if (currentIdx < 0) {
                currentIdx = -currentIdx - 1;
            }

            return this.timeFrames.size() - currentIdx;
        }

    }

    public static void main(String[] args) {
//        RecentCounter recentCounter = new RecentCounter();
        RecentCounterV2 recentCounter = new RecentCounterV2();
        int num = recentCounter.ping(1);     // requests = [1], range is [-2999,1], return 1
        System.out.println("Num: " + num);

        num = recentCounter.ping(100);   // requests = [1, 100], range is [-2900,100], return 2
        System.out.println("Num: " + num);

        num = recentCounter.ping(3001);  // requests = [1, 100, 3001], range is [1,3001], return 3
        System.out.println("Num: " + num);

        num = recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002], range is [2,3002], return 3
        System.out.println("Num: " + num);
    }

}
