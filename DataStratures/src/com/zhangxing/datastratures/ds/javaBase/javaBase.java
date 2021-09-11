package com.zhangxing.datastratures.ds.javaBase;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author zhangxing
 * @Date 2021/9/2 15:57
 * @Version 1.0
 * @Description
 */
class MyData {
    volatile int num = 0;
    // https://docs.oracle.com/javase/8/docs/api/\
    // 多线程中可用原子整型包装类
    AtomicInteger temp = new AtomicInteger();

    public void add() {
        this.num = 90;
    }

    public void addPlus() {
        num++;
    }

    public void addAtomic() {
        temp.getAndIncrement();
    }

}

class Threads4 extends Thread {
    int a;

    public static void main(String[] args) {
        // JMM可见性 volatile
        // 线程操作资源类
        MyData data = new MyData();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    data.addAtomic();
                    data.addPlus();
                }
            }, String.valueOf(i)).start();
        }
//        while (data.num == 0) {
//            // 主线程一直等待
//        }
        while (Thread.activeCount() > 2) {
            // A hint to the scheduler that the current thread is willing to yield its current use of a processor.
            // 礼让线程
            Thread.yield();
        }
        System.out.println("任务结束：" + data.temp);
        System.out.println("任务结束：" + data.num);


//        String str = "";
//        System.out.println(str.split(",").length);
//        new Threads4().go();
//        Object o = new Object() {
//            public boolean equals(Object obj) {
//                return true;
//            }
//        };
//        System.out.println(o.equals("Fred"));
    }

    public void go() {
        int a = 5;
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.print("foo");
            }
        };
        Thread t = new Thread(r);
        t.start();
    }


}

public class javaBase implements test1 {
    @Override
    public void drink() {
        Object o = new Object();

    }
}

interface test1 {
    void drink();
}

enum AccountType {
    SAVING, FIXED, CURRENT;

    private AccountType() {
        System.out.println("It is a account type");
    }
}

class EnumOne {
    public static void main(String[] args) {
        System.out.println(AccountType.FIXED);
    }
}

class HasStatic {
    private static int x = 100;

    public static void main(String args[]) {
        HasStatic hs1 = new HasStatic();
        hs1.x++;
        HasStatic hs2 = new HasStatic();
        hs2.x++;
        hs1 = new HasStatic();
        hs1.x++;
        HasStatic.x--;
        System.out.println("x=" + x);
    }

}

abstract class Pvf {
    static boolean Paddy;
    private int a = 0;

    public static void main(String args[]) {
        System.out.println(Paddy);
        Paddy = false;
        String a = "1";
        a.equals(Paddy);
    }
}

class jvmStudy {
    public static void main(String[] args) throws ClassNotFoundException {
        String str1 = "a";
        String str2 = "b";
        String str3 = "ab";
        String str4 = str1 + str2;
        String str5 = new String("ab");
        System.out.println(str5.equals(str3));
        // true
        System.out.println(str5 == str3);
        // false
        System.out.println(str5.intern() == str3);
        // true
        System.out.println(str5.intern() == str4);
        // true
        System.out.println("============");
        String a = new String("ab");
        String b = new String("ab");
        String c = "ab";
        String d = "a" + "b";
        String e = "b";
        String f = "a" + e;
        System.out.println(b.intern() == a);//t
        System.out.println(b.intern() == c);//f
        System.out.println(b.intern() == d);//t
        System.out.println(b.intern() == f);//f
        System.out.println(b.intern() == a.intern());
        Class.forName("zx");
    }
}

class Test {
    public static void main(String[] args) {
        int[] a = new int[10];
        for (int i = 0; i <= 10; i++) {
            System.out.println(a[i]);
        }
        int[][] a2 = new int[][]{
                {2,3,4},
                {2,3},
                {1,2}
        };
        MyNum myNum = new MyNum();
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                myNum.add();
                System.out.println(Thread.currentThread().getName() + "修改变量");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread-X").start();
        System.out.println(myNum.num);
        while (myNum.num == 10) {

        }
        System.out.println("值已经是" + myNum.num + Thread.currentThread().getName() + "mission over");
    }
}

interface Test2 {
    public void add();

    static final String a = "123";

}

class MyNum implements Test2 {
    volatile int num = 10;

    @Override
    public void add() {

    }
}



