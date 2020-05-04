package com.manhpd;

/**
 * Given the root node of a binary search tree (BST) and a value.
 * You need to find the node in the BST that the node's value equals the given value.
 * Return the subtree rooted with that node. If such node doesn't exist, you should return NULL.
 *
 * Given the tree:
 *         4
 *        / \
 *       2   7
 *      / \
 *     1   3
 *
 * And the value to search: 2
 *
 * Return subtree:
 *       2
 *      / \
 *     1   3
 *
 */
public class SearchInBST {

    public static void main(String[] args) {
        TreeNode four = new TreeNode(4);
        TreeNode two = new TreeNode(2);
        TreeNode seven = new TreeNode(7);
        TreeNode one = new TreeNode(1);
        TreeNode three = new TreeNode(3);

        four.left = two;
        four.right = seven;

        two.left = one;
        two.right = three;

        int k = 2;
        TreeNode subTree = searchBST(four, k);
        System.out.println(subTree.val);
    }

    public static TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }

        if (root.val < val) {
            return searchBST(root.right, val);
        } else {
            return searchBST(root.left, val);
        }
    }

    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }



}
