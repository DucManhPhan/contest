package com.manhpd;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 *
 * Example:
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [1,3,2]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 */
public class InorderBinaryTree {

    public static void main(String[] args) {
        TreeNode first = new TreeNode(1);
        TreeNode second = new TreeNode(2);
        TreeNode third = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);
        TreeNode eight = new TreeNode(8);
        TreeNode nine = new TreeNode(9);
        TreeNode ten = new TreeNode(10);

        first.left = second;
        first.right = third;

        second.left = four;
        second.right = five;

        third.left = six;
        third.right = seven;

//        four.right = eight;
//
//        five.left = nine;
//        five.right = ten;

        // recursive version
//        List<Integer> res = new ArrayList<>();
//        inorderTraversal(first, res);

        // iterative version
        List<Integer> res = inorderTraversal(first);

        res.stream().map(item -> item + " --> ").forEach(item -> System.out.print(item));
        System.out.println("null");
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stk = new Stack<>();
        TreeNode tmp = root;
        boolean isContinue = true;

        do {
            // get the left side
            while (tmp != null) {
                stk.push(tmp);
                tmp = tmp.left;
            }

            // goes with right side
            if (stk.size() > 0) {
                tmp = stk.pop();
                res.add(tmp.val);

                tmp = tmp.right;
                isContinue = true;
            } else {
                isContinue = false;
            }
        } while (isContinue);

        return res;
    }

    public static void inorderTraversal(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left, res);
        res.add(root.val);
        inorderTraversal(root.right, res);
    }

}
