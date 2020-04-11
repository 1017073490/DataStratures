package com.zhangxing.recursion;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-6 13:02
 */
public class RecursionTest {
    public static void main(String[] args) {
        System.out.println(factorial(5));
    }

    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }
}

