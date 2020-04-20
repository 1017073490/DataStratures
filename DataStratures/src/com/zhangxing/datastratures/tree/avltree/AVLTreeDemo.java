package com.zhangxing.datastratures.tree.avltree;

/**
 * @author zhangxing
 * @Description: 实现平衡二叉树
 * @date 2020/4/15 14:50
 * 想想怎么删除
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = {10, 11, 7, 6, 8, 9};
        //int[] arr = {4, 3, 5, 6, 7, 8};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("平衡处理：");
        System.out.println("树的高度：" + avlTree.getRoot().height());
        System.out.println("左子树的高度：" + avlTree.getRoot().leftHeight());
        System.out.println("右子树的高度：" + avlTree.getRoot().rightHeight());
    }
}


class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

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

    public void leftRotate() {
        //创建一个新结点，其值等于当前根结点的值
        Node newNode = new Node(this.value);
        //把新结点的左子树设置为当前结点的左子树
        newNode.leftNode = this.leftNode;
        //把新结点的右子树设置为当前结点的右子树的左子树
        newNode.rightNode = this.rightNode.leftNode;
        //把当前结点的值替换成右子结点的值
        this.value = this.rightNode.value;
        //把当前结点的右子树设置成右子树的右子树
        this.rightNode = this.rightNode.rightNode;
        //把当前结点的左子树设置成新结点
        this.leftNode = newNode;
    }

    public void rightRotate() {
        Node newNode = new Node(value);
        newNode.rightNode = this.rightNode;
        newNode.leftNode = this.leftNode.rightNode;
        this.value = this.leftNode.value;
        //存在问题。如果leftNode.leftNode不存在怎么办
        this.leftNode = this.leftNode.leftNode;
        this.rightNode = newNode;
    }

    public int leftHeight() {
        if (leftNode == null) {
            return 0;
        }
        return leftNode.height();
    }

    public int rightHeight() {
        if (rightNode == null) {
            return 0;
        }
        return rightNode.height();
    }

    public int height() {
        //返回当前结点的高度，以该结点为根结点的树的高度
        return Math.max(leftNode == null ? 0 : leftNode.height(),
                rightNode == null ? 0 : rightNode.height()) + 1;
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
                this.leftNode.addNode(node);
            }
        } else {
            if (this.rightNode == null) {
                this.rightNode = node;
            } else {
                this.rightNode.addNode(node);
            }
        }
        //如果（右子树的高度-左子树的高度） > 1
        if ((this.rightHeight() - this.leftHeight()) > 1) {
            //增加一个判断：如果 它的右子树的左子树 大于 它的右子树的右子树 的高度。要先进行右旋
            if (this.rightNode != null &&
                    this.rightNode.leftHeight() > this.rightNode.rightHeight()) {
                //先对当前结点的右子树进行右旋
                this.rightNode.rightRotate();
                //再对当前结点左旋
                this.leftRotate();
            }else {
                //直接左旋
                this.leftRotate();
            }
            return;
        }
        if ((this.leftHeight() - this.rightHeight()) > 1) {
            //增加一个判断：如果 它的左子树的右子树 大于 它的左子树的左子树 的高度。要先进行左旋
            if (this.leftNode != null &&
                    this.leftNode.rightHeight() > this.leftNode.leftHeight()) {
                //先对当前结点的左子树进行左旋
                this.leftNode.leftRotate();
                //再对当前结点右旋
                this.rightRotate();
            } else {
                //直接右旋
                this.rightRotate();
            }
            return;
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

