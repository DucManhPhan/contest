package com.manhpd;

import com.manhpd.tree.TreeFactory;
import com.manhpd.tree.TreeNode;

/**
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 *
 * Note:
 * Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest to the target.
 * Example:
 *
 * Input: root = [4,2,5,1,3], target = 3.714286
 *
 *     4
 *    / \
 *   2   5
 *  / \
 * 1   3
 *
 * Output: 4
 */
public class ClosetBinarySearchTreeValue {

    public static void main(String[] args) {
        TreeNode root = TreeFactory.init();
        double target = 3.714286;
        int res = closetValue(root, target);
    }

    public static int closetValue(TreeNode root, double target) {
        TreeNode tmp = root;
        return -1;
    }

}
