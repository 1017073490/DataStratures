package com.zhangxing.datastratures.ds.bgw;

/**
 * @Author zhangxing
 * @Date 2021/9/1 12:34
 * @Version 1.0
 * @Description
 */
public abstract class JavaQ1 {
    public static void main(String[] args) {
        String[] a;

        String str1 = new String("hello");
        String str2 = new String("hello");
        String str3 = "hello";
        String str4 = "hello";
        String str5 = "he"+"llo";
        String str6 = "he";
        String str7 = "llo";
        System.out.println(str1==str2);
        System.out.println(str1==str3);
        System.out.println(str3==str4);
        System.out.println(str3=="hello");
        System.out.println(str4==str5);
        System.out.println(str4==(str6+str7));
    }
    public int constInt = 5;
    //add code here
    /**
     * 有方法体的不能作为抽象函数
     * 返回值不能作为重载的依据
     * 在类中不能constInt = constInt + 5
     * 是抽象方法，抽象类可以包含抽象方法，也可以不包含，实现重载。
     * 父类里面的构造函数里面的方法在子类中已经进行了重写所以会调用子类的这个方法
     * 子类不可以继承父类的构造方法，只可以调用父类的构造方法。
     * 子类中所有的构造函数都会默认访问父类中的空参数构造函数，
     *      这是因为子类的构造函数内第一行都有默认的super（）语句。
     *      super（）表示子类在初始化时调用父类的空参数的构造函数来完成初始化。
     * 方法没有继承一说，只有重载和重写
     * 声明一个数组时，不能直接限定数组长度，只有在创建实例化对象时，才能对给定数组长度.。
     */
    public abstract void method(int a);
    public static void method2() {

    }
    public void method() {
    }
}
