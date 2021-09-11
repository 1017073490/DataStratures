package com.zhangxing.datastratures.ds.bgw;

/**
 * @Author zhangxing
 * @Date 2021/8/23 22:48
 * @Version 1.0
 * @Description
 */
@SuppressWarnings("all")
public class FAndS {
    private int a = 0;

    public static void main(String[] args) {

        // 运行时多态、子类与父类之间、子类重写父类的方法具有相同的返回类型、更好的访问权限。
        Son1 f1 = new Son1();
//        FAndS f2 = new FAndS();
        // 子类也会继承父类的构造方法，无论有没有什么super()
        f1.sout(1);
        f1.sout();
        f1.pri();
        // 这句话可以输出“我是noDynamic”，但是不推荐此写法：不应该通过类实例来访问静态成员
        // 也就是说静态的方法可以被继承，但是不能重写。
        // 如果父类和子类中存在同样名称和参数的静态方法，
        // 那么该子类的方法会把原来继承过来的父类的方法隐藏，而不是重写。
        FAndS.noDynamic();
        f1.noDynamic();
    }

    public FAndS() {
        //（1）名字与类名相同；
        //（2）没有返回值，但不能用 void 声明构造函数: 用void申明就失去了构造函数的特性
        //（3）成类的对象时自动执行，无需调用。
        // 自动生成的构造方法，不写默认也有
        // 显示调用可以产生一些东西
        // 这样创建对象时会执行下面一行
        System.out.println("执行了构造方法");
    }

    public FAndS(int a) {
        System.out.println("执行了构造方法：" + a);
    }


    // Java 中 static 方法不能被覆盖，因为根据多态特性我们知道方法覆盖是基于运行时动态绑定的，
    // 而 static 方法是编译时静态绑定的。static 方法跟类的任何实例都不相关，所以概念上不适用。

    public static void noDynamic() {
        System.out.println("我是noDynamic");
    }

    // 当然，Java 中也不可以覆盖 private 的方法，
    // 因为 private 修饰的变量和方法只能在当前类中使用，
    // 如果是其他的类继承当前类是不能访问到 private 变量或方法的，当然也不能覆盖。
    private void pri() {
        System.out.println("我很隐私");
    }

    public void sout() {
        System.out.println("Father");
    }

    // 编译时多态、同一个类中同名的方法具有不同的参数列表、
    // 不能根据返回类型进行区分【因为：函数调用时不能指定类型信息，编译器不知道你要调哪个函数】；

    public void sout(int a) {
        System.out.println("Father" + a);
    }

}

class Son1 extends FAndS {
    public void sout() {
        System.out.println("son1");
    }

    // 而在子类的构造方法中又没有用 super() 来调用父类中特定的构造方法，
    // 则编译时将发生错误，因为 Java 程序在父类中找不到没有参数的构造方法可供执行
    public Son1() {
        // 加了这个调用父类的特定构造方法就不会报错，
        // 所以如果不需要调用特定的构造方法
        // 在父类建一个无参构造函数即可，运行也没问题
//        super(2);
    }


//        public void noDynamic(){
//        System.out.println("111");
//    }
    // 定义同名方法会直接报错，报错：
    // cannot override noDynamic() in com.zhangxing.datastratures.ds.bgw.FAndS
    // overridden method is static

    // 可以单独调用，但是就没有覆盖一说了。
    public void pri() {
        System.out.println("我是子类的隐私");
    }
}

class Son2 extends FAndS {
    public Son2(int a) {
        super(a);
    }

    public void sout() {
        System.out.println("son2");
    }
}
