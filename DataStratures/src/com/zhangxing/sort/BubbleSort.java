package com.zhangxing.sort;

import com.zhangxing.util.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-7 9:49
 * 冒泡排序：
 * 通过对待排序序列从前向后（下标较小的元素开始），依次比较相邻元素的值
 * 若发现逆序则交换，使得值较大的元素逐渐从前向后移动。
 * 就像水底下的气泡一样逐渐向上冒。
 */
public class BubbleSort {
    public static void main(String[] args) {
        //int[] array = {3, 9, -1, 10, 20};
        int[] array = new int[80000];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Random().nextInt(100000);
        }
        TimeUtils.getTime();
        bubbleSort(array);
        TimeUtils.getTime();

    }

    public static void bubbleSort(int[] array) {
        int temp = 0;
        boolean flag = false;
        //冒泡排序，O(n^2)
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    flag = true;
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false;
            }
        }
    }
}
