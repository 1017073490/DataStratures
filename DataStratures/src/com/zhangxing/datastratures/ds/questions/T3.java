package com.zhangxing.datastratures.ds.questions;

/**
 * @Author zhangxing
 * @Date 2021/8/24 22:27
 * @Version 1.0
 * @Description
 */
public class T3 {
    public static void main(String[] args) {
        int i = reverseInteger(800);
        System.out.println(i);
    }

    /**
     * @param number: A 3-digit number.
     * @return: Reversed number.
     */
    public static int reverseInteger(int number) {
        // write your code here
        int ans = 0;
        int fm = 100;
        while (number != 0) {
            ans = ans + (number % 10) * fm;
            number /= 10;
            fm /= 10;
        }
        return ans;
    }
}
