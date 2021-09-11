package com.zhangxing.datastratures.ds.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author zhangxing
 * @Date 2021/9/8 19:56
 * @Version 1.0
 * @Description
 */
public class Q1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sOne = scanner.nextLine();
        String sTwo = scanner.nextLine();
        String[] one = sOne.split(" ");
        String[] two = sTwo.split(" ");
        List res = new ArrayList();
        int i = 0, j = 0;
        while (i < one.length && j < two.length) {
            if (Integer.parseInt(one[i]) <= Integer.parseInt(two[j])) {
                res.add(Integer.parseInt(one[i]));
                i++;
            } else {
                res.add(Integer.parseInt(two[j]));
                j++;
            }
        }
        while (i < one.length) {
            res.add(Integer.parseInt(one[i++]));

        }
        while (j < two.length) {
            res.add(Integer.parseInt(two[j++]));
        }
        System.out.println("res:" + res);
        res.stream().forEach(System.out::print);
    }
}
