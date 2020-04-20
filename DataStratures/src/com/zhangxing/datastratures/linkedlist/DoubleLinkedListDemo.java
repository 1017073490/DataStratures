package com.zhangxing.datastratures.linkedlist;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-2 19:17
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleHeroNode hero1 = new DoubleHeroNode(1, "宋江", "及时雨");
        DoubleHeroNode hero3 = new DoubleHeroNode(3, "吴用", "智多星");
        DoubleHeroNode hero5 = new DoubleHeroNode(5, "宋江", "及时雨");
        DoubleHeroNode hero7 = new DoubleHeroNode(7, "卢俊义", "玉麒麟");
        DoubleHeroNode hero10 = new DoubleHeroNode(10, "卢俊义", "玉麒麟");
        DoubleHeroNode hero11 = new DoubleHeroNode(11, "卢俊义", "玉麒麟");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addByOrder(hero10);
        doubleLinkedList.addByOrder(hero11);

        doubleLinkedList.addByOrder(hero5);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero7);

        doubleLinkedList.addByOrder(hero3);

        doubleLinkedList.showList();

        System.out.println("修改后的list。。。");
        DoubleHeroNode newHero = new DoubleHeroNode(5, "公孙胜", "入云龙");
        doubleLinkedList.updateNode(newHero);
        doubleLinkedList.showList();

        System.out.println("删除后的list。。。");
        doubleLinkedList.deleteNode(3);
        doubleLinkedList.showList();


    }
}

/**
 *
 */
class DoubleLinkedList {
    private DoubleHeroNode doubleHeadNode = new DoubleHeroNode(0, "", "");

    public DoubleHeroNode getDoubleHeadNode() {
        return doubleHeadNode;
    }

    public void showList() {
        //先判断是否为空
        if (doubleHeadNode.next == null) {
            System.out.println("链表为空");
            return;
        }
        //引入辅助变量temp。并遍历链表
        DoubleHeroNode temp = doubleHeadNode.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }

    }

    public void addNode(DoubleHeroNode doubleHeroNode) {
        //当不考虑编号顺序时，找到当前链表的最后节点。并指向新节点
        //引入辅助变量temp。并遍历链表
        DoubleHeroNode temp = doubleHeadNode;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        //退出while循环时
        temp.next = doubleHeroNode;
        doubleHeroNode.pre = temp;
    }

    public void addByOrder(DoubleHeroNode doubleHeroNode) {
        DoubleHeroNode temp = doubleHeadNode;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.id > doubleHeroNode.id) {
                //此时已经找到添加的位置
                break;
            } else if (temp.next.id == doubleHeroNode.id) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag的值
        if (flag) {
            System.out.println("编号" + doubleHeroNode.id + "已经存在。。。");
        } else {
            //插入到temp的后面
            if (temp.next == null) {
                temp.next = doubleHeroNode;
                doubleHeroNode.pre = temp;
            } else {
                temp.next.pre = doubleHeroNode;
                doubleHeroNode.pre = temp;
                doubleHeroNode.next = temp.next;
                temp.next = doubleHeroNode;

            }


        }
    }

    public void updateNode(DoubleHeroNode doubleHeroNode) {
        //判断是否为空
        if (doubleHeadNode.next == null) {
            System.out.println("链表为空。。。");
        }
        //先定义一个辅助变量
        DoubleHeroNode temp = doubleHeadNode.next;
        boolean flag = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.id == doubleHeroNode.id) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到
        if (flag) {
            temp.name = doubleHeroNode.name;
            temp.nickName = doubleHeroNode.nickName;
        } else {
            System.out.println("没有找到编号为的" + doubleHeroNode.id + "节点");
        }
    }

    public void deleteNode(int id) {
        if (doubleHeadNode.next == null) {
            System.out.println("链表已空。。。");
        }
        DoubleHeroNode temp = doubleHeadNode.next;
        while (true) {
            //最后一个节点的next
            if (temp == null) {
                System.out.println("没有该id。。。");
                break;
            }
            if (temp.id == id) {
                temp.pre.next = temp.next;
                if (temp.next != null) {
                    temp.next.pre = temp.pre;
                }
                break;
            }
            temp = temp.next;
        }
    }


}

class DoubleHeroNode {
    public int id;
    public String name;
    public String nickName;
    public DoubleHeroNode next;
    public DoubleHeroNode pre;

    public DoubleHeroNode(int id, String name, String nickName) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "DoubleHeroNode{" +
                "id=" + id + "," + next +
                '}';
    }

/**
 * 创建一个单链表管理人物
 */
}
