package com.zhangxing.datastratures.ds.bgw;

/**
 * @Author zhangxing
 * @Date 2021/8/24 21:01
 * @Version 1.0
 * @Description
 */
@SuppressWarnings("all")
public abstract class AbstractAndInterface {
    // 静态变量：是被 static 修饰的变量，也称为类变量，它属于类，
    // 因此不管创建多少个对象，静态变量在内存中有且仅有一个拷贝；
    // 静态变量可以实现让多个对象共享内存。

    //实例变量：属于某一实例，需要先创建对象，然后通过对象才能访问到它。
    static int sv1 = 2;
    int v2 = 3;

    // 1.抽象类中可以定义构造函数，接口不能定义构造函数；
    public AbstractAndInterface() {
        System.out.println("我是父类构造函数");
    }

    // 2.抽象类中可以有抽象方法和具体方法，而接口中只能有抽象方法（public abstract）；
    public abstract void test_1();

    public void test_2() {
        System.out.println("我是父类的具体方法");
    }

    public static void test_3() {
        System.out.println("我是父类的静态方法");
    }
}

interface TestInterface {
    // 可以直接用接口调用静态方法
    static void test3() {
        System.out.println("接口中的静态方法。");
    }

    //允许在接口中包含带有具体实现的方法，使用 default 修饰，这类方法就是默认方法。
    //创建对象后使用接口中的default方法
    default void test4() {
        System.out.println("我是接口的默认方法");
    }

    //接口中的方法必须实现，但是静态方法和默认方法不必须
    public void neededImp();
}

class TestAAI implements TestInterface {
    @Override
    public void neededImp() {
        System.out.println();
    }

    public static void main(String[] args) {
        TestInterface.test3();
        TestAAI testAAI = new TestAAI();
        testAAI.test4();
    }

    public TestAAI() {

    }
}

class TestAAT2 extends AbstractAndInterface {

    @Override
    public void test_1() {
        //只需实现抽象父类的抽象方法
        System.out.println("实现抽象父类的抽象方法");
    }

    public static void main(String[] args) {
        System.out.println(AbstractAndInterface.sv1);
        //创建对象时会调用默认构造方法
        AbstractAndInterface anInterface = new AbstractAndInterface() {
            @Override
            public void test_1() {
            }
        };

        System.out.println(anInterface.v2);
        TestAAT2 testAAT2 = new TestAAT2();
        testAAT2.test_1();
        testAAT2.test_2();
        // static 方法跟类的任何实例都不相关，所以概念上不适用。
        // 不应该通过类实例访问静态方法
        testAAT2.test_3();
    }
}
