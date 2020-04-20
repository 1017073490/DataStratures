package com.zhangxing.datastratures.tree.binarysorttree;

/**
 * @author zhangxing
 * @Description: 二叉排序树的代码实现
 * @date 2020/4/15 9:15
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        CreateBinarySortTree binarySortTree = new CreateBinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        System.out.println("中序：");
        binarySortTree.infixOrder();
        System.out.println("============");
        System.out.println("删除有2颗子树的结点~");
        binarySortTree.deleteNode(10);
        binarySortTree.infixOrder();
        System.out.println("============");
    }
}


class CreateBinarySortTree {
    private Node root;

    /**
     * 要删除以 node 为根结点的二叉排序树的最小结点
     *
     * @param node
     * @return 返回以 node 为根结点的二叉排序树的最小结点的值
     */
    public int deleteRightTreeMin(Node node) {
        Node target = node;
        //循环查找左子树，找最小值
        while (target.leftNode != null) {
            target = target.leftNode;
        }
        deleteNode(target.value);
        return target.value;
    }

    public void deleteNode(int value) {
        if (root == null) {
            return;
        } else {
            //先找目标结点和其父结点
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            //如果这个二叉树只有一个结点
            if (root.leftNode == null && root.rightNode == null) {
                root = null;
                return;
            }
            Node parent = searchParent(value);
            //叶子结点
            if (targetNode.leftNode == null && targetNode.rightNode == null) {
                //判断targetNode是父结点的左/右子结点
                if (parent.leftNode != null && parent.leftNode.value == value) {
                    parent.leftNode = null;
                } else if (parent.rightNode != null && parent.rightNode.value == value) {
                    parent.rightNode = null;
                }

            } else if (targetNode.leftNode != null && targetNode.rightNode != null) {
                int minVal = deleteRightTreeMin(targetNode.rightNode);
                targetNode.value = minVal;
            } else {
                //只有一颗子树的结点
                //如果要删除的结点有左子结点
                if (targetNode.leftNode != null) {
                    if (parent != null) {
                        //考虑父结点有无可能为空
                        //如果targetNode是parent的左结点
                        if (parent.leftNode.value == value) {
                            parent.leftNode = targetNode.leftNode;
                        } else {
                            parent.rightNode = targetNode.leftNode;
                        }
                    } else {
                        root = targetNode.leftNode;
                    }
                } else {
                    if (parent != null) {
                        //否则要删除的结点有右子结点
                        //如果targetNode是parent的右子结点
                        if (parent.leftNode.value == value) {
                            parent.leftNode = targetNode.rightNode;
                        } else {
                            parent.rightNode = targetNode.rightNode;
                        }
                    } else {
                        root = targetNode.rightNode;
                    }
                }
            }

        }
    }

    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.addNode(node);
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("~~~");
        }
    }
}


class Node {
    int value;
    Node leftNode;
    Node rightNode;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public void addNode(Node node) {
        if (node == null) {
            return;
        }
        //判断结点与当前子树根结点的值的大小关系
        if (node.value < this.value) {
            if (this.leftNode == null) {
                this.leftNode = node;
            } else {
                //递归的左添加
                this.leftNode.addNode(node);
            }
        } else {
            if (this.rightNode == null) {
                this.rightNode = node;
            } else {
                //递归右添加
                this.rightNode.addNode(node);
            }
        }
    }

    public void infixOrder() {
        if (this.leftNode != null) {
            this.leftNode.infixOrder();
        }
        System.out.println(this);
        if (this.rightNode != null) {
            this.rightNode.infixOrder();
        }
    }

    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            //左递归
            if (this.leftNode == null) {
                return null;
            }
            return this.leftNode.search(value);
        } else {
            //右递归
            if (this.rightNode == null) {
                return null;
            }
            return this.rightNode.search(value);
        }
    }

    public Node searchParent(int value) {
        //返回要删除结点的父结点
        if ((this.leftNode != null && this.leftNode.value == value) ||
                (this.rightNode != null && this.rightNode.value == value)) {
            return this;
        } else {
            if (value < this.value && this.leftNode != null) {
                //查找的值小于当前结点的值，且当前结点的左子节点不为空-->左递归
                return this.leftNode.searchParent(value);
            } else if (value >= this.value && this.rightNode != null) {
                //同理-->右递归
                return this.rightNode.searchParent(value);
            } else {
                //无父结点
                return null;
            }
        }
    }


}
