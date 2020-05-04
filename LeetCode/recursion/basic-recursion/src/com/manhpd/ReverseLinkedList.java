package com.manhpd;

/**
 * Reverse a singly linked list.
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 *
 * Ex:  Input: 1->2->3->4->5->NULL
 *      Output: 5->4->3->2->1->NULL
 *
 */
public class ReverseLinkedList {

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
        five.next = six;

//        ListNode reverseHead = reverseListByIterative(one);
        ListNode reverseHead = reverseListByRecursive(one);
        printList(reverseHead);
    }

    /**
     * Reverse Linked List with Iteration
     *
     * @param head
     * @return
     */
    public static ListNode reverseListByIterative(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode backward = null;
        ListNode current = head;
        ListNode forward = head.next;
        while (current != null) {
            current.next = backward;

            backward = current;
            current = forward;

            if (current != null) {
                forward = current.next;
            }
        }

        return backward;
    }

    /**
     * Reverse Linked List with Recursive
     *
     * @param head
     * @return
     */
    public static ListNode reverseListByRecursive(ListNode head) {
        return recursive(head);
    }

    public static ListNode recursive(ListNode current) {
        if (current == null || current.next == null) {
            return current;
        }

        ListNode last = recursive(current.next);
        current.next.next = current;
        current.next = null;

        return last;
    }

    private static void printList(ListNode head) {
        for (ListNode tmp = head; tmp != null; tmp = tmp.next) {
            System.out.println(tmp.val + ", ");
        }

        System.out.println();
    }

}
