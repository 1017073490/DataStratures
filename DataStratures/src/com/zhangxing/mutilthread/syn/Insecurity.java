package com.zhangxing.mutilthread.syn;

/**
 * @author zhangxing
 * @Description: 三大不安全案例
 * @date 2020/4/22 15:03
 * 每个线程在自己的工作内存交互，内存控制不当会造成数据不一致。
 */
public class Insecurity {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();

        new Thread(buyTicket, "zx").start();
        new Thread(buyTicket, "zf").start();
        new Thread(buyTicket, "周峰").start();
    }

}


class BuyTicket implements Runnable {
    private int ticketCount = 10;
    boolean flag = true;

    @Override
    public void run() {
        //买票
        while (flag) {
            buy();
        }
    }

    private synchronized void buy() {
        //判断是否有票
        if (ticketCount <= 0) {
            flag = false;
            return;
        }
        //模拟延时
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //买票
        System.out.println(Thread.currentThread().getName() + "买到" + ticketCount--);
    }

}
