package com.zhangxing.mutilthread.thread;

/**
 * @author zhangxing
 * @Description: 尝试实现购买火车票
 * @date 2020/4/21 14:54
 * 多个线程操作统一资源，线程不安全，数据紊乱
 */
public class TestThread_4 implements Runnable {
    private int ticketCount = 10;

    @Override
    public void run() {
        while (true) {
            if (ticketCount <= 0) {
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "买到了" + ticketCount--);
        }
    }

    public static void main(String[] args) {
        TestThread_4 testThread_4 = new TestThread_4();
        new Thread(testThread_4, "zx").start();
        new Thread(testThread_4, "gy").start();
        new Thread(testThread_4, "黄牛").start();
    }
}
