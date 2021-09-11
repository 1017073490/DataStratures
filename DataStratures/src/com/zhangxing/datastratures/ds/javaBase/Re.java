package com.zhangxing.datastratures.ds.javaBase;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author zhangxing
 * @Date 2021/9/6 21:47
 * @Version 1.0
 * @Description
 */
class Phone implements Runnable{
    public synchronized void sendMs() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t sendMS()");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t sendEmail()");
    }

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t get()");
            set();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t set()");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
/**
 * @author 13758
 */
@SuppressWarnings("all")
public class Re {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //线程主程序
                try {
                    phone.sendMs();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"ThreadName1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //线程主程序
                try {
                    phone.sendMs();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"ThreadName2").start();

        System.out.println("==============");
        try { TimeUnit.SECONDS.sleep(3); } catch (Exception e) { e.printStackTrace(); }

        Thread thread3 = new Thread(phone);
        Thread thread4 = new Thread(phone);
        thread3.start();
        thread4.start();
    }
}
