package com.zhangxing.mutilthread.thread;

/**
 * @author zhangxing
 * @Description: 观察测试线程的状态
 * @date 2020/4/22 10:44
 */
public class TestState {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("=======");
        });

        System.out.println("1" + thread.getState());

        //观察启动后
        thread.start();
        System.out.println("2" + thread.getState());

        while (thread.getState() != Thread.State.TERMINATED) {
            try {
                Thread.sleep(100);
                System.out.println("3" + thread.getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
