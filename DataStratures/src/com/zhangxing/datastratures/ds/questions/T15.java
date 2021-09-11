package com.zhangxing.datastratures.ds.questions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @Author zhangxing
 * @Date 2021/9/9 10:04
 * @Version 1.0
 * @Description
 */
public class T15{
    static void fun(int... x){
        for (int i : x) {
            System.out.println(i);
        }
    }
    public enum Dogs{one,two};
    public static void main(String[] args) throws IOException {
        // 使用文件名称创建流对象
        FileOutputStream fos = new FileOutputStream("fos.txt");
        // 写出数据
        fos.write(97); // 写出第1个字节
        fos.write(98); // 写出第2个字节
        fos.write(99); // 写出第3个字节
        // 关闭资源
        fos.close();
        System.out.println('a'+0);
        System.out.println(Dogs.one==Dogs.one);
        System.out.println(Dogs.one.equals(Dogs.one));


        int x=0,y=10;
        do{
            y--;
            ++x;
        }while (x<5);
        System.out.println(x+","+y);
        List list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");


    }

    public static void go(){

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("foo");
            }
        };
        Thread t = new Thread(runnable);
        t.start();
        t.start();
    }
}
