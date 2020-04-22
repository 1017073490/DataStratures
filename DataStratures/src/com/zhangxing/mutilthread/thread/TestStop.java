package com.zhangxing.mutilthread.thread;

/**
 * @author zhangxing
 * @Description: 停止线程
 * @date 2020/4/22 9:27
 * 1.建议正常停止
 * 2.建议使用标志位
 * 不要使用stop或者destroy
 */
public class TestStop implements Runnable {
    private boolean flag = true;

    public static void main(String[] args) {
        TestStop testStop = new TestStop();
        new Thread(testStop).start();
        for (int i = 0; i < 1000; i++) {
            System.out.println("main" + i);
            if (i == 500) {
                testStop.testStop();
                System.out.println("线程停止！");
            }
        }
    }

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println("run..." + (i++));
        }
    }

    //设置一个公开的方法停止线程，转化标志位
    public void testStop() {
        this.flag = false;
    }
}
