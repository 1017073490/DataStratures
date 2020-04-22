package com.zhangxing.mutilthread.staticproxy;

/**
 * @author zhangxing
 * @Description: 函数式接口的扩展理解
 * @date 2020/4/21 21:47
 *
 */
public class TestLambda {
    public static void main(String[] args) {
        ILove love = (int a) -> {
                System.out.println("i love " + a);
            };
        love.love(1024);

        love = a -> {
            System.out.println("i love " + a);
        };
        love.love(520);


    }



}


interface ILove {
    void love(int a);
}



