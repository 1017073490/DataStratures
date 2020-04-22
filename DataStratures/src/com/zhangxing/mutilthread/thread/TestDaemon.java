package com.zhangxing.mutilthread.thread;

/**
 * @author zhangxing
 * @Description: 测试守护线程
 * @date 2020/4/22 14:40
 */
public class TestDaemon {
    public static void main(String[] args) {
        God god = new God();
        You you = new You();

        Thread thread = new Thread(god);
        thread.setDaemon(true);
        thread.start(); //守护线程启动。用户线程结束，守护线程也将结束。

        new Thread(you).start();    //用户线程启动
    }

}


class God implements Runnable{
    @Override
    public void run() {
        while (true){
            System.out.println("守护我每一天！");
        }
    }
}


class You implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("第" + i + "天开心");
        }
        System.out.println("Bey!World!");
    }
}
