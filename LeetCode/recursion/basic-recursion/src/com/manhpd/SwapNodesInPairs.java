package com.manhpd;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * Ex1: Given 1->2->3->4, you should return the list as 2->1->4->3.
 */
public class SwapNodesInPairs {

    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        ListNode six = new ListNode(6);

        // 1->2->3->4
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
//        five.next = six;

//        ListNode head = swapPairsByIteration(one);
        ListNode head = swapPairsByRecursion(one);
        printList(head);
    }

    /**
     * Using iteration to swap nodes
     *
     * @param head
     * @return
     */
    public static ListNode swapPairsByIteration(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode tmp = head;
        head = tmp.next;
        int count = 0;
        ListNode prev = null;
        while (tmp != null) {
            if (((count & 1) == 0) && tmp.next != null) {     // odd position
                ListNode nextNode = tmp.next;
                tmp.next = nextNode.next;
                nextNode.next = tmp;

                if (prev != null) {
                    prev.next = nextNode;
                }
            }

            count += 2;
            prev = tmp;
            tmp = tmp.next;
        }

        return head;
    }

    public static ListNode swapPairsByRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode fakeFirstNode = head.next;
        recursive(head, null, 0);

        return fakeFirstNode;
    }

    public static void recursive(ListNode head, ListNode prev, int pos) {
        if (head == null || head.next == null) {
            return;
        }

        if (((pos & 1) == 0) && head.next != null) {
            ListNode nextNode = head.next;
            head.next = nextNode.next;
            nextNode.next = head;

            if (prev != null) {
                prev.next = nextNode;
            }
        }

        recursive(head.next, head, pos + 2);
    }

    private static void printList(ListNode head) {
        for (ListNode tmp = head; tmp != null; tmp = tmp.next) {
            System.out.println(tmp.val + ", ");
        }

        System.out.println();
    }

}

class ListNode {

    public int val;

    public ListNode next;

    ListNode() {
        // nothing to do
    }

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}