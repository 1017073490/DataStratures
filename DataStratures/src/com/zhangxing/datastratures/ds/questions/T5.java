package com.zhangxing.datastratures.ds.questions;

/**
 * @Author zhangxing
 * @Date 2021/8/24 23:10
 * @Version 1.0
 * @Description
 */
public class T5 {
    public static void main(String[] args) {
        canWinBash(25);
    }

    /**
     * @param n: an integer
     * @return: whether you can win the game given the number of stones in the heap
     */
    public static boolean canWinBash(int n) {
        // Write your code here
        if (n % 4 != 0) {
            return true;
        }
        return false;
    }
}
