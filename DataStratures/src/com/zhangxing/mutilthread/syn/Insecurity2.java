package com.zhangxing.mutilthread.syn;

/**
 * @author zhangxing
 * @Description: 不安全的银行实例
 * @date 2020/4/22 18:49
 */
public class Insecurity2 {
    public static void main(String[] args) {
        Account account = new Account(1000, "基金");
        Drawing you = new Drawing(account, 50, "你");
        Drawing girl = new Drawing(account, 100, "媳妇");
        you.start();
        girl.start();
    }

}


class Account {
    int money;
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}


class Drawing extends Thread {
    Account account;
    int drawingMoney;
    int nowMoney;

    public Drawing(Account account, int drawingMoney, String name) {
        super(name);
        this.account = account;
        this.drawingMoney = drawingMoney;

    }

    @Override
    public void run() {
        //synchronized默认锁的是this。锁的必须是变化的量
        synchronized (account){
            if (account.money - drawingMoney < 0) {
                System.out.println(Thread.currentThread().getName() + "钱不够。。。");
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            account.money = account.money - drawingMoney;
            nowMoney = nowMoney + drawingMoney;
            System.out.println(account.name + "余额为" + account.money);
            //this.getName()+Thread.currentThread().getName()
            System.out.println(this.getName() + "手里的钱：" + nowMoney);
        }

    }
}
