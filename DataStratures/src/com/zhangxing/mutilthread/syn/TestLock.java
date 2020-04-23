package com.zhangxing.mutilthread.syn;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangxing
 * @Description: Lock锁
 * @date 2020/4/23 9:19
 */
public class TestLock {
    public static void main(String[] args) {
        TestClock2 testLock2 = new TestClock2();

        new Thread(testLock2).start();
        new Thread(testLock2).start();
        new Thread(testLock2).start();
    }
}


class TestClock2 implements Runnable{
    private int ticketCount = 10;

    //定义lock锁
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true){
            try {
                lock.lock();
                if (ticketCount > 0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ticketCount--);
                }
            }finally {
                lock.unlock();
            }
        }
    }
}