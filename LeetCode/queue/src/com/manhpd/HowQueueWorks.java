package com.manhpd;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class HowQueueWorks {

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 4, 5, 6, 7};

        HowQueueWorks.howQueueWorks(data);
        HowQueueWorks.howDequeWorks(data);
    }

    public static void howQueueWorks(int[] data) {
        Queue<Integer> queue = new LinkedList<>();
        for (int value : data) {
            queue.add(value);
        }

        System.out.println("Front element: " + queue.peek());   // 1
        System.out.println("Rear element");                     // 7
    }

    public static void howDequeWorks(int[] data) {
        Deque<Integer> deque = new LinkedList<>();

        for (int value : data) {
            deque.addLast(value);
        }

        System.out.println("Front element: " + deque.peekFirst());      // 1
        System.out.println("Rear element: " + deque.peekLast());        // 7
        System.out.println("Converted to array: " + Arrays.toString(deque.toArray()));      // 1, 2, 3, 4, 5, 6, 7
    }

}
