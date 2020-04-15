package com.zhangxing.tree;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-10 10:12
 * 完全二叉树：如果该二叉树的所有叶子结点都在最后一层或者倒数第二层，而且最后一层的叶子结点在左边连续，
 * 倒数第二层的叶子结点在右边连续，我们成为完全二叉树。
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "zx");
        HeroNode node2 = new HeroNode(2, "gy");
        HeroNode node3 = new HeroNode(3, "zf");
        HeroNode node4 = new HeroNode(4, "ly");
        HeroNode node5 = new HeroNode(5, "rc");
        //先手动创建，后面再递归创建
        root.setLeftNode(node2);
        root.setRightNode(node3);
        node2.setRightNode(node5);
        node2.setLeftNode(node4);
        binaryTree.setRoot(root);


        System.out.println("前序：");
        binaryTree.preOrder();
        binaryTree.deleteNode(5);
        System.out.println("删除结点后：");
        binaryTree.preOrder();
        System.out.println("============");
        System.out.println("中序：");
        HeroNode resInfixNode = binaryTree.infixOrderSearch(5);
        System.out.println(resInfixNode);
//        binaryTree.infixOrder();
        System.out.println("============");
        System.out.println("后序：");
        HeroNode resPostNode = binaryTree.postOrderSearch(1);
        System.out.println(resPostNode);
//        binaryTree.postOrder();

    }
}


class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void deleteNode(int id) {
        if (root != null) {
            if (root.getId() == id) {
                root = null;
            } else {
                root.deleteNode(id);
            }
        } else {
            System.out.println("空树。。。");
        }
    }

    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空。。。");
        }
    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空。。。");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空。。。");
        }
    }

    public HeroNode preOrderSearch(int id) {
        if (this.root != null) {
            return root.preOrderSearch(id);
        } else {
            return null;
        }
    }

    public HeroNode infixOrderSearch(int id) {
        if (this.root != null) {
            return root.infixOrderSearch(id);
        } else {
            return null;
        }
    }

    public HeroNode postOrderSearch(int id) {
        if (this.root != null) {
            return root.postOrderSearch(id);
        } else {
            return null;
        }
    }


}


class HeroNode {
    private int id;
    private String name;
    private HeroNode leftNode;
    private HeroNode rightNode;

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(HeroNode leftNode) {
        this.leftNode = leftNode;
    }

    public HeroNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(HeroNode rightNode) {
        this.rightNode = rightNode;
    }

    /**
     * 递归删除结点
     */
    public void deleteNode(int id) {
        if (this.leftNode != null && this.leftNode.id == id) {
            this.leftNode = null;
            return;
        }
        if (this.rightNode != null && this.rightNode.id == id) {
            this.rightNode = null;
            return;
        }
        if (this.leftNode != null) {
            this.leftNode.deleteNode(id);
        }
        if (this.rightNode != null) {
            this.rightNode.deleteNode(id);
        }
    }

    /**
     * 前序、中序、后序遍历方法
     */

    public void preOrder() {
        System.out.println(this);
        //递归左子树前序遍历
        if (this.leftNode != null) {
            this.leftNode.preOrder();
        }
        //递归右子树
        if (this.rightNode != null) {
            this.rightNode.preOrder();
        }
    }

    public void infixOrder() {
        //递归左子树中序遍历
        if (this.leftNode != null) {
            this.leftNode.infixOrder();
        }
        System.out.println(this);
        //递归右子树
        if (this.rightNode != null) {
            this.rightNode.infixOrder();
        }
    }

    public void postOrder() {
        //递归左子树后序遍历
        if (this.leftNode != null) {
            this.leftNode.postOrder();
        }
        //递归右子树
        if (this.rightNode != null) {
            this.rightNode.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 前序、中序、后序查找方法
     */

    public HeroNode preOrderSearch(int id) {
        System.out.println("进入前序");
        if (this.id == id) {
            return this;
        }
        HeroNode resNode = null;
        if (this.leftNode != null) {
            resNode = this.leftNode.preOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.rightNode != null) {
            resNode = this.rightNode.preOrderSearch(id);
        }
        return resNode;
    }

    public HeroNode infixOrderSearch(int id) {
        HeroNode resNode = null;
        if (this.leftNode != null) {
            resNode = this.leftNode.infixOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序");
        if (this.id == id) {
            return this;
        }
        if (this.rightNode != null) {
            resNode = this.rightNode.infixOrderSearch(id);
        }
        return resNode;
    }

    public HeroNode postOrderSearch(int id) {
        HeroNode resNode = null;
        if (this.leftNode != null) {
            resNode = this.leftNode.postOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.rightNode != null) {
            resNode = this.rightNode.postOrderSearch(id);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入后序");
        if (this.id == id) {
            return this;
        }
        return resNode;
    }
}
