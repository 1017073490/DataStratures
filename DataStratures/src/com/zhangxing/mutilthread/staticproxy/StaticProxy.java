package com.zhangxing.mutilthread.staticproxy;

/**
 * @author zhangxing
 * @Description: 静态代理代码分析
 * @date 2020/4/21 21:00
 * 真实对象和代理对象都要实现同一个接口
 * 代理对象要代理真实角色
 *
 * 好处：
 * 代理对象可以完成很多真实对象无法完成的事情
 * 真实对象专注做自己的事情
 */
public class StaticProxy {
    public static void main(String[] args) {
        Me me = new Me();
        WeddingCompany weddingCompany = new WeddingCompany(me);
        weddingCompany.HappyMarry();
    }
}


interface Marry{
    void HappyMarry();

}

//真实角色
class Me implements Marry{
    @Override
    public void HappyMarry() {
        System.out.println("你结婚啦！");
    }
}

//代理角色-->帮助结婚
class WeddingCompany implements Marry{
    //代理谁-->真实目标角色
    private Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;
    }

    @Override
    public void HappyMarry() {
        before();
        this.target.HappyMarry();
        after();
    }

    private void before() {
        System.out.println("结婚前布置现场。。。");
    }

    private void after() {
        System.out.println("收尾款。。。");
    }
}
