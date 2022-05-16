package com.manhpd;

import java.util.*;

/**
 * Ref: https://leetcode.com/problems/tweet-counts-per-frequency/
 *
 */
public class TweetCountsPerFrequencyV3 {

    private Map<String, TreeMap<Integer, Integer>> map;

    private Map<String, Integer> freqMap;

    public TweetCountsPerFrequencyV3() {
        this.map = new HashMap<>();

        this.freqMap = new HashMap<>();
        this.freqMap.put("minute", 60);
        this.freqMap.put("hour", 3600);
        this.freqMap.put("day", 86400);
    }

    public void recordTweet(String tweetName, int time) {
        if (!this.map.containsKey(tweetName)) {
            this.map.put(tweetName, new TreeMap<>());
        }

        TreeMap<Integer, Integer> tweetMap = this.map.get(tweetName);
        tweetMap.put(time, tweetMap.getOrDefault(time, 0) + 1);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        if (!this.map.containsKey(tweetName)) {
            return null;
        }

        List<Integer> res = new LinkedList<>();
        int interval = this.freqMap.get(freq);
        int size = ((endTime - startTime) / interval) + 1;
        int[] buckets = new int[size];

        TreeMap<Integer, Integer> tweetMap = this.map.get(tweetName);
        for (Map.Entry<Integer, Integer> entry : tweetMap.subMap(startTime, endTime + 1).entrySet()) {
            int idx = (entry.getKey() - startTime) / interval;
            buckets[idx] += entry.getValue();
        }

        for (int num : buckets) {
            res.add(num);
        }

        return res;
    }

    public static void main(String[] args) {
        TweetCountsPerFrequencyV3 tweetCounts = new TweetCountsPerFrequencyV3();
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
