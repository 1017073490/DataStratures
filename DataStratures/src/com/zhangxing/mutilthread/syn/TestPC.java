package com.zhangxing.mutilthread.syn;

/**
 * @author zhangxing
 * @Description: 管程法
 * @date 2020/4/23 9:42
 */
public class TestPC {
    public static void main(String[] args) {
        SynContainer container = new SynContainer();

        new Producer(container).start();
        new Consumer(container).start();
    }

}


class Producer extends Thread {
    SynContainer container;

    public Producer(SynContainer container) {
        this.container = container;
    }

    //生产

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            container.push(new Chicken(i));
            System.out.println("生产了第" + i + "只鸡。");
        }
    }
}


class Consumer extends Thread {
    SynContainer container;

    public Consumer(SynContainer container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了第" + container.pop().id + "只鸡。");
        }
    }
}


class Chicken {
    int id;

    public Chicken(int id) {
        this.id = id;
    }
}


class SynContainer {
    Chicken[] chickens = new Chicken[10];
    int count = 0;

    //生产者放入产品
    public synchronized void push(Chicken chicken) {
        //容器满了就需要等待消费者消费
        if (count == chickens.length) {
            //通知消费者消费，生产等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //如果没有满，我们就需要丢入产品
        chickens[count] = chicken;
        count++;

        //通知消费者可以消费了
        this.notify();

    }

    //消费者消费产品
    public synchronized Chicken pop() {
        //判断能否消费
        if (count == 0) {
            //等待生产者生产，消费者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        count--;
        Chicken chicken = chickens[count];
        //吃完了，通知生产者可以生产了
        this.notify();

        return chicken;
    }
}
