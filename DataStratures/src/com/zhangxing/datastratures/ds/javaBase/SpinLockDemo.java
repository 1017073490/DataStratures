package com.zhangxing.datastratures.ds.javaBase;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author zhangxing
 * @Date 2021/9/6 22:15
 * @Version 1.0
 * @Description
 */
public class SpinLockDemo {
    //原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void muUnLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName()+"\t unlock");
    }

    public static void main(String[] args) {
        //线程操作资源类
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()-> {
            //线程主程序
            spinLockDemo.myLock();
            try { TimeUnit.SECONDS.sleep(10); } catch (Exception e) { e.printStackTrace(); }
            spinLockDemo.muUnLock();
        },"ThreadName-AAA").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (Exception e) { e.printStackTrace(); }

        new Thread(()-> {
            //线程主程序
            spinLockDemo.myLock();
            spinLockDemo.muUnLock();
        },"ThreadName-BBB").start();
    }
}
