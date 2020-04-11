package com.zhangxing.tree;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-10 19:44
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        ArrBinaryTree tree = new ArrBinaryTree(array);
        tree.portOrder();
    }
}


class ArrBinaryTree {
    private int[] array;

    public ArrBinaryTree(int[] array) {
        this.array = array;
    }

    public void preOrder() {
        this.preOrder(0);
    }

    public void infixOrder() {
        this.infixOrder(0);
    }

    public void portOrder() {
        this.portOrder(0);
    }

    public void preOrder(int index) {
        //如果数组为空，或者array.length=0
        if (array == null || array.length == 0) {
            System.out.println();
        }
        //先输出当前元素
        System.out.println(array[index]);
        //左递归遍历
        if ((index * 2 + 1) < array.length) {
            preOrder(index * 2 + 1);
        }
        //右递归遍历
        if ((index * 2 + 2) < array.length) {
            preOrder(index * 2 + 2);
        }
    }

    public void infixOrder(int index) {
        //如果数组为空，或者array.length=0
        if (array == null || array.length == 0) {
            System.out.println();
        }
        //左递归遍历
        if ((index * 2 + 1) < array.length) {
            infixOrder(index * 2 + 1);
        }
        //先输出当前元素
        System.out.println(array[index]);
        //右递归遍历
        if ((index * 2 + 2) < array.length) {
            infixOrder(index * 2 + 2);
        }
    }

    public void portOrder(int index) {
        //如果数组为空，或者array.length=0
        if (array == null || array.length == 0) {
            System.out.println();
        }
        //左递归遍历
        if ((index * 2 + 1) < array.length) {
            portOrder(index * 2 + 1);
        }
        //右递归遍历
        if ((index * 2 + 2) < array.length) {
            portOrder(index * 2 + 2);
        }
        //先输出当前元素
        System.out.println(array[index]);
    }
}
