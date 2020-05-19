package com.manhpd;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * Example:
 *
 * Input: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * Output: [3,2,1]
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 *
 */
public class PostorderBinaryTree {

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
//        postorderTraversalRecursive(first, res);

        // Iterative version
        List<Integer> res = postorderTraversal(first);

        res.stream().map(item -> item + " --> ").forEach(item -> System.out.print(item));
        System.out.println("null");
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNodeState> stk = new Stack<>();
        stk.push(new TreeNodeState(null, false));   // mark the end of stack

        TreeNode tmp = root;
        boolean isRightNode = true;

        do {
            while (tmp != null && isRightNode) {
                TreeNodeState stateRoot = new TreeNodeState(tmp, false);
                stk.push(stateRoot);

                if (tmp.right != null) {
                    TreeNodeState stateRight = new TreeNodeState(tmp.right, true);
                    stk.push(stateRight);
                }

                tmp = tmp.left;
            }

            if (tmp != null) {
                res.add(tmp.val);
            }

            TreeNodeState poppedNode = stk.pop();
            tmp = poppedNode.node;
            isRightNode = poppedNode.isRightNode;
        } while (tmp != null);

        return res;
    }

    public static void postorderTraversalRecursive(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }

        postorderTraversalRecursive(root.left, res);
        postorderTraversalRecursive(root.right, res);
        res.add(root.val);
    }

}

class TreeNodeState {
    TreeNode node;
    boolean isRightNode;

    TreeNodeState(TreeNode node, boolean isRightNode) {
        this.node = node;
        this.isRightNode = isRightNode;
    }
}