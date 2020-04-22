package com.zhangxing.mutilthread.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * @author zhangxing
 * @Description: 模拟倒计时
 * @date 2020/4/22 9:39
 */
public class TestClock {
    public static void main(String[] args){
        Date start = new Date(System.currentTimeMillis());

        while (true){
            try {
                Thread.sleep(1000);
                System.out.println(new SimpleDateFormat("HH:mm:ss").format(start));
                start = new Date(System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }


    private static void tenDown() throws InterruptedException {
        int num = 10;
        while (true) {
            Thread.sleep(1000);
            System.out.println(num--);
            if (num <= 0) {
                break;
            }
        }
    }
}
