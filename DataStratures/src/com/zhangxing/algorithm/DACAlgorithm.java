package com.zhangxing.algorithm;

/**
 * 思想！
 * @author zhangxing
 * @Description: 分治算法的实现
 * 复杂问题分解成简单的子问题，再将子问题的解合并为原问题的解。
 * @date 2020/4/17 9:04
 */
public class DACAlgorithm {
    static int count = 0;
    public static void main(String[] args) {

        //汉诺塔的实现。
        //n>=2时。只分2个盘，一个最下面的盘。一个其余的盘
        //其余的盘：A->B
        //最下面的盘：A->C
        //其余的盘：B->C
        int num = 5;
        hanoiTower(num, 'A', 'B', 'C');
        System.out.println(count);
    }

    public static void hanoiTower(int num, char a, char b, char c) {
        count++;
        //只有一个盘
        if (num == 1) {
            System.out.println("第1个盘: " + a + "->" + c);
        } else {
            //这个递归的意思是最终要把上面的盘移动到b,过程种将使用到c
            hanoiTower(num - 1, a, c, b);
            System.out.println("第" + num + "个盘: " + a + "->" + c);
            hanoiTower(num - 1, b, a, c);
        }
    }


}
