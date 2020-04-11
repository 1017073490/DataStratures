package com.zhangxing.linkedlist;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-4 9:09
 */
public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.addBoy(5);
        list.showBoy();
        list.quitBoy(1, 2, 5);
    }
}

class CircleSingleLinkedList {
    private Boy first = null;

    public void addBoy(int nums) {
        //数据校验
        if (nums < 1) {
            System.out.println("nums的值有误");
            return;
        }
        //辅助boy节点
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            //加入节点。第一个小孩特殊
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    public void showBoy() {
        if (first == null) {
            System.out.println("List is null...");
            return;
        }
        //first不能动，使用辅助指针
        Boy curBoy = first;
        while (true) {
            System.out.println("小孩的编号" + curBoy.getId());
            if (curBoy.getNext() == first) {
                System.out.println("遍历完毕。。。");
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * @param startId  从第几个小孩开始数
     * @param countNum 数几下
     * @param nums     最初有多少小孩
     */
    public void quitBoy(int startId, int countNum, int nums) {
        //先校验
        if (first == null || startId < 1 || startId > nums) {
            System.out.println("参数有误");
            return;
        }
        Boy helper = first;
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        //报数前先移动k-1次，移动到指定的开始的位置
        for (int j = 0; j < startId - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //报数时，同时移动countNum-1次。这是一个循环的操作
        while (true) {
            if (helper == first) {
                //此时只有一个人
                break;
            }
            //移动countNum-1次
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向出圈的小孩
            System.out.println("小孩" + first.getId() + "出圈");
            //这时first出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.println("最后一个" + first.getId());
    }
}

class Boy {
    private int id;
    private Boy next;

    public Boy(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
