package com.manhpd;

import java.util.Collections;
import java.util.List;

public class LevelOrderTraversal {

    private static int maxHeight = Integer.MIN_VALUE;

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
        List<Integer> res = levelOrderTraversal(first);

//        res.stream().map(item -> item + " --> ").forEach(item -> System.out.print(item));
//        System.out.println("null");
    }

    /**
     * Using recursive version
     *
     * @param root
     */
    public static List<Integer> levelOrderTraversal(TreeNode root) {
//        int height = getMaxLevelBottomUp(root);
        getMaxLevelTopDown(root, 1);
        int height = maxHeight;
        System.out.println(height);

        return Collections.emptyList();
    }

    private static void getMaxLevelTopDown(TreeNode root, int level) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            maxHeight = Math.max(maxHeight, level);
        }

        getMaxLevelTopDown(root.left, level + 1);
        getMaxLevelTopDown(root.right, level + 1);
    }

    private static int getMaxLevelBottomUp(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getMaxLevelBottomUp(root.left);
        int rightHeight = getMaxLevelBottomUp(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

}
