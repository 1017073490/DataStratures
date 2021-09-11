package com.zhangxing.datastratures.sort;

import com.zhangxing.datastratures.util.TimeUtils;

import java.util.Arrays;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-7 12:12
 * 插入排序：
 * 把n个待排序元素看成一个有序表和一个无序表。
 * 开始有序表只有第一个元素，无序表包含n-1个元素
 * 排序过程中每次从无序表中选择其第一个元素，将它插入到有序表的适当位置。使之成为新的有序表。
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] array = {101, 34, 119, 1, -1, 89, 1, 23, -321};
//        int[] array = new int[80000];
//        for (int i = 0; i < array.length; i++) {
//            array[i] = new Random().nextInt(100000);
//        }
        TimeUtils.getTime();
        insertSortCreateByZX(array);
        Arrays.stream(array).forEach(System.out::println);
        TimeUtils.getTime();
    }

    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int insertVal = array[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < array[insertIndex]) {
                array[insertIndex + 1] = array[insertIndex];
                insertIndex--;
            }
            array[insertIndex + 1] = insertVal;
        }
        //System.out.println(Arrays.toString(array));
    }

    public static void insertSortCreateByZX(int[] array) {
        for (int i = 1; i < array.length; i++) {
            //i仅仅代表排序次数
            int insertIndex = i;
            int insertValue = array[i];
            while (insertIndex > 0 && insertValue < array[insertIndex - 1]) {
                //移动
                array[insertIndex] = array[insertIndex - 1];
                insertIndex--;
            }
            //插入
            array[insertIndex] = insertValue;
        }
    }
}
