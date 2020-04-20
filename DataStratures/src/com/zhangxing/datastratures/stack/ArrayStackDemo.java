package com.zhangxing.datastratures.stack;

import java.util.Scanner;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-4 10:48
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        int key = 0;
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("0:显示");
            System.out.println("1:退出");
            System.out.println("2:添加");
            System.out.println("3:删除");
            System.out.println("请选择：");
            key = scanner.nextInt();
            switch (key) {
                case 0:
                    stack.showStack();
                    break;
                case 1:
                    scanner.close();
                    loop = false;
                    break;
                case 2:
                    System.out.println("输入一个数");
                    int nextInt = scanner.nextInt();
                    stack.push(nextInt);
                    break;
                case 3:
                    try {
                        System.out.println(stack.pop());
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                default:
                    break;
            }
        }
    }
}


class ArrayStack {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("stack is full...");
            return;

        }
        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("stack is empty...");
        }
        return stack[top--];
    }

    public void showStack() {
        if (isEmpty()) {
            System.out.println("stack is empty...");
        }
        for (int i = top; i >= 0; i--) {
            System.out.println("第" + i + "个栈：" + stack[i]);
        }
    }
}
