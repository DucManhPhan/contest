package com.manhpd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Ref: https://leetcode.com/problems/tweet-counts-per-frequency/
 *
 * A social media company is trying to monitor activity on their site by analyzing the number of tweets that occur in select periods of time.
 * These periods can be partitioned into smaller time chunks based on a certain frequency (every minute, hour, or day).
 *
 * For example, the period [10, 10000] (in seconds) would be partitioned into the following time chunks with these frequencies:
 *
 * Every minute (60-second chunks): [10,69], [70,129], [130,189], ..., [9970,10000]
 * Every hour (3600-second chunks): [10,3609], [3610,7209], [7210,10000]
 * Every day (86400-second chunks): [10,10000]
 * Notice that the last chunk may be shorter than the specified frequency's chunk size and will always end with the end time of the period (10000 in the above example).
 *
 * Design and implement an API to help the company with their analysis.
 *
 * Implement the TweetCounts class:
 *
 * TweetCounts() Initializes the TweetCounts object.
 * void recordTweet(String tweetName, int time) Stores the tweetName at the recorded time (in seconds).
 * List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) Returns a list of integers representing the number of tweets with tweetName in each time chunk for the given period of time [startTime, endTime] (in seconds) and frequency freq.
 * freq is one of "minute", "hour", or "day" representing a frequency of every minute, hour, or day respectively.
 *
 * Example:
 * Input
 * ["TweetCounts","recordTweet","recordTweet","recordTweet","getTweetCountsPerFrequency","getTweetCountsPerFrequency","recordTweet","getTweetCountsPerFrequency"]
 * [[],["tweet3",0],["tweet3",60],["tweet3",10],["minute","tweet3",0,59],["minute","tweet3",0,60],["tweet3",120],["hour","tweet3",0,210]]
 *
 * Output
 * [null,null,null,null,[2],[2,1],null,[4]]
 *
 * Explanation
 * TweetCounts tweetCounts = new TweetCounts();
 * tweetCounts.recordTweet("tweet3", 0);                              // New tweet "tweet3" at time 0
 * tweetCounts.recordTweet("tweet3", 60);                             // New tweet "tweet3" at time 60
 * tweetCounts.recordTweet("tweet3", 10);                             // New tweet "tweet3" at time 10
 * tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59); // return [2]; chunk [0,59] had 2 tweets
 * tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60); // return [2,1]; chunk [0,59] had 2 tweets, chunk [60,60] had 1 tweet
 * tweetCounts.recordTweet("tweet3", 120);                            // New tweet "tweet3" at time 120
 * tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210);  // return [4]; chunk [0,210] had 4 tweets
 *
 *
 * Constraints:
 *
 * 0 <= time, startTime, endTime <= 109
 * 0 <= endTime - startTime <= 104
 * There will be at most 104 calls in total to recordTweet and getTweetCountsPerFrequency.
 *
 * This solution didn't work. Because:
 * - Using array list to contain all tweets. So find the tweet based on its name makes to iterate this array. It's really slow.
 *
 */
public class TweetCountsPerFrequency {

    private List<TweetInfo> tweetInfos;

    private String[] freqs = {"minute", "hour", "day"};

    private int[] timeChunks = {59, 3599, 86399};

    public TweetCountsPerFrequency() {
        this.tweetInfos = new ArrayList<>();
    }

    public void recordTweet(String tweetName, int time) {
        final TweetInfo currentTweetInfo = new TweetInfo(tweetName, time);
        if (this.tweetInfos.size() == 0) {
            this.tweetInfos.add(currentTweetInfo);
            return;
        }

        int insertPosition = this.searchInsertPosition(currentTweetInfo);
        this.tweetInfos.add(insertPosition, currentTweetInfo);
    }

    /**
     * When using binary search for this problem, we can refer to this article in Python's solution:
     * https://leetcode.com/problems/tweet-counts-per-frequency/discuss/922832/100.00-Runtime-fast-and-short-binary-search-solution
     *
     * @param freq
     * @param tweetName
     * @param startTime
     * @param endTime
     * @return
     */
    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        int otherEndTime = this.getOtherEndTime(freq, startTime, endTime);
        int startIdx = this.searchInsertPosition(new TweetInfo(tweetName, startTime));
        int endIdx = this.searchInsertPosition(new TweetInfo(tweetName, otherEndTime));

        List<Integer> res = new ArrayList<>();
        int timeChunks = this.getTimeChunks(freq);
        int i = startIdx;
        int nextChunksIdx = -1;
        while (i <= endIdx) {
//        for (int i = startIdx; i < endIdx; ++i) {
            TweetInfo currentTweet = this.tweetInfos.get(i);
            nextChunksIdx = this.searchInsertPosition(new TweetInfo(tweetName, currentTweet.time + timeChunks));

            if (nextChunksIdx > endIdx) {
                nextChunksIdx = endIdx;
            }

            int count = 0;
            for (int j = i; j <= nextChunksIdx; ++j) {
                TweetInfo tmpTweet = this.tweetInfos.get(j);
                if (tmpTweet.tweetName.equals(tweetName) && tmpTweet.time <= currentTweet.time + timeChunks) {
                    ++count;
                }
            }

            res.add(count);
            i = nextChunksIdx;
        }

        return res;
    }

    private int getOtherEndTime(String freq, int startTime, int endTime) {
        int otherEndTime = 0;
        for (int i = 0; i < this.freqs.length; ++i) {
            String currentFreq = this.freqs[i];
            if (currentFreq.equals(freq)) {
                if (endTime > this.timeChunks[i] + startTime) {
                    otherEndTime = this.timeChunks[i] + startTime;
                } else {
                    otherEndTime = endTime;
                }
            }
        }

        return otherEndTime;
    }

    private int getTimeChunks(String freq) {
        for (int i = 0; i < this.freqs.length; ++i) {
            String currentFreq = this.freqs[i];
            if (currentFreq.equals(freq)) {
                return this.timeChunks[i];
            }
        }

        return -1;
    }

    private int searchInsertPosition(TweetInfo tweetInfo) {
        int left = 0;
        int right = this.tweetInfos.size();

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            final TweetInfo midTweetInfo = this.tweetInfos.get(mid);
            if (midTweetInfo.time == tweetInfo.time) {
                return mid;
            } else if (midTweetInfo.time < tweetInfo.time) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (this.tweetInfos.get(left).time >= tweetInfo.time) {
            return left;
        }

        return right;
    }

    class TweetInfo {
        public String tweetName;
        public int time;

        public TweetInfo(String tweetName, int time) {
            this.tweetName = tweetName;
            this.time = time;
        }
    }

    public static void main(String[] args) {
        TweetCountsPerFrequency tweetCounts = new TweetCountsPerFrequency();
        tweetCounts.recordTweet("tweet3", 0);                              // New tweet "tweet3" at time 0
        tweetCounts.recordTweet("tweet3", 60);                             // New tweet "tweet3" at time 60
        tweetCounts.recordTweet("tweet3", 10);                             // New tweet "tweet3" at time 10
        List<Integer> res = new ArrayList<>();
//         res = tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59); // return [2]; chunk [0,59] had 2 tweets
//        System.out.println(res.toString());

        res = tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60); // return [2,1]; chunk [0,59] had 2 tweets, chunk [60,60] had 1 tweet
        System.out.println(res.toString());
//        tweetCounts.recordTweet("tweet3", 120);                            // New tweet "tweet3" at time 120
//        tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210);  // return [4]; chunk [0,210] had 4 tweets
    }

}
