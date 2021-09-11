package com.zhangxing.datastratures.sort;

import com.zhangxing.datastratures.util.TimeUtils;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-7 15:44
 * 快速排序：
 * 通过一趟排序将要排序的数据分割为独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这2部分数据分别进行快速排序。
 * 整个排序过程可以递归进行，以此变成有序序列。
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] array = {-9, 78, 0, 23, -576, 58, 27, -32};
//        int[] array = new int[8000000];
//        for (int i = 0; i < array.length; i++) {
//            array[i] = new Random().nextInt(100000);
//        }
        TimeUtils.getTime();
        quickSortByZX(array, 0, array.length - 1);
        Arrays.stream(array).forEach(System.out::println);
        TimeUtils.getTime();
//        System.out.println(Arrays.toString(array));


    }


    public static void quickSort2(int[] array, int left, int right) {
        int l = left;
        int r = right;
        int pivot = array[left];
        while (l < r) {
            while (l < r && array[r] >= pivot) {
                r--;
            }
            if (l < r) {
                array[l] = array[r];
            }

            while (l < r && array[l] <= pivot) {
                l++;
            }
            if (l < r) {
                array[r] = array[l];
            }


            if (l == r) {
                array[l] = pivot;
            }
        }
        //左递归
        if (left < (r - 1)) {
            quickSort2(array, left, r - 1);
        }
        if (right > (l + 1)) {
            quickSort2(array, l + 1, right);
        }
    }

    public static void quickSortByZX(int[] array, int left, int right) {
        int pivot = array[left];
        int l = left;
        int r = right;
        while (l < r) {
            //直到l=r
            //右边
            while (l < r && array[r] >= pivot) {
                r--;
            }
            if (l < r) {
                array[l] = array[r];
            }
            //左边
            while (l < r && array[l] <= pivot) {
                l++;
            }
            if (l < r) {
                array[r] = array[l];
            }
            if (l == r) {
                array[l] = pivot;
            }
        }

        if (left < r - 1) {
            quickSortByZX(array, left, r - 1);
        }
        //递归
        if (l + 1 < right) {
            quickSortByZX(array, l + 1, right);
        }


    }

}
