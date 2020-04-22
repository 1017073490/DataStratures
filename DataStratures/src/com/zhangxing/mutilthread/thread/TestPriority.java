package com.zhangxing.mutilthread.thread;

/**
 * @author zhangxing
 * @Description: 线程的优先级
 * @date 2020/4/22 11:03
 */
public class TestPriority {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+ Thread.currentThread().getPriority());
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()
                    + "-->" + Thread.currentThread().getPriority());
        });
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }
}
