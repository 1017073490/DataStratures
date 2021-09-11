package com.zhangxing.datastratures.ds.questions;

import java.util.Arrays;

/**
 * @Author zhangxing
 * @Date 2021/8/24 23:01
 * @Version 1.0
 * @Description
 */
public class T4 {
    public static void main(String[] args) {
        float F[][] = new float[6][6];
        double[] calculate = calculate(3);
        System.out.println(Arrays.stream(calculate).iterator());
    }

    /**
     * @param r: a Integer represent radius
     * @return: the circle's circumference nums[0] and area nums[1]
     */
    public static double[] calculate(int r) {
        // write your code here
        double perimeter = r * 2 * 3.14;
        double area = r * r * 3.14;
        double[] ans = new double[]{perimeter, area};
        return ans;
    }
}
