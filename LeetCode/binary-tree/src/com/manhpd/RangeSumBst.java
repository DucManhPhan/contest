package com.manhpd;

public class RangeSumBst {

    public static void main(String[] args) {
        TreeNode first = new TreeNode(10);
        TreeNode second = new TreeNode(5);
        TreeNode third = new TreeNode(15);
        TreeNode four = new TreeNode(3);
        TreeNode five = new TreeNode(7);
        TreeNode six = new TreeNode(18);

        first.left = second;
        first.right = third;

        second.left = four;
        second.right = five;

        third.right = six;

        int low = 7;
        int right = 15;

        RangeSumBst solution = new RangeSumBst();
        System.out.println(solution.rangeSumBST(first, low, right));
    }

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }

        int tmp = 0;
        if (root.val >= low && root.val <= high) {
            tmp = root.val;
        }

        return tmp + this.rangeSumBST(root.left, low, high) + this.rangeSumBST(root.right, low, high);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
