package com.zhangxing.datastratures.sort;

import com.zhangxing.datastratures.util.TimeUtils;

import java.util.Random;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-8 10:09
 * 归并排序：
 * 该算法采用经典的分治策略
 * 将问题分为一些小问题，然后递归求解
 * 而治的阶段就将分阶段得到的答案修补在一起
 * 分而治之。
 * 每次都是合并相邻有序子序列，最后得到完整的有序序列。
 */
public class MergeSort {
    public static void main(String[] args) {
        //int[] array = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] array = new int[8000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Random().nextInt(1000000);
        }
        int[] temp = new int[array.length];
        TimeUtils.getTime();
        mergeSort(array, 0, array.length - 1, temp);
        TimeUtils.getTime();
        //System.out.println(Arrays.toString(array));

    }

    public static void mergeSort(int[] array, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //左递归分解
            mergeSort(array, left, mid, temp);
            //右递归分解
            mergeSort(array, mid + 1, right, temp);
            merge(array, left, mid, right, temp);
        }
    }

    /**
     * @param array 待排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边有序序列的初始索引
     * @param temp  中间值
     */
    public static void merge(int[] array, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        //temp数组的当前索引
        int t = 0;

        //先把左右2边数据按照规则填充到temp中，直到有一边处理完毕就结束
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[t] = array[i];
                t += 1;
                i += 1;
            } else {
                temp[t] = array[j];
                t += 1;
                j += 1;
            }
        }

        //把剩余的一边直接填充到temp中
        while (i <= mid) {
            temp[t] = array[i];
            t += 1;
            i += 1;
        }
        while (j <= right) {
            temp[t] = array[j];
            t += 1;
            j += 1;
        }

        //把temp拷贝到array中
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            array[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }

    }
}
