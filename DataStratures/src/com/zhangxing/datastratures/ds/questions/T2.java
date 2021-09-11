package com.zhangxing.datastratures.ds.questions;

/**
 * @Author zhangxing
 * @Date 2021/8/23 21:07
 * @Version: 1.0
 * @Description: A + B 问题
 * 　　异或的运算方法是一个二进制运算：
 * 　　1^1=0
 * 　　0^0=0
 * 　　1^0=1
 * 　　0^1=1
 * 　　两者相等为0,不等为1
 * <<:左移运算符，num << 1,相当于num乘以2
 * >>:右移运算符，num >> 1,相当于num除以2
 */
public class T2 {
    public static void main(String[] args) {
//        T2.findSingleElement();
        int aplusb = aplusb(5, 7);
        System.out.println(aplusb);

    }

    /**
     * @param a: An integer
     * @param b: An integer
     * @return: The sum of a and b
     */
    public static int aplusb(int a, int b) {
        // write your code here
        while (b != 0) {
            int a1 = a ^ b;
            int b1 = (a & b) << 1;
            a = a1;
            b = b1;
        }
        return a;
    }

    public static void findSingleElement() {
        int[] nums = {2, 2, 3, 4, 4};
        for (int i = 1; i < nums.length; i++) {
            nums[0] = nums[0] ^ nums[i];
        }
        System.out.println(nums[0]);
    }
}
