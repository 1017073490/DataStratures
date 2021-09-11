package com.zhangxing.datastratures.ds.bgw;

/**
 * @Author zhangxing
 * @Date 2021/8/24 21:25
 * @Version 1.0
 * @Description
 */
public class IntegerAndInt {
    public static void main(String[] args) {
        short s1 = 1;
        short s2 = 1;
        // 在 s1 + 1 运算时会自动提升表达式的类型为 int
        // 那么将 int 型值赋值给 short 型变量，s1 会出现类型转换错误。
        // 强转才可以
        s1 = (short) (s1 + 1);
        // s1 += 1; 来说，+= 是 Java 语言规定的运算符
        // ，Java 编译器会对它进行特殊处理，因此可以正确编译。
        s2 += 1;
        new IntegerAndInt().intVSInteger();

        // 八种基本数据类型: byte、short、int、long、float、double、boolean、char。
        // 都有其对应的封装类

    }

    public void intVSInteger(){
        //（1）int 是 Java 的八种基本数据类型之一，而 Integer 是 Java 为 int 类型提供的封装类；
        //（2）int 型变量的默认值是 0，Integer 变量的默认值是 null，
        // 这一点说明 Integer 可以区分出未赋值和值为 0 的区分；
        //（3）Integer 变量必须实例化后才可以使用，而 int 不需要。
        // 等于Integer i = Integer.valueOf(100)
        Integer i = 100;
        Integer i2 = 100;
        //1、由于 Integer 变量实际上是对一个 Integer 对象的引用
        // ，所以两个通过 new 生成的 Integer 变量永远是不相等的，因为其内存地址是不同的；
        //缓存除外
            // 包装类型间的相等判断应该用equals，而不是'=='。针对equals和==
        // equals是判断两个变量或者实例指向同一个内存空间的值是不是相同
        // 而==是判断两个变量或者实例是不是指向同一个内存空间
        // 举个通俗的例子来说，==是判断两个人是不是住在同一个地址，而equals是判断同一个地址里住的人是不是同一个
            // 如果比较的对象是基本数据类型，则比较的是数值是否相等；
            // 如果比较的是引用数据类型，则比较的是对象的地址值是否相等。
            // equals 方法：用来比较两个对象的内容是否相等。注意：equals 方法不能用于比较基本数据类型的变量。
            // 如果没有对 equals 方法进行重写，则比较的是引用类型的变量所指向的对象的地址
            // （很多类重新了 equals 方法，比如 String、Integer 等把它变成了值比较，
            // 所以一般情况下 equals 比较的是值是否相等）。
        System.out.println(i.equals(i2));
        System.out.println(i.equals(100));
        // Integer 变量和 int 变量比较时，只要两个变量的值是相等的，则结果为 true。
        // 因为包装类 Integer 和基本数据类型 int 类型进行比较时，
        // Java 会自动拆包装类为 int，然后进行比较，实际上就是两个 int 型变量在进行比较；
        /*
            public static Integer valueOf(int i) {
                if (i >= IntegerCache.low && i <= IntegerCache.high)
                    return IntegerCache.cache[i + (-IntegerCache.low)];
                return new Integer(i);
            }
            从上面的代码中可以看出：Java 对于 [-128, 127] 之间的数会进行缓存，
            比如：Integer i = 127，会将 127 进行缓存，
            下次再写 Integer j = 127 的时候，就会直接从缓存中取出，而对于这个区间之外的数就需要 new 了。
        * */
        Integer m = new Integer(1);
    }


    // PS：包装类的缓存
    /**
     Boolean：全部缓存
     Byte：全部缓存
     Character：<= 127 缓存
     Short：-128 — 127 缓存
     Long：-128 — 127 缓存
     Integer：-128 — 127 缓存
     Float：没有缓存
     Doulbe：没有缓存
     */

    //常量池和堆？

}
