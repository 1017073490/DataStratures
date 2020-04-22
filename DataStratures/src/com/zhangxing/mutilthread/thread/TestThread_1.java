package com.zhangxing.mutilthread.thread;

/**
 * @author zhangxing
 * @Description: 学习创建线程
 * @date 2020/4/21 14:05
 */
public class TestThread_1 extends Thread{
    public static void main(String[] args) {
        //创建线程对象
        TestThread_1 thread_1 = new TestThread_1();
        //调用start()
        thread_1.start();

        for (int i = 0; i < 200; i++) {
            System.out.println("学习线程。。。" + i);
        }
    }
    @Override
    public void run() {
        //线程体
        int k = 200;
        for (int i = 0; i < k; i++) {
            System.out.println("我在。。。"+ i);
        }
    }
}
