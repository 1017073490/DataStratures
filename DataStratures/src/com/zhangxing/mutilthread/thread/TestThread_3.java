package com.zhangxing.mutilthread.thread;

/**
 * @author zhangxing
 * @Description: Runnable实现创建线程
 * 重写run方法，执行线程需要丢入runnable接口实现类，调用start方法
 * @date 2020/4/21 14:42
 */
public class TestThread_3 implements Runnable {
    public static void main(String[] args) {
        //创建runnable接口实现类对象
        TestThread_3 testThread_3 = new TestThread_3();
        //创建线程对象，通过线程对象来开启我们的线程，代理
        Thread thread = new Thread(testThread_3);
        thread.start();
        for (int i = 0; i < 200; i++) {
            System.out.println("学习线程。。。" + i);
        }
    }
    @Override
    public void run() {
        //线程体
        for (int i = 0; i < 200; i++) {
            System.out.println("我在。。。"+ i);
        }
    }
}
