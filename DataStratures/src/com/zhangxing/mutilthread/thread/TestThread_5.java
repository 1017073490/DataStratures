package com.zhangxing.mutilthread.thread;

/**
 * @author zhangxing
 * @Description: 龟兔赛跑复习线程。。。
 * @date 2020/4/21 15:00
 */
public class TestThread_5 implements Runnable {
    private static String winner;

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if (Thread.currentThread().getName().equals("兔子") && i % 10 == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            boolean flag = gameOver(i);
            if (flag) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + "-->前进" + i + "米");
        }
    }

    private boolean gameOver(int distance) {
        if (winner != null) {
            return true;
        }
        if (distance >= 100) {
            winner = Thread.currentThread().getName();
            System.out.println("winner:" + winner);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TestThread_5 race = new TestThread_5();
        new Thread(race, "兔子").start();
        new Thread(race, "乌龟").start();
    }
}
