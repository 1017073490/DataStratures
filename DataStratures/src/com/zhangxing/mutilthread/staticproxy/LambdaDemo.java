package com.zhangxing.mutilthread.staticproxy;

/**
 * @author zhangxing
 * @Description: 理解Lambda表达式
 * @date 2020/4/21 21:17
 * 可以用Lambda表达式来创建函数式接口（包含唯一抽象方法的接口）的对象
 * 它属于函数式编程的概念（函数式编程关心数据的映射，命令式编程关心解决问题的步骤）
 */
public class LambdaDemo {
    //3.静态内部类
    static class Like2 implements ILike{
        @Override
        public void lambda() {
            System.out.println("like lambda 2");
        }
    }


    public static void main(String[] args) {
        ILike like = new Like();
        like.lambda();

        like = new Like2();
        like.lambda();

        //4.局部内部类
        class Like3 implements ILike{
            @Override
            public void lambda() {
                System.out.println("lambda 3");
            }
        }

        like = new Like3();
        like.lambda();

        //5.匿名内部类.没有类的名称，必须借助接口或者父类
        like = new ILike() {
            @Override
            public void lambda() {
                System.out.println("匿名的lambda");
            }
        };
        like.lambda();

        //6.lambda简化
        like = () -> {
            System.out.println("lambda5");
        };
        like.lambda();


    }
}


//1.定义一个函数式接口
interface ILike{
    void lambda();
}


//2.实现类
class Like implements ILike{
    @Override
    public void lambda() {
        System.out.println("i like lambda");
    }
}
