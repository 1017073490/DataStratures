package com.zhangxing.datastratures.ds.javaBase;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @Author zhangxing
 * @Date 2021/9/6 14:59
 * @Version 1.0
 * @Description
 */
@SuppressWarnings("all")
public class SingletonDemo {
    private static volatile SingletonDemo instance = null;
    private static AtomicReference<Integer> abaAto = new AtomicReference<>(100);
    private static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(100, 1);

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + "\t构造方法");
    }

    // DCL 双端检索机制
    public static SingletonDemo getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
//        new Thread(() -> {
//            abaAto.compareAndSet(100, 101);
//            abaAto.compareAndSet(101, 100);
//        }, "Thread-X-1").start();

//        new Thread(() -> {
//            try {
//                Thread.sleep(1);
//                System.out.println(abaAto.compareAndSet(100, 2019) + "\t" + abaAto.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }, "Thread-X-2").start();

        new Thread(() -> {
            System.out.println(stampedReference.getStamp() + "版本1");
            try {
                Thread.sleep(1);
                stampedReference.compareAndSet(100, 101, 1, 2);
                System.out.println(stampedReference.getStamp() + "版本2");
                stampedReference.compareAndSet(101, 100, 2, 3);
                System.out.println(stampedReference.getStamp() + "版本3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-X-3").start();

        new Thread(() -> {
            int stamp = stampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() +stamp + "版本1");
            try {
                Thread.sleep(3);
                boolean res = stampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
                System.out.println(Thread.currentThread().getName() + "修改" + res + "版本：" + stampedReference.getStamp());
                System.out.println(stampedReference.getReference());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-X-4").start();


//        AtomicReference<User> atomicReference = new AtomicReference<>();
//        User zx = new User("zx", 12);
//        User zx2 = new User("zx2", 13);
//        atomicReference.set(zx);
//        System.out.println(atomicReference.compareAndSet(zx, zx2) + "\t" + atomicReference.get().toString());
//        System.out.println(atomicReference.compareAndSet(zx, zx2) + "\t" + atomicReference.get().toString());
//        // 并发！
//        for (int i = 0; i < 20; i++) {
//            new Thread(() -> {
//                SingletonDemo.getInstance();
//            }, String.valueOf(i)).start();
//        }


    }
}


class User {
    String userName;
    int age;

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
