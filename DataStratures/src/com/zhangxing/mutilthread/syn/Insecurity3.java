package com.zhangxing.mutilthread.syn;

import java.util.ArrayList;

/**
 * @author zhangxing
 * @Description: 线程不安全的集合
 * @date 2020/4/22 19:13
 * synchronized (Obj){}
 * Obj称为同步监视器
 * obj可以是任何对象，但是推荐使用共享资源作为同步监视器
 * 同步方法中无需指定同步监视器，因为同步方法的同步监视器就是this,就是对象本身
 * 执行过程：
 * 第一个线程访问，锁定同步监视器，执行其中代码，
 * 第二个线程访问，发现同步监视器被锁定，无法访问。
 * 第一个线程访问完毕，解锁同步监视器，
 * 第二个线程访问，发现同步监视器没有被锁，此时第二个线程锁定同步监视器并访问。
 * 所以要锁好这个obj对象
 */
public class Insecurity3 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                synchronized (list){
                    list.add(Thread.currentThread().getName());
                }

            }).start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }
}
