package com.zhangxing.datastratures.tree.threadedbinarytree;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-10 20:25
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        HeroNode root = new HeroNode(1, "zx");
        HeroNode node2 = new HeroNode(3, "gy");
        HeroNode node3 = new HeroNode(6, "zf");
        HeroNode node4 = new HeroNode(8, "ly");
        HeroNode node5 = new HeroNode(10, "rc");
        HeroNode node6 = new HeroNode(14, "ff");
        root.setLeftNode(node2);
        root.setRightNode(node3);
        node2.setLeftNode(node4);
        node2.setRightNode(node5);
        node3.setLeftNode(node6);
        //线索化
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.preThreadedNodes();
        System.out.println("线索化遍历线索化二叉树：");
        threadedBinaryTree.preThreadedShowList();

    }
}


class ThreadedBinaryTree {
    private HeroNode root;
    /**
     * pre保留住前一个结点，方便指向
     */
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }


    /**
     * 中序线索化二叉树与重载
     */
    public void infixThreadedNodes() {
        this.infixThreadedNodes(root);
    }

    public void infixThreadedNodes(HeroNode node) {
        if (node == null) {
            return;
        }
        //递归线索化左子树
        infixThreadedNodes(node.getLeftNode());
        //递归线索化当前节点
        //处理当前结点的前驱
        if (node.getLeftNode() == null) {
            //让当前结点的左指针指向前驱结点
            node.setLeftNode(pre);
            //修改当前结点的左指针类型
            node.setLeftType(1);
        }
        //处理当前结点的后续
        if (pre != null && pre.getRightNode() == null) {
            //让前驱结点的右指针指向当前结点
            pre.setRightNode(node);
            //修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        //
        pre = node;
        //递归线索化右子树
        infixThreadedNodes(node.getRightNode());
    }


    /**
     * 先序线索化二叉树与重载
     */
    public void preThreadedNodes() {
        this.preThreadedNodes(root);
    }

    public void preThreadedNodes(HeroNode node) {
        //node就是需要线索化的结点
        if (node == null) {
            return;
        }
        //递归线索化当前节点
        //处理当前结点的前驱
        if (node.getLeftNode() == null) {
            node.setLeftNode(pre);
            node.setLeftType(1);
        }
        //处理当前结点的后续
        if (pre != null && pre.getRightNode() == null) {
            pre.setRightNode(node);
            pre.setRightType(1);
        }
        pre = node;

        //递归线索化左子树
        if (node.getLeftType() == 0){
            //这个判断很关键，不然会死循环。确实，也只有有左右子树才应该继续递归，如果是前驱或后继就不进入。
            preThreadedNodes(node.getLeftNode());
        }
        //递归线索化右子树
        if (node.getRightType() == 0){
            preThreadedNodes(node.getRightNode());
        }

    }



    public void infixThreadedShowList() {
        //定义一个变量，存储当前遍历的结点。从root开始。
        HeroNode node = root;
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeftNode();
            }
            System.out.println(node);
            while (node.getRightType() == 1) {
                node = node.getRightNode();
                System.out.println(node);
            }
            node = node.getRightNode();
        }
        System.out.println();
    }

    public void preThreadedShowList() {
        //定义一个变量，存储当前遍历的结点。从root开始。
        HeroNode node = root;
        while (node != null) {
            //循环找到leftType == 1 的结点。
            while (node.getLeftType() == 0) {
                System.out.println(node);
                node = node.getLeftNode();
            }
            //找到，输出
            System.out.println(node);
            //如果当前结点的右指针指向的是后继结点，就一直输出
            while (node.getRightType() == 1) {
                node = node.getRightNode();
                System.out.println(node);

            }
            node = node.getRightNode();
        }
    }



}


class HeroNode {
    private int id;
    private String name;
    private HeroNode leftNode;
    private HeroNode rightNode;

    /**
     * leftType == 0 表示指向的是左子树，== 1表示前驱结点。
     * rightType == 0 表示指向的是右子树，== 1表示后继结点。
     */
    private int leftType;
    private int rightType;

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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
        System.out.println("进入前序。。。");
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
        System.out.println("进入中序。。。");
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
        System.out.println("进入后序。。。");
        if (this.id == id) {
            return this;
        }
        return resNode;
    }
}

