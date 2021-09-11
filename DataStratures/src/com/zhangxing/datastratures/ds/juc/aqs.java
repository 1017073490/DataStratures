package com.zhangxing.datastratures.ds.juc;

/**
 * @Author zhangxing
 * @Date 2021/8/22 15:10
 * @Version 1.0
 * @Description
 */
public class aqs {
    //    synchronized ReentrantLock 都是可重入锁
    // 锁进入，退出
    static Object objectA = new Object();

    public static void m1() {
        new Thread(() -> {
            synchronized (objectA){
                System.out.println(Thread.currentThread().getName()+"外层");
                synchronized (objectA){
                    System.out.println(Thread.currentThread().getName()+"中层");
                    synchronized (objectA){
                        System.out.println(Thread.currentThread().getName()+"内层");
                    }
                }
            }
        },"t1").start();
    }

    public static void main(String[] args) {
        m1();
    }
}
