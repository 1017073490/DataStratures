package com.zhangxing.mutilthread.syn;

/**
 * @author zhangxing
 * @Description: 死锁
 * @date 2020/4/22 19:46
 * 2个线程互相拥有对方需要的资源，然后形成僵持
 */
public class DeadLock {
    public static void main(String[] args) {
        Makeup girl1 = new Makeup(0, "zx");
        Makeup girl2 = new Makeup(1, "fd");
        girl1.start();
        girl2.start();
    }
}


class Lipstick{

}


class Mirror{

}


class Makeup extends Thread{
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();
    int choice;
    String girl;

    public Makeup(int choice, String girl) {
        this.choice = choice;
        this.girl = girl;
    }

    @Override
    public void run() {
        makeup();
    }

    private void makeup(){
        if (choice == 0){
            synchronized (lipstick){
                System.out.println(this.girl+"获得口红的锁");
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            synchronized (mirror){
                System.out.println(this.girl+"获得了镜子的锁");
            }
        }else {
            synchronized (mirror){
                System.out.println(this.girl+"获得了镜子的锁");
                try {
                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            synchronized (lipstick){
                System.out.println(this.girl+"获得了口红的锁");
            }
        }
    }
}
