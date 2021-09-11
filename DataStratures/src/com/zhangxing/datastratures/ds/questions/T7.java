package com.zhangxing.datastratures.ds.questions;

import java.util.Arrays;

/**
 * @Author zhangxing
 * @Date 2021/8/26 7:39
 * @Version 1.0
 * @Description
 */
public class T7 {
    public static void main(String[] args) {
        // 对于==，比较的是值是否相等
        // 作用于基本数据类型的变量，则直接比较其存储的 “值”是否相等：即两个变量的值是否相等
        // 作用于引用类型的变量，则比较的是所指向的对象的地址：即对于引用型变量表示的是两个变量在栈中存储的地址是否相同，即栈中的内容是否相同
        // 对于equals，主要是看有没有重写
        // 如果一个类没有对equals方法进行重写（即没有自己定义的equals方法），
        // 那么ta将继承Object类的equals方法，equals本身和==没有区别，对于基本数据都是比较值，对于对象都是比较是否为内存地址；
        String str1 = new String("abc");
        String str2 = new String("abc");
        System.out.println(str1.equals(str2));
        System.out.println(str1 == str2);
        char a = 57;
        System.out.println(T7.isAlphanumeric(a));
        System.out.println(T7.isLeapYear(9999));
        System.out.println(T7.transfer('a'));
        System.out.println(T7.getTheMonthDays(1500, 5));
        // 这个为true
        Integer[] month_31 = {1, 3, 5, 7, 8, 10, 12};
        // !!!下面这个contains输出false
        // https://blog.csdn.net/zhangjianming2018/article/details/81358649
//        int[] month_31 = {1, 3, 5, 7, 8, 10, 12};
        System.out.println(Arrays.asList(month_31).contains(5));
    }

    public static boolean isAlphanumeric(char c) {
        // write your code here
        // ASICC: 0->48  9->57
        if (48 <= c && c <= 57) {
            return true;
        } else if (65 <= c && c <= 90) {
            // ASICC: A->65  Z->90
            return true;
        } else if (97 <= c && c <= 122) {
            // ASICC: a->97  9->122
            return true;
        } else {
            return false;
        }
    }

    public static boolean isLeapYear(int n) {
        // write your code here
        return n % 4 == 0 && n % 100 != 0 || n % 400 == 0;
    }

    public static char transfer(char character) {
        // write your code here
        return (char) (character - 32);
    }

    public static int getTheMonthDays(int year, int month) {
        // 优美的解法：
        // int[] day = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        // 然后再判断是否为闰年的2月
        // write your code here
        boolean flag = (year % 4 == 0 && year % 100 != 0 || year % 400 == 0);
        if (flag && month == 2) {
            return 29;
        } else {
            Integer[] month_31 = {1, 3, 5, 7, 8, 10, 12};
            Integer[] month_30 = {4, 6, 9, 11};
            if (Arrays.asList(month_31).contains(month)) {
                return 31;
            } else if (Arrays.asList(month_30).contains(month)) {
                return 30;
            } else {
                return 28;
            }
        }
    }

    public static int calculate(int a, char operator, int b) throws Exception {
        // write your code here
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
            default:
                return 0;
        }
    }

    public static int maxOfThreeNumbers(int num1, int num2, int num3) {
        // write your code here
        return num1 > num2 ? (num1 > num3 ? num1 : num3) : (num2 > num3 ? num2 : num3);
    }
}
