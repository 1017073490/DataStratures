package com.zhangxing.datastratures.ds.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author zhangxing
 * @Date 2021/9/1 15:28
 * @Version 1.0
 * @Description
 */

public class Test {
    private static final Integer SPECIAL = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int first = n;
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            while (n % i == 0) {
                list.add(i);
                n = n / i;
            }
        }
        if (n != 1) {
            list.add(n);
        }
        if (first == SPECIAL) {
            System.out.print(first + "=2");
        } else {
            System.out.print(first + "=" + list.get(0));
            for (int i = 1; i < list.size(); i++) {
                System.out.print("*" + list.get(i));
            }
        }
    }
}
