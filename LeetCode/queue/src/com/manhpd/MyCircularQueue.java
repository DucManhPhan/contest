package com.manhpd;

/**
 * Ref: https://leetcode.com/explore/learn/card/queue-stack/228/first-in-first-out-data-structure/1337/
 *
 * Implementation the MyCircularQueue class:
 *
 * MyCircularQueue(k) Initializes the object with the size of the queue to be k.
 * int Front() Gets the front item from the queue. If the queue is empty, return -1.
 * int Rear() Gets the last item from the queue. If the queue is empty, return -1.
 * boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
 * boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
 * boolean isEmpty() Checks whether the circular queue is empty or not.
 * boolean isFull() Checks whether the circular queue is full or not.
 *
 * You must solve the problem without using the built-in queue data structure in your programming language.
 *
 * Example 1:
 * Input
 * ["MyCircularQueue", "enQueue", "enQueue", "enQueue", "enQueue", "Rear", "isFull", "deQueue", "enQueue", "Rear"]
 * [[3], [1], [2], [3], [4], [], [], [], [4], []]
 * Output
 * [null, true, true, true, false, 3, true, true, true, 4]
 *
 * Explanation
 * MyCircularQueue myCircularQueue = new MyCircularQueue(3);
 * myCircularQueue.enQueue(1); // return True
 * myCircularQueue.enQueue(2); // return True
 * myCircularQueue.enQueue(3); // return True
 * myCircularQueue.enQueue(4); // return False
 * myCircularQueue.Rear();     // return 3
 * myCircularQueue.isFull();   // return True
 * myCircularQueue.deQueue();  // return True
 * myCircularQueue.enQueue(4); // return True
 * myCircularQueue.Rear();     // return 4
 *
 * Drawbacks:
 * - always take care how to increment 1 for tail and head pointers. --> Using modulo operator.
 */
public class MyCircularQueue {

    private int head = -1;

    private int tail = -1;

    private final int[] data;

    public MyCircularQueue(int k) {
        this.data = new int[k];
    }

    public boolean enQueue(int value) {
        if (this.isFull()) {
            return false;
        }

        if (this.tail == this.data.length - 1) {
            this.tail = -1;
        }

        ++this.tail;
        if (this.tail == 0 && this.head == -1) {
            ++this.head;
        }

        this.data[this.tail] = value;

        return true;
    }

    public boolean deQueue() {
        if (this.isEmpty()) {   // queue is empty
            return false;
        }

        if (this.head == this.tail) {
            this.head = -1;
            this.tail = -1;
            return true;
        }

        if (this.head == this.data.length - 1) {
            this.head = -1;
        }

        ++this.head;
        return true;
    }

    public int Front() {
        if (this.isEmpty()) {
            return -1;
        }

        return this.data[this.head];
    }

    public int Rear() {
        if (this.isEmpty()) {
            return -1;
        }

        return this.data[this.tail];
    }

    public boolean isEmpty() {
        return this.head == -1 && this.tail == -1;
    }

    public boolean isFull() {
        int dumpTail = this.tail;
        if (dumpTail == this.data.length - 1) {
            dumpTail = -1;
        }

        return this.head == dumpTail + 1;
    }

    public static void main(String[] args) {
//        runTestCase1();
//        runTestCase2();
//        runTestCase3();
//        runTestCase4();
        runTestCase5();
    }

    public static void runTestCase2() {
        int k = 6;
        MyCircularQueue obj = new MyCircularQueue(k);

        boolean bEn = obj.enQueue(6);
        System.out.println("enQueue: " + bEn);

        int rear = obj.Rear();
        System.out.println("Rear: " + rear);

        rear = obj.Rear();
        System.out.println("Rear: " + rear);

        boolean bDe = obj.deQueue();
        System.out.println("Dequeue: " + bDe);

        bEn = obj.enQueue(5);
        System.out.println("enQueue: " + bEn);

        rear = obj.Rear();
        System.out.println("Rear: " + rear);

        bDe = obj.deQueue();
        System.out.println("Dequeue: " + bDe);

        int front = obj.Front();
        System.out.println("Front: " + front);

        bDe = obj.deQueue();
        System.out.println("Dequeue: " + bDe);

        bDe = obj.deQueue();
        System.out.println("Dequeue: " + bDe);

        bDe = obj.deQueue();
        System.out.println("Dequeue: " + bDe);
    }

