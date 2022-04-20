package com.manhpd;

public class CircularLinkedList {

    private Node head;

    private Node tail;

    public void insert(int value) {
        Node newNode = new Node(value);

        if (this.head == null) {
            head = newNode;
        } else {
            this.tail.next = newNode;
        }

        this.tail = newNode;
        this.tail.next = head;
    }

    public void remove(int value) {
        Node currentNode = this.head;

        if (this.head == null) {
            return;
        }

        do {
            Node nextNode = currentNode.next;

            if (nextNode.value == value) {
                if (this.tail == this.head) {
                    this.head = null;
                    this.tail = null;
                } else {
                    currentNode.next = nextNode.next;
                    if (this.head == nextNode) {
                        this.head = this.head.next;
                    }

                    if (this.tail == nextNode) {
                        this.tail = currentNode;
                    }
                }

                break;
            }

            currentNode = nextNode;
        } while (currentNode != this.head);
    }

    public boolean contains(int value) {
        Node currentNode = this.head;

        if (this.head == null) {
            return false;
        }

        do {
            if (currentNode.value == value) {
                return true;
            }

            currentNode = currentNode.next;
        } while (currentNode != this.head);

        return false;
    }

    public void traverse() {
        Node currentNode = this.head;

        if (this.head != null) {
            do {
                System.out.println(currentNode.value + " ");
                currentNode = currentNode.next;
            } while (currentNode != this.head);
        }
    }

    public void remove(Node node) {
        if (node == null) {
            return;
        }

        Node currentNode = this.head;

        do {
            if (currentNode.next == node) {
                currentNode.next = node.next;

                if (this.head == node) {
                    this.head = this.head.next;
                }

                if (this.tail == node) {
                    this.tail = currentNode;
                }

                break;
            }

            currentNode = currentNode.next;
        } while (currentNode != this.head);
    }

    public boolean hasNotRemainedOne() {
        return this.head != this.tail;
    }

    public Node incrementWithSteps(Node startNode, int k) {
        if (k == 1) {
            return startNode;
        }

        if (k < 1 || startNode == null) {
            return null;
        }

        Node currentNode = startNode;
        do {
            currentNode = currentNode.next;
            --k;
        } while (k != 1);

        return currentNode;
    }

    public Node head() {
        return this.head;
    }

    public static void main(String[] args) {
        int n = 5;
        CircularLinkedList circularLinkedList = new CircularLinkedList();

        for (int i = 1; i <= n; ++i) {
            circularLinkedList.insert(i);
        }

        Node kNode = circularLinkedList.incrementWithSteps(circularLinkedList.head(), 2);
        System.out.println("Value of kNode: " + kNode.value);
    }

    static class Node {

        public Node next;

        public int value;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

}
