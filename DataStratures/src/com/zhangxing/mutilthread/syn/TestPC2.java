package com.zhangxing.mutilthread.syn;

/**
 * @author zhangxing
 * @Description: 信号灯法：标志位解决
 * @date 2020/4/23 10:27
 */
public class TestPC2 {
    public static void main(String[] args) {
        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();
    }
}


class Player extends Thread {
    TV tv;

    public Player(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0){
                this.tv.play("天天向上。");
            }else {
                this.tv.play("抖音广告。");
            }
        }
    }
}


class Watcher extends Thread {
    TV tv;

    public Watcher(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            tv.watch();
        }
    }
}


class TV {
    String voice;
    boolean flag = true;

    //表演
    public synchronized void play(String voice) {
        if (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("演员表演了" + voice);
        //通知观众观看。通知唤醒
        this.notify();
        this.voice = voice;
        this.flag = !this.flag;
    }

    //观看
    public synchronized void watch() {
        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("观众观看了" + voice);
        //通知演员表演。通知唤醒
        this.notify();
        this.flag = !this.flag;
    }


}
