package com.manhpd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * Example:
 *
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [1,2,3]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class PreorderBinaryTree {

    public static void main(String[] args) {
        TreeNode first = new TreeNode(1);
        TreeNode second = new TreeNode(2);
        TreeNode third = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);

        first.left = second;
        first.right = third;

        second.left = four;
        second.right = five;

        third.left = six;
        third.right = seven;

        // Recursive version
//        List<Integer> res = new ArrayList<>();
//        preOrderTraversalRecursive(first, res);

        // Iterative version
        List<Integer> res = preorderTraversal(first);

        res.stream().map(item -> item + " --> ").forEach(item -> System.out.print(item));
        System.out.println("null");
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stk = new Stack<>();
        stk.push(root);

        while (stk.size() > 0) {
            TreeNode tmp = stk.pop();
            res.add(tmp.val);

            if (tmp.right != null) {
                stk.push(tmp.right);
            }

            if (tmp.left != null) {
                stk.push(tmp.left);
            }
        }

        return res;
    }

    public static void preOrderTraversalRecursive(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }

        res.add(root.val);
        preOrderTraversalRecursive(root.left, res);
        preOrderTraversalRecursive(root.right, res);
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
        // nothing to do
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
