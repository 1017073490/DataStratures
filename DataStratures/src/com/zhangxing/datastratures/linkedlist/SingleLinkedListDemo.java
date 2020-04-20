package com.zhangxing.datastratures.linkedlist;

import java.util.Stack;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-1 11:06
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero5 = new HeroNode(5, "宋江", "及时雨");
        HeroNode hero7 = new HeroNode(7, "卢俊义", "玉麒麟");
        HeroNode hero8 = new HeroNode(8, "吴用", "智多星");
        HeroNode hero9 = new HeroNode(9, "吴用", "智多星");
        HeroNode hero10 = new HeroNode(10, "吴用", "智多星");
        HeroNode hero20 = new HeroNode(20, "吴用", "智多星");
        HeroNode hero30 = new HeroNode(30, "吴用", "智多星");
        HeroNode hero40 = new HeroNode(40, "吴用", "智多星");

        //创建一个链表
        SingleLinkedList list = new SingleLinkedList();
        SingleLinkedList list2 = new SingleLinkedList();
        list.addByOrder(hero1);
        list.addByOrder(hero7);
        list.addByOrder(hero3);
        list.addByOrder(hero9);

        list2.addByOrder(hero5);
        list2.addByOrder(hero8);
        list2.addByOrder(hero10);


//        System.out.println("合并后的链表：");
//        System.out.println(mergeLists(list.getHeadNode(), list2.getHeadNode()));


//        System.out.println("逆序打印:");
//        reversePrint(list.getHeadNode());
//
//        System.out.println("有效节点的个数为：" + getLength(list.getHeadNode()));
//        list.showList();
//        System.out.println("输出倒数第4个的result:" + getIndexNode(list.getHeadNode(), 4));
//
//        reverseList(list.getHeadNode());
//        System.out.println("反转后的链表:");
//        list.showList();
//
//        HeroNode newHero = new HeroNode(2, "小鹿", "露娜");
//        list.updateNode(newHero);
//        System.out.println("修改后的list。。。");
//        list.showList();
//
//
//        list.deleteNode(1);
//        list.deleteNode(4);
//        list.deleteNode(3);
//        list.deleteNode(2);
//        System.out.println("删除后的list。。。");
//        list.showList();
    }

    /**
     * 方法：获取到单链表节点的个数（不统计头结点）
     *
     * @param headNode 链表的头结点
     * @return 有效节点的个数
     */
    public static int getLength(HeroNode headNode) {
        if (headNode.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode temp = headNode.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 接受head节点，同时接受指定的Index。
     * 先把链表从头到尾遍历得到有效节点个数。根据getLength。
     * 然后从头开始遍历length-index个就可以得到
     * 找到则返回节点，否则为空
     *
     * @param headNode
     * @return
     */
    public static HeroNode getIndexNode(HeroNode headNode, int index) {
        if (headNode.next == null) {
            return null;
        }
        //第一次遍历得到链表的长度
        int length = getLength(headNode);
        //第二次遍历
        //先做一个index的校验。
        if (index <= 0 || index > length) {
            System.out.println("数据有误");
            return null;
        }
        HeroNode temp = headNode;
        for (int i = 0; i < (length - index) +1 ; i++) {
            temp = temp.next;
        }
        return temp;


    }

    /**
     * 思路：
     * 先定义一个节点reverseHead=new HeroNode()
     * 从头到尾遍历原来的节点，每遍历出一个，就将其取出，并放在新的链表reverseHead的最前端
     * 原来的head.next = reverseHead.next
     */
    public static void reverseList(HeroNode headNode) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (headNode.next == null || headNode.next.next == null) {
            return;
        }
        //定义辅助指针帮助我们遍历原来的链表
        HeroNode temp = headNode.next;
        //指向当前temp节点的下一个节点
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0, "", "");
        //开始遍历,每遍历出一个，就将其取出，并放在新的链表reverseHead的最前端
        while (temp != null) {
            //暂时保存当前节点的下一个节点
            next = temp.next;
            //将temp的next指向reverseHead的next
            temp.next = reverseHead.next;
            //reverseHead的next指向该节点，这样形成一个链
            reverseHead.next = temp;
            //让原来的temp后移
            temp = next;
        }
        //将原来headNode的next指向新的reverseHead的next,这样就实现原来链表的反转
        headNode.next = reverseHead.next;


    }

    /**
     * 利用Stack这个数据结构，将各个节点压入Stack中，再利用其特点实现逆序打印
     */
    public static void reversePrint(HeroNode headNode) {
        if (headNode.next == null) {
            return;
        }
        //创建一个Stack
        Stack<HeroNode> nodeStack = new Stack<HeroNode>();
        HeroNode temp = headNode.next;
        //开始循环遍历并压入
        while (temp != null) {
            nodeStack.push(temp);
            temp = temp.next;
        }
        //遍历Stack中的节点
        while (nodeStack.size() > 0) {
            System.out.println(nodeStack.pop());
        }
    }

    /**
     * 课后作业。将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     */
    public static HeroNode mergeLists(HeroNode headNode1, HeroNode headNode2) {
        HeroNode temp1 = headNode1.next;
        HeroNode temp2 = headNode2.next;
        HeroNode dummy = new HeroNode(0, "", "");
        HeroNode lastTemp = dummy;
        while (!(temp1 == null || temp2 == null)) {
            if (temp1.id <= temp2.id) {
                lastTemp.next = temp1;
                temp1 = temp1.next;
            } else {
                lastTemp.next = temp2;
                temp2 = temp2.next;
            }
            lastTemp = lastTemp.next;
        }
        lastTemp.next = temp1 == null ? temp2 : temp1;
        return dummy;
    }


}

