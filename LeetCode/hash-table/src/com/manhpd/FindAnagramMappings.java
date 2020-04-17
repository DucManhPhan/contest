package com.manhpd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Refer: https://leetcode.com/problems/find-anagram-mappings/
 */
public class FindAnagramMappings {

    public static void main(String[] args) {
//        int[] A = {12, 28, 46, 32, 50};
//        int[] B = {50, 12, 32, 46, 28};
        int[] A = {10, 20, 10};
        int[] B = {10, 10, 20};

//        int[] results = anagramMappings(A, B);
//        int[] results = anagramMappingsNormal(A, B);
        int[] results = anagramMappingsDuplicates(A, B);
        IntStream.of(results).forEach(item -> System.out.println(item + ", "));
    }

    /**
     * Time complexity: O(n^2)
     *
     * @param A
     * @param B
     * @return
     */
    public static int[] anagramMappingsNormal(int[] A, int[] B) {
        int size = A.length;
        int[] P = new int[size];

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (A[i] == B[j]) {
                    P[i] = j;
                }
            }
        }

        return P;
    }

    /**
     * Use HashMap
     * Time complexity: O(n)
     *
     * @param A
     * @param B
     * @return
     */
    public static int[] anagramMappings(int[] A, int[] B) {
        int[] P = new int[A.length];
        Map<Integer, Integer> positions = new HashMap<>();

        int bSize = B.length;
        for (int i = 0; i < bSize; ++i) {
            positions.put(B[i], i);
        }

        // process A array
        for (int i = 0; i < bSize; ++i) {
            int j = positions.get(A[i]);
            P[i] = j;
        }

        return P;
    }

    /**
     * Calculate some cases that include duplicate cases.
     *
     * @param A
     * @param B
     * @return
     */
    public static int[] anagramMappingsDuplicates(int[] A, int[] B) {
        int size = A.length;
        int[] P = new int[size];
        Map<Integer, List<Integer>> anagramMap = new HashMap<>();

        // process A array
        for (int i = 0; i < size; ++i) {
            anagramMap.computeIfAbsent(A[i], key -> new ArrayList<>()).add(i);
        }

        // process B array
        for (int j = 0; j < size; ++j) {
            List<Integer> ints = anagramMap.get(B[j]);
            int i = ints.get(ints.size() - 1);
            P[i] = j;

            ints.remove(ints.size() - 1);
        }

        return P;
    }

}
