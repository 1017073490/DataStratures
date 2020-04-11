package com.zhangxing.queue;

import java.util.Scanner;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-3-31 19:58
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //创建队列
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show) 显示队列");
            System.out.println("e(exit) 退出程序");
            System.out.println("a(add) 添加数据");
            System.out.println("g(get) 取出数据");
            System.out.println("h(head) 查看对头");
            System.out.println("请选择操作：");
            key = scanner.next().charAt(0);
            switch (key){
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
                        System.out.printf("取出的是%d",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("取出的队头是%d",res);
                    }catch (Exception e){
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

class ArrayQueue{
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    /**
     * 创建队列
     * maxArraySize:传入的队列大小
     */
    public ArrayQueue(int maxArraySize){
        maxSize = maxArraySize;
        front = -1;
        rear = -1;
        arr = new int[maxSize];
    }

    /**
     * 判断是否队满
     * @return
     */
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    /**
     * 判断是否队空
     * @return
     */
    public boolean isEmpty(){
        return rear == front;
    }

    /**
     * 添加数据
     * @return
     */
    public void addQueue(int n){
        //先判断是否队满
        if (isFull()){
            System.out.println("队列已满。。。");
            return;
        }
        //进行操作
        rear++;
        arr[rear] = n;
    }

    /**
     * 数据出队
     * @return
     */
    public int getQueue(){
        //先判读是否空
        if (isEmpty()){
            throw new RuntimeException("队列已空。。。");
        }
        //
        front++;
        return arr[front];
    }

    /**
     * 显示队列
     */
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列已空。。。");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n",i,arr[i]);
        }
    }

    /**
     * 显示队头
     */
    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列已空。。。");
        }
        return arr[front + 1];
    }
}