class HeroNode {
    public int id;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
//                ", name='" + name + '\'' +
//                ", nickName='" + nickName + '\'' +
                "," + next +
                '}';
    }

    /**
     * 创建一个单链表管理人物
     */
}

class SingleLinkedList {
    /**
     * 先初始化一个不可移动的头节点
     */
    private HeroNode headNode = new HeroNode(0, "", "");

    public HeroNode getHeadNode() {
        return headNode;
    }

    public void addNode(HeroNode heroNode) {
        //当不考虑编号顺序时，找到当前链表的最后节点。并指向新节点
        //引入辅助变量temp。并遍历链表
        HeroNode temp = headNode;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        //退出while循环时，temp就指向了最后节点
        temp.next = heroNode;
    }

    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = headNode;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.id > heroNode.id) {
                //此时已经找到添加的位置
                break;
            } else if (temp.next.id == heroNode.id) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag的值
        if (flag) {
            System.out.println("编号" + heroNode.id + "已经存在。。。");
        } else {
            //插入到temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    public void updateNode(HeroNode heroNode) {
        //判断是否为空
        if (headNode.next == null) {
            System.out.println("链表为空。。。");
        }
        //先定义一个辅助变量
        HeroNode temp = headNode.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.id == heroNode.id) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到
        if (flag) {
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        } else {
            System.out.println("没有找到编号为的" + heroNode.id + "节点");
        }
    }

    public void deleteNode(int id) {
        if (headNode.next == null) {
            System.out.println("链表已空。。。");
        }
        HeroNode temp = headNode;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                System.out.println("没有该id。。。");
                break;
            }
            if (temp.next.id == id) {
                //找到了
                flag = true;
                break;
            }
            //后移遍历
            temp = temp.next;
        }
        if (flag == true) {
            //删除
            temp.next = temp.next.next;
        }


    }

    public void showList() {
        //先判断是否为空
        if (headNode.next == null) {
            System.out.println("链表为空");
            return;
        }
        //引入辅助变量temp。并遍历链表
        HeroNode temp = headNode.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }

    }

}
