package com.zhangxing.search;

import com.zhangxing.sort.ShellSort;

import java.util.Arrays;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-8 15:55
 * 插值查找
 * 适用于数据量较大，且元素值分布比较均匀。
 */
public class InsertSearch {
    public static void main(String[] args) {
        int[] array = {1, 9, 11, -1, 37, 341, 34, 89};
        ShellSort.shellSortImprove(array);
        System.out.println(Arrays.toString(array));
        int searchIndex = insertSearch(array, 0, array.length - 1, 341);
        System.out.println(searchIndex);
    }

    public static int insertSearch(int[] array, int left, int right, int objectVal) {
        System.out.println("次数。。。");
        if (left > right || objectVal < array[0] || objectVal > array[array.length - 1]) {
            return -1;
        }
        int mid = left + (right - left) * (objectVal - array[left]) / (array[right] - array[left]);
        int midVal = array[mid];
        if (midVal > objectVal) {
            //左递归
            return insertSearch(array, left, mid - 1, objectVal);
        } else if (midVal < objectVal) {
            //右递归
            return insertSearch(array, mid + 1, right, objectVal);
        } else {
            return mid;
        }

    }

}