    public static void runTestCase1() {
        int k = 3;
        MyCircularQueue obj = new MyCircularQueue(k);

        boolean bEn = obj.enQueue(1);
        System.out.println("enQueue: " + bEn);

        bEn = obj.enQueue(2);
        System.out.println("enQueue: " + bEn);

        bEn = obj.enQueue(3);
        System.out.println("enQueue: " + bEn);

        bEn = obj.enQueue(4);
        System.out.println("enQueue: " + bEn);

        int param_4 = obj.Rear();
        System.out.println("Rear: " + param_4);

        boolean param_6 = obj.isFull();
        System.out.println("isFull: " + param_6);

        boolean param_2 = obj.deQueue();
        System.out.println("deQueue: " + param_2);

        bEn = obj.enQueue(4);
        System.out.println("enQueue: " + bEn);

        param_4 = obj.Rear();
        System.out.println("Rear: " + param_4);
    }

    /**
     * ["MyCircularQueue","enQueue","enQueue","deQueue","enQueue","deQueue","enQueue","deQueue","enQueue","deQueue", "Front"]
     * [[2],[1],[2],[],[3],[],[3],[],[3],[],[]]
     */
    public static void runTestCase3() {
        int k = 2;
        MyCircularQueue obj = new MyCircularQueue(k);

        boolean bEn = obj.enQueue(1);
        System.out.println("enQueue: " + bEn);

        bEn = obj.enQueue(2);
        System.out.println("enQueue: " + bEn);

        boolean bDe = obj.deQueue();
        System.out.println("Dequeue: " + bDe);

        bEn = obj.enQueue(3);
        System.out.println("enQueue: " + bEn);

        bDe = obj.deQueue();
        System.out.println("Dequeue: " + bDe);

        bEn = obj.enQueue(3);
        System.out.println("enQueue: " + bEn);

        bDe = obj.deQueue();
        System.out.println("Dequeue: " + bDe);

        bEn = obj.enQueue(3);
        System.out.println("enQueue: " + bEn);

        bDe = obj.deQueue();
        System.out.println("Dequeue: " + bDe);

        int front = obj.Front();
        System.out.println("Front: " + front);
    }

    /**
     * ["MyCircularQueue","enQueue","Front","isFull","enQueue","enQueue","enQueue","deQueue","enQueue","enQueue","isEmpty","Rear"]
     * [[4],[3],[],[],[7],[2],[5],[],[4],[2],[],[]]
     *
     */
    public static void runTestCase4() {
        int k = 4;
        MyCircularQueue obj = new MyCircularQueue(k);

        boolean bEn = obj.enQueue(3);
        System.out.println("enQueue: " + bEn);

        int front = obj.Front();
        System.out.println("Front: " + front);

        boolean isFull = obj.isFull();
        System.out.println("isFull: " + isFull);

        bEn = obj.enQueue(7);
        System.out.println("enQueue: " + bEn);

        bEn = obj.enQueue(2);
        System.out.println("enQueue: " + bEn);

        bEn = obj.enQueue(5);
        System.out.println("enQueue: " + bEn);

        boolean bDe = obj.deQueue();
        System.out.println("Dequeue: " + bDe);

        bEn = obj.enQueue(4);
        System.out.println("enQueue: " + bEn);

        bEn = obj.enQueue(2);
        System.out.println("enQueue: " + bEn);

        boolean isEmpty = obj.isEmpty();
        System.out.println("isEmpty: " + isEmpty);

        int rear = obj.Rear();
        System.out.println("Rear: " + rear);
    }

    /**
     * ["MyCircularQueue","enQueue","enQueue","enQueue","enQueue","Rear","isFull","deQueue","enQueue","Rear"]
     * [[3],[1],[2],[3],[4],[],[],[],[4],[]]
     *
     */
    public static void runTestCase5() {
        int k = 3;
        MyCircularQueue obj = new MyCircularQueue(k);

        boolean bEn = obj.enQueue(1);
        System.out.println("enQueue: " + bEn);

        bEn = obj.enQueue(2);
        System.out.println("enQueue: " + bEn);

        bEn = obj.enQueue(3);
        System.out.println("enQueue: " + bEn);

        bEn = obj.enQueue(4);
        System.out.println("enQueue: " + bEn);

        int rear = obj.Rear();
        System.out.println("Rear: " + rear);

        boolean isFull = obj.isFull();
        System.out.println("isFull: " + isFull);

        boolean bDe = obj.deQueue();
        System.out.println("Dequeue: " + bDe);

        bEn = obj.enQueue(4);
        System.out.println("enQueue: " + bEn);

        rear = obj.Rear();
        System.out.println("Rear: " + rear);
    }

}
