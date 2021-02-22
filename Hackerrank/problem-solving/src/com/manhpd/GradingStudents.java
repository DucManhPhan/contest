package com.manhpd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.hackerrank.com/challenges/grading/problem
 */
public class GradingStudents {

    public static void main(String[] args) {
//        List<Integer> grades = new ArrayList<Integer>(Arrays.asList(73, 67, 38, 33));
//        List<Integer> grades = new ArrayList<Integer>(Arrays.asList(84, 29, 57));
        List<Integer> grades = new ArrayList<Integer>(Arrays.asList(84, 94, 21, 0, 18, 100, 18, 62, 30, 61, 53, 0, 43, 2, 29, 53, 61, 40, 14, 4, 29, 98, 37, 23, 46, 9, 79, 62, 20, 38, 51, 99, 59, 47, 4, 86, 61, 68, 17, 45, 6, 1, 95, 95));
        gradingStudents(grades).stream().forEach(grade -> System.out.println(grade));
    }

    public static List<Integer> gradingStudents(List<Integer> grades) {
        List<Integer> res = new ArrayList<>();
        for (int grade : grades) {
            if (grade < 38) {
                res.add(grade);
            } else {
                int remainder = grade % 10;
                int nextMultiple5 = grade - remainder + 5;
                int tmp = nextMultiple5 - grade;

                if (tmp > 0 && tmp < 3) {
                    res.add(nextMultiple5);
                } else if (tmp + 5 < 3) {
                    res.add(nextMultiple5 + 5);
                } else {
                    res.add(grade);
                }
            }
        }

        return res;
    }

}
