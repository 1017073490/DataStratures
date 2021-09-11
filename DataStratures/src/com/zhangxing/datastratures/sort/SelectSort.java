package com.zhangxing.datastratures.sort;

import com.zhangxing.datastratures.util.TimeUtils;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-7 10:41
 * 选择排序：
 * 第一次从array[0]~array[n-1]中选取最小值，与array[0]交换。
 * 第二次从array[1]~array[n-1]中选取最小值，与array[1]交换。
 * 第三次从array[2]~array[n-1]中选取最小值，与array[2]交换。
 * 。。。
 * 第i次从array[i-1]~array[n-1]中选取最小值，与array[i-1]交换。
 * 。。。
 * 第n-1次从array[n-2]~array[n-1]中选取最小值，与array[n-2]交换。
 * 总共通过n-1次比较，得到一个有序序列。
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] array = {394, -93, 34, 101, 34, 119, 1, -1, 99, 1, 100};
//        int[] array = new int[80000];
//        for (int i = 0; i < array.length; i++) {
//            array[i] = new Random().nextInt(100000);
//        }
        TimeUtils.getTime();
        selectSortCreateByZX(array);
        Arrays.stream(array).forEach(System.out::println);
        TimeUtils.getTime();
    }

    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            int min = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (min > array[j]) {
                    min = array[j];
                    minIndex = j;
                }
            }
            //进行交换
            if (minIndex != i) {
                array[minIndex] = array[i];
                array[i] = min;
            }
        }
        //System.out.println(Arrays.toString(array));
    }

    public static void selectSortCreateByZX(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = array[i];
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    //记录
                    min = array[j];
                    minIndex = j;
                }
            }
            //交换
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }
}
