package com.manhpd;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    public static void main(String[] args) {
        String s = "AADOBECODEBANC";
        String t = "ABC";

//        String s = "ab";
//        String t = "b";

//        String s = "bdab";
//        String t = "ab";

//        String s = "acbbaca";
//        String t = "aba";

        MinimumWindowSubstring solution = new MinimumWindowSubstring();
        System.out.println(solution.minWindow(s, t));
    }

    public String minWindow(String s, String t) {
        Map<Character, Integer> countCharacters = new HashMap<>();
        for (char c : t.toCharArray()) {
            countCharacters.put(c, countCharacters.getOrDefault(c, 0) + 1);
        }

        int windowStart = 0;
        int tSize = t.length();
        int minSize = Integer.MAX_VALUE;
        String res = "";

        while (windowStart < s.length() && !countCharacters.containsKey(s.charAt(windowStart))) {
            ++windowStart;
        }

        for (int windowEnd = 0; windowEnd < s.length(); ++windowEnd) {
            char c = s.charAt(windowEnd);

            if (countCharacters.containsKey(c)) {
                --tSize;
                countCharacters.put(c, countCharacters.get(c) - 1);

                while (countCharacters.get(c) < 0 && countCharacters.containsKey(s.charAt(windowStart))) {
                    char startChar = s.charAt(windowStart);
                    if (windowStart < s.length() && countCharacters.containsKey(startChar)) {
                        ++tSize;
                        countCharacters.put(startChar, countCharacters.get(startChar) + 1);
                    }

                    ++windowStart;
                }

                if (tSize == 0 && this.hasAcceptSubstring(countCharacters, t)) {
                    if (windowEnd - windowStart + 1 < minSize) {
                        minSize = windowEnd - windowStart + 1;
                        res = s.substring(windowStart, windowEnd + 1);
                    }

//                    ++tSize;

//                    if (windowStart < s.length()) {
//                        countCharacters.put(s.charAt(windowStart), countCharacters.get(s.charAt(windowStart)) + 1);
//                        ++windowStart;
//
//                        while (windowStart < s.length() && !countCharacters.containsKey(s.charAt(windowStart))) {
//                            ++windowStart;
//                        }
//                    }
                }
            }
        }

        return res;
    }

    private boolean hasAcceptSubstring(Map<Character, Integer> countCharacters, String t) {
        for (Map.Entry<Character, Integer> e : countCharacters.entrySet()) {
            if (e.getValue() != 0) {
                return false;
            }
        }

        return true;
    }
}
