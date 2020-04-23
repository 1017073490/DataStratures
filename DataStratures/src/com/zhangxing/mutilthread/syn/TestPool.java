package com.zhangxing.mutilthread.syn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangxing
 * @Description: 线程池
 * @date 2020/4/23 13:54
 */
public class TestPool {
    public static void main(String[] args) {
        //创建线程池，
        ExecutorService service = Executors.newFixedThreadPool(10);

        service.execute(new MyThreads());
        service.execute(new MyThreads());
        service.execute(new MyThreads());

        service.shutdownNow();
    }
}


class MyThreads implements Runnable{
    @Override
    public void run() {
            System.out.println(Thread.currentThread().getName());
    }
}
