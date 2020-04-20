package com.zhangxing.datastratures.queue;

import java.util.Scanner;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-1 9:57
 */
public class CircleArrayQueue {
    public static void main(String[] args) {
        CircleArray queue = new CircleArray(4);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show) 显示队列");
            System.out.println("e(exit) 退出程序");
            System.out.println("a(add) 添加数据");
            System.out.println("g(get) 取出数据");
            System.out.println("h(head) 查看对头");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数字：");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的是%d", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("取出的队头是%d", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出。。。");
    }
}

class CircleArray {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public CircleArray(int maxArraySize) {
        maxSize = maxArraySize;
        front = 0;
        rear = 0;
        arr = new int[maxSize];
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        //先判断是否队满
        if (isFull()) {
            System.out.println("队列已满。。。");
            return;
        }
        //进行操作
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        //先判读是否空
        if (isEmpty()) {
            throw new RuntimeException("队列已空。。。");
        }
        //进行操作
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列已空。。。");
        }
        //从front开始遍历，遍历有效个元素
        for (int i = front; i < front + getEfficient(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    public int getEfficient() {
        return (rear + maxSize - front) % maxSize;
    }

    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列已空。。。");
        }
        return arr[front];
    }

}
