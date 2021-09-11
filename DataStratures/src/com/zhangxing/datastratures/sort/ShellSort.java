package com.zhangxing.datastratures.sort;

import com.zhangxing.datastratures.util.TimeUtils;

import java.util.Random;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-7 12:52
 * 希尔排序：希尔排序的实质就是分组的插入排序
 * 它是把记录按下标的一定增量分组，对每组使用直接插入排序进行排序。
 * 随着增量的减少，每组包含的关键词越来越多，当增量减少到1时，整个待排序序列就被分为一组，算法终止。
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        int[] array = new int[8000000];
//        for (int i = 0; i < array.length; i++) {
//            array[i] = new Random().nextInt(100000);
//        }
        TimeUtils.getTime();
        shellSortImprove(array);
        TimeUtils.getTime();
    }

    public static void shellSort(int[] array) {
        //shell排序的第一轮
        int temp = 0;
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            //共进行多少次排序
            for (int i = gap; i < array.length; i++) {
                //遍历各组中所有的元素，共gap组
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (array[j] > array[j + gap]) {
                        //如果当前元素大于步长相加后的元素，说明交换
                        temp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = temp;
                    }
                }
            }
        }
        //System.out.println(Arrays.toString(array));
    }

    /**
     * 对交换式的希尔排序进行优化->移位法
     */
    public static void shellSortImprove(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            //共进行多少次排序
            for (int i = gap; i < array.length; i++) {
                //从第gap元素开始，逐个对其所在的组进行直接插入排序
                int index = i;
                int temp = array[index];
                if (array[index] < array[index - gap]) {
                    while (index - gap >= 0 && temp < array[index - gap]) {
                        array[index] = array[index - gap];
                        index -= gap;
                    }
                    //退出这个while循环就找到了插入的位置
                    array[index] = temp;
                }
            }
        }
        //System.out.println(Arrays.toString(array));
    }
}
