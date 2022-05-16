package com.manhpd;

import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * Ref: https://leetcode.com/problems/tweet-counts-per-frequency/
 *
 * A social media company is trying to monitor activity on their site by analyzing the number of tweets that occur in select periods of time. These periods can be partitioned into smaller time chunks based on a certain frequency (every minute, hour, or day).
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
 *
 * Example:
 *
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
public class TweetCountsPerFrequencyV2 {

    private Map<String, TreeSet<Integer>> map = new HashMap<>();

    private Map<String, Integer> meta = new HashMap<>();

    public TweetCountsPerFrequencyV2() {
        this.meta.put("minute", 60);
        this.meta.put("hour", 3600);
        this.meta.put("day", 86400);
    }

    public void recordTweet(String tweetName, int time) {
        this.map.computeIfAbsent(tweetName, t -> new TreeSet<Integer>()).add(time);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        TreeSet<Integer> subset = (TreeSet<Integer>) map.getOrDefault(tweetName, new TreeSet<Integer>())
                                                        .subSet(startTime, true, endTime, true);
        List<Integer> out = new ArrayList<>();
        int chunk = this.meta.get(freq);

        for (int i = startTime; i <= endTime; i = i + chunk) {
            final int iEndRange = Math.min(i + chunk - 1, endTime);
            out.add(subset.subSet(i, true, iEndRange, true)
                          .size());
        }

        return out;
    }

    public static void main(String[] args) {
        TweetCountsPerFrequencyV2 tweetCounts = new TweetCountsPerFrequencyV2();
        tweetCounts.recordTweet("tweet3", 0);                              // New tweet "tweet3" at time 0
        tweetCounts.recordTweet("tweet3", 60);                             // New tweet "tweet3" at time 60
        tweetCounts.recordTweet("tweet3", 10);                             // New tweet "tweet3" at time 10
        List<Integer> res = new ArrayList<>();
         res = tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59); // return [2]; chunk [0,59] had 2 tweets
        System.out.println(res.toString());

        res = tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60); // return [2,1]; chunk [0,59] had 2 tweets, chunk [60,60] had 1 tweet
        System.out.println(res.toString());

        tweetCounts.recordTweet("tweet3", 120);                            // New tweet "tweet3" at time 120
        res = tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210);  // return [4]; chunk [0,210] had 4 tweets
        System.out.println(res.toString());
    }

}
