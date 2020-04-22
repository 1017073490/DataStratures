package com.zhangxing.mutilthread.thread;
import com.zhangxing.mutilthread.thread.WebDownLoader;

import java.util.concurrent.*;

/**
 * @author zhangxing
 * @Description: Callable接口实现多线程
 * @date 2020/4/21 15:24
 * 可以定义返回值
 * 可以抛出异常
 *
 */
public class TestThread_6 implements Callable{
    private String url;
    private String fileName;

    public TestThread_6(String url, String fileName) {
        this.url = url;
        this.fileName = fileName;
    }

    @Override
    public Boolean call() {
        WebDownLoader loader = new WebDownLoader();
        loader.downLoader(url, fileName);
        System.out.println("下载了文件，名为：" + fileName);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestThread_6 thread_1 = new TestThread_6("http://icon.nipic.com/BannerPic/20200327/home/20200327142844.jpg", "1.jpg");
        TestThread_6 thread_2 = new TestThread_6("http://icon.nipic.com/BannerPic/20200420/original/20200420095303_1.jpg", "2.jpg");
        TestThread_6 thread_3 = new TestThread_6("http://icon.nipic.com/BannerPic/20200420/original/20200420095402_1.jpg", "3.jpg");
        /**
         * 1.创建执行服务
         * 2.提交执行
         * 3.获取结果
         * 4.关闭服务
         */
        ExecutorService service = Executors.newFixedThreadPool(3);

        Future<Boolean> submit_1 = service.submit(thread_1);
        Future<Boolean> submit_2 = service.submit(thread_2);
        Future<Boolean> submit_3 = service.submit(thread_3);

        boolean res1 = submit_1.get();
        boolean res2 = submit_2.get();
        boolean res3 = submit_3.get();

        service.shutdownNow();

    }
}



