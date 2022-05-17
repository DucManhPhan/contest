package com.manhpd;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
 */
public class TweetCountsPerFrequency {

    private Map<String, List<Integer>> tweetInfos;

    private Map<String, Integer> freqs;

    public TweetCountsPerFrequency() {
        this.tweetInfos = new HashMap<>();

        this.freqs = new HashMap<>();
        this.freqs.put("minute", 60);
        this.freqs.put("hour", 3600);
        this.freqs.put("day", 86400);
    }

    public void recordTweet(String tweetName, int time) {
        List<Integer> currentTimesPerTweet = this.tweetInfos.computeIfAbsent(tweetName, t -> new ArrayList<>());

        int idx = this.searchInsertPosition(currentTimesPerTweet, time);
        currentTimesPerTweet.add(idx, time);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        List<Integer> timesPerTweet = this.tweetInfos.get(tweetName);
        int startIdx = this.searchInsertPosition(timesPerTweet, startTime);
        int endIdx = this.searchInsertPosition(timesPerTweet, endTime);

        int duration = this.freqs.get(freq);
        int outputSize = ((endTime - startTime) / duration) + 1;
//        List<Integer> res = new ArrayList<>(outputSize);
        int[] res = new int[outputSize];

        for (int i = startIdx; i <= endIdx; ++i) {
            int tmpResIdx = (timesPerTweet.get(i) - startTime) / duration;
            res[tmpResIdx] += 1;
        }

        return Stream.of(res).collect(Collectors.toCollection(ArrayList::new));
    }

    private int searchInsertPosition(List<Integer> timesPerTweet, int currentTime) {
        int left = 0;
        int right = timesPerTweet.size();

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            int midTime = timesPerTweet.get(mid);
            if (midTime == currentTime) {
                return mid;
            } else if (midTime < currentTime) {
                left = mid;
            } else {
                right = mid;
            }
        }

        if (timesPerTweet.get(left) >= currentTime) {
            return left;
        }

        return right;
    }

    public static void main(String[] args) {
        TweetCountsPerFrequency tweetCounts = new TweetCountsPerFrequency();
        tweetCounts.recordTweet("tweet3", 0);                              // New tweet "tweet3" at time 0
        tweetCounts.recordTweet("tweet3", 60);                             // New tweet "tweet3" at time 60
        tweetCounts.recordTweet("tweet3", 10);                             // New tweet "tweet3" at time 10
//        tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59); // return [2]; chunk [0,59] had 2 tweets
//        tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60); // return [2,1]; chunk [0,59] had 2 tweets, chunk [60,60] had 1 tweet
//        tweetCounts.recordTweet("tweet3", 120);                            // New tweet "tweet3" at time 120
//        tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210);  // return [4]; chunk [0,210] had 4 tweets
    }

}
