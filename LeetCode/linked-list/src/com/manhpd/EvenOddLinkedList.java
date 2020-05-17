package com.manhpd;

//  Given a singly linked list, group all odd nodes together followed by the even nodes.
//  Please note here we are talking about the node number and not the value in the nodes.
//  You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
//
//  The relative order inside both the even and odd groups should remain as it was in the input.
//  The first node is considered odd, the second node even and so on ...
//
//  Example 1:
//  Input: 1->2->3->4->5->NULL
//  Output: 1->3->5->2->4->NULL
//
//  Example 2:
//  Input: 2->1->3->5->6->4->7->NULL
//  Output: 2->3->6->7->1->5->4->NULL

public class EvenOddLinkedList {

    public static void main(String[] args) {
        ListNode first = new ListNode(1);
        ListNode second= new ListNode(2);
        ListNode third= new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        ListNode six = new ListNode(6);

        first.next = second;
        second.next = third;
        third.next = four;
        four.next = five;

        ListNode head = oddEvenList(first);
        print(head);
    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        // odd list
        ListNode oddHeadNode = new ListNode(head.val);;
        ListNode tmp = head.next.next;
        ListNode tmpOddNode = oddHeadNode;
        ListNode endOddNode = null;

        while (tmp != null) {
            tmpOddNode.next = new ListNode(tmp.val);

            if (tmp.next == null) {
                endOddNode = tmpOddNode.next;
                break;
            }

            tmp = tmp.next.next;
            endOddNode = tmpOddNode.next;
            tmpOddNode = tmpOddNode.next;
        }

        // even list
        ListNode evenHeadNode = new ListNode(head.next.val);
        ListNode tmpEvenNode = evenHeadNode;
        tmp = head.next.next.next;

        while (tmp != null) {
            tmpEvenNode.next = new ListNode(tmp.val);

            if (tmp.next == null) {
                break;
            }

            tmp = tmp.next.next;
            tmpEvenNode = tmpEvenNode.next;
        }

        // combine odd and even list
        endOddNode.next = evenHeadNode;
        return oddHeadNode;
    }

    public static void print(ListNode head) {
        ListNode tmp = head;
        while (tmp != null) {
            System.out.print("" + tmp.val + " --> ");
            tmp = tmp.next;
        }

        System.out.println("null");
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
