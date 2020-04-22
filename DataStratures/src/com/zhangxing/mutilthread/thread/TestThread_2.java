package com.zhangxing.mutilthread.thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author zhangxing
 * @Description: 实现多线程同步下载图片
 * @date 2020/4/21 14:15
 */
public class TestThread_2 implements Runnable {
    private String url;
    private String fileName;

    public TestThread_2(String url, String fileName) {
        this.url = url;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        WebDownLoader loader = new WebDownLoader();
        loader.downLoader(url, fileName);
        System.out.println("下载了文件，名为：" + fileName);
    }

    public static void main(String[] args) {
        TestThread_2 thread_1 = new TestThread_2("http://icon.nipic.com/BannerPic/20200327/home/20200327142844.jpg", "1.jpg");
        TestThread_2 thread_2 = new TestThread_2("http://icon.nipic.com/BannerPic/20200420/original/20200420095303_1.jpg", "2.jpg");
        TestThread_2 thread_3 = new TestThread_2("http://icon.nipic.com/BannerPic/20200420/original/20200420095402_1.jpg", "3.jpg");
        new Thread(thread_1).start();
        new Thread(thread_2).start();
        new Thread(thread_3).start();
    }
}


class WebDownLoader {
    public void downLoader(String url, String fileName) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常。。。");
        }
    }
}
