package com.manhpd;

import java.util.LinkedHashMap;

/**
 * Find the intersection point of two Linked Lists.
 */
public class Main {

    /**
     * Brute force
     * Time Complexity: O(m * n)
     * Space complexity: O(1)
     * @param a
     * @param b
     * @return
     */
    public static ListNode mergeNode(ListNode a, ListNode b) {
        // iterate the list a as an outside loop, loop for b as inner loop
        ListNode tmpA = a;

        while (tmpA != null) {
            ListNode tmpB = b;
            while (tmpB != null) {
                if (tmpA == tmpB) {
                    return tmpA;
                }

                tmpB = tmpB.next;
            }

            tmpA = tmpA.next;
        }

        return null;
    }

    /**
     * Using HashMap to improve the time complexity
     * Time complexity: O(n) with n is maximum length of two list.
     * Space complexity: O(n)
     *
     * 1. Using Set
     * 2. check element is existed or not on list B
     *
     * @param a
     * @param b
     * @return
     */
    public static ListNode mergeNodeV1(ListNode a, ListNode b) {
        LinkedHashMap<Integer, ListNode> map = new LinkedHashMap<>();

        ListNode tmpA = a;
        ListNode tmpB = b;

        while (tmpA != null) {
            map.put(tmpA.val, tmpA);
            tmpA = tmpA.next;
        }

        while (tmpB != null) {
            if (map.containsKey(tmpB.val)) {
                ListNode node = map.get(tmpB.val);

                if (node == tmpB) {
                    return tmpB;
                }
            }

            tmpB = tmpB.next;
        }

        return null;
    }

    public static void main(String[] args) {
        ListNode inter = new ListNode(8, new ListNode(4, new ListNode(5, null)));
        ListNode listA = new ListNode(4, new ListNode(1, inter));
        ListNode listB = new ListNode(5, new ListNode(6, new ListNode(1, inter)));

//        ListNode intersectedNode = mergeNode(listA, listB);
        ListNode intersectedNode = mergeNodeV1(listA, listB);
        System.out.println("Intersected at '" + intersectedNode.val + "'");
    }

}
