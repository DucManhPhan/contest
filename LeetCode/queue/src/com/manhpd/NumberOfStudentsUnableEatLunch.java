package com.manhpd;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Ref: https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/
 *
 * The school cafeteria offers circular and square sandwiches at lunch break, referred to by numbers 0 and 1 respectively.
 * All students stand in a queue. Each student either prefers square or circular sandwiches.
 *
 * The number of sandwiches in the cafeteria is equal to the number of students.
 * The sandwiches are placed in a stack. At each step:
 *
 * If the student at the front of the queue prefers the sandwich on the top of the stack, they will take it and leave the queue.
 * Otherwise, they will leave it and go to the queue's end.
 * This continues until none of the queue students want to take the top sandwich and are thus unable to eat.
 *
 * You are given two integer arrays students and sandwiches where sandwiches[i] is the type of the i​​​​​​th sandwich in the stack (i = 0 is the top of the stack) and students[j] is the preference of the j​​​​​​th student in the initial queue (j = 0 is the front of the queue). Return the number of students that are unable to eat.
 *
 * Example 1:
 *
 * Input: students = [1,1,0,0], sandwiches = [0,1,0,1]
 * Output: 0
 * Explanation:
 * - Front student leaves the top sandwich and returns to the end of the line making students = [1,0,0,1].
 * - Front student leaves the top sandwich and returns to the end of the line making students = [0,0,1,1].
 * - Front student takes the top sandwich and leaves the line making students = [0,1,1] and sandwiches = [1,0,1].
 * - Front student leaves the top sandwich and returns to the end of the line making students = [1,1,0].
 * - Front student takes the top sandwich and leaves the line making students = [1,0] and sandwiches = [0,1].
 * - Front student leaves the top sandwich and returns to the end of the line making students = [0,1].
 * - Front student takes the top sandwich and leaves the line making students = [1] and sandwiches = [1].
 * - Front student takes the top sandwich and leaves the line making students = [] and sandwiches = [].
 * Hence all students are able to eat.
 *
 * Example 2:
 * Input: students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
 * Output: 3
 *
 * Constraints:
 *
 * 1 <= students.length, sandwiches.length <= 100
 * students.length == sandwiches.length
 * sandwiches[i] is 0 or 1.
 * students[i] is 0 or 1.
 *
 */
public class NumberOfStudentsUnableEatLunch {

    public static void main(String[] args) {
//        int[] students = {1, 1, 1, 0, 0, 1};
//        int[] sandwiches = {1, 0, 0, 0, 1, 1};

        int[] students = {1, 1, 0, 0};
        int[] sandwiches = {0, 1, 0, 1};

        NumberOfStudentsUnableEatLunch numberOfStudentsUnableEatLunch = new NumberOfStudentsUnableEatLunch();
        int res = numberOfStudentsUnableEatLunch.countStudentsV2(students, sandwiches);

        System.out.println("Result: " + res);
    }

    /**
     * Using Queue data structure for this problem
     *
     * @param students
     * @param sandwiches
     * @return
     */
    public int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> qStudents = new LinkedList<>();

        for (int value : students) {
            qStudents.add(value);
        }

        int topPositionOfSandwich = 0;
        int numStudentsUnableEat = 0;
        while (!qStudents.isEmpty() && numStudentsUnableEat < qStudents.size()) {
            if (sandwiches[topPositionOfSandwich] == qStudents.peek()) {
                numStudentsUnableEat = 0;

                ++topPositionOfSandwich;
                qStudents.poll();
            } else {
                ++numStudentsUnableEat;

                qStudents.add(qStudents.poll());
            }
        }

        return numStudentsUnableEat;
    }

    /**
     * Optimized the above solution
     *
     * @param students
     * @param sandwiches
     * @return
     */
    public int countStudentsV2(int[] students, int[] sandwiches) {
        int circularSandwiches = 0;
        int squareSandwiches = 0;

        for (int i = 0; i < students.length; ++i) {
            if (students[i] == 0) {
                ++circularSandwiches;
            } else {
                ++squareSandwiches;
            }
        }

        for (int i = 0; i < sandwiches.length; ++i) {
            if (sandwiches[i] == 0) {
                if (circularSandwiches == 0) {
                    return squareSandwiches;
                }

                --circularSandwiches;
            } else {
                if (squareSandwiches == 0) {
                    return circularSandwiches;
                }

                --squareSandwiches;
            }
        }

        return circularSandwiches + squareSandwiches;
    }

}
