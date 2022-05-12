package com.manhpd;

import java.util.*;

/**
 * Given a string s of '(' , ')' and lowercase English characters.
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 * Example 1:
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 *
 * Example 2:
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 *
 * Example 3:
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 *
 * Constraints:
 * 1 <= s.length <= 105
 * s[i] is either'(' , ')', or lowercase English letter.
 *
 */
public class MinimumRemoveToMakeValidParenthesis {

    public static void main(String[] args) {
        String s = "lee(t(c)o)de)";
//        String s = "a)b(c)d";
//        String s = "))((";
//        String s = "abc";

//        String result = minRemoveToMakeValid(s);
        String result = minRemoveToMakeValidV2(s);
        System.out.println("Result: " + result);
    }

    /**
     * Disadvantage: using too many data structures - List, Map, Stack.
     *
     * The time complexity: O(n)
     * The space complexity: O(n)
     *
     * @param s
     * @return
     */
    public static String minRemoveToMakeValid(String s) {
        List<CharacterData> characterDatas = new ArrayList<>();
        int idx = 0;

        for (char c : s.toCharArray()) {
            if (c == '(' || c == ')') {
                characterDatas.add(new CharacterData(c, idx));
            }

            ++idx;
        }

        if (characterDatas.isEmpty()) {
            return s;
        }

        Map<Character, Character> mpCharacters = new HashMap<>();
//        mpCharacters.put('(', ')');
        mpCharacters.put(')', '(');

        Stack<CharacterData> stk = new Stack<>();
        for (CharacterData cData : characterDatas) {
            if (stk.isEmpty()) {
                stk.push(cData);
                continue;
            }

            CharacterData topCharacterData = stk.peek();
            if (topCharacterData.c == mpCharacters.get(cData.c)) {
                stk.pop();
            } else {
                stk.push(cData);
            }
        }

        if (stk.empty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder(s);
        while (!stk.empty()) {
            CharacterData cData = stk.pop();
            sb.deleteCharAt(cData.pos);
        }

        return sb.toString();
    }

    static class CharacterData {
        public Character c;
        public int pos;

        public CharacterData(char c, int pos) {
            this.c = c;
            this.pos = pos;
        }
    }

    public static String minRemoveToMakeValidV2(String s) {
        StringBuilder sb = new StringBuilder(s);
        Stack<Integer> stk = new Stack<>();

        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                stk.push(i);
            } else if (s.charAt(i) == ')') {
                if (!stk.isEmpty()) {
                    stk.pop();
                } else {
                    sb.setCharAt(i, '#');
                }
            }
        }

        while (!stk.isEmpty()) {
            sb.setCharAt(stk.pop(), '#');
        }

        return sb.toString().replaceAll("#", "");
    }

}
