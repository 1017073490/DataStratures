package com.zhangxing.mutilthread.thread;

/**
 * @author zhangxing
 * @Description: 测试礼让，但是礼让不一定成功
 * @date 2020/4/22 9:52
 */
public class TestYield {
    public static void main(String[] args) {
        MyYield yield = new MyYield();
        new Thread(yield, "a").start();
        new Thread(yield, "b").start();
    }

}


class MyYield implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "线程开始执行。");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + "线程停止。");
    }
}
