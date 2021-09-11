package com.zhangxing.datastratures.ds.questions;

/**
 * @Author zhangxing
 * @Date 2021/9/5 12:16
 * @Version 1.0
 * @Description
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
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
