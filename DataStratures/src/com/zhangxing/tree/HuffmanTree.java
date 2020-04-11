package com.zhangxing.tree;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-11 20:14
 * 赫夫曼树：
 * 从小到大进行排序，每个数据都是一个结点，每个结点可以看出是最简单的二叉树。
 * 取出根结点权值最小的两颗二叉树，组成一颗新的二叉树
 * 该二叉树的根节点的权值是前面两颗二叉树根节点权值的和
 * 再将这颗新的二叉树，以根结点的权值大小再次排序。
 * 重复上述步骤。
 * 新增东西
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        System.out.println("=================");
        preOrderMethod(root);
    }

    public static void preOrderMethod(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空树。。。");
        }
    }

    public static Node createHuffmanTree(int[] arr) {
        //第一步，遍历数组并将每个元素构成一个Node，并放入ArrayList中。
        ArrayList<Node> nodeList = new ArrayList<Node>();
        for (int value : arr) {
            nodeList.add(new Node(value));
        }
        while (nodeList.size() > 1) {
            //进行排序。
            Collections.sort(nodeList);
            System.out.println(nodeList);
            //取出权值最小的二叉树结点
            Node leftNode = nodeList.get(0);
            Node rightNode = nodeList.get(1);
            //构造新的二叉树
            Node parentNode = new Node(leftNode.value + rightNode.value);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            //从nodeList中删除处理过的二叉树
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
            //将parentNode加入nodeList
            nodeList.add(parentNode);
        }
        //返回赫夫曼树的头结点
        return nodeList.get(0);
    }
}

class Node implements Comparable<Node> {
    public Node left;
    public Node right;
    public int value;

    public Node(int value) {
        this.value = value;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(@NotNull Node o) {
        //java基础。这里表示从小到大
        return this.value - o.value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
