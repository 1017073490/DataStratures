package com.zhangxing.datastratures.search;

import com.zhangxing.datastratures.sort.ShellSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-8 14:57
 * 二分查找
 */
public class BinarySearch{
    public static void main(String[] args) {
        int[] array = {1, 9, 9, 11, -1, 34, 34, 34, 89};
        ShellSort.shellSortImprove(array);
        System.out.println(Arrays.toString(array));
//        int searchIndex = binarySearch(array, 0, array.length - 1, 34);
//        System.out.println(searchIndex);
        List<Integer> list = binarySearchImprove(array, 0, array.length - 1, 2);
        System.out.println(list);

    }

    /**
     * @param array
     * @param left
     * @param right
     * @param objectVal
     * @return 目标值的下标
     */
    public static int binarySearch(int[] array, int left, int right, int objectVal) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = array[mid];
        if (objectVal > midVal) {
            //右递归
            return binarySearch(array, mid + 1, right, objectVal);
        } else if (objectVal < midVal) {
            //左递归
            return binarySearch(array, left, mid - 1, objectVal);
        } else {
            return mid;
        }
    }

    public static List<Integer> binarySearchImprove(int[] array, int left, int right, int objectVal) {
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = array[mid];
        if (objectVal > midVal) {
            //右递归
            return binarySearchImprove(array, mid + 1, right, objectVal);
        } else if (objectVal < midVal) {
            //左递归
            return binarySearchImprove(array, left, mid - 1, objectVal);
        } else {
            List<Integer> resIndexList = new ArrayList<Integer>();
            //先找左边
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || array[temp] != objectVal) {
                    break;
                }
                //否则temp就放入到resIndexList中
                resIndexList.add(temp);
                temp--;
            }
            resIndexList.add(mid);
            //找右边
            temp = mid + 1;
            while (true) {
                if (temp > array.length - 1 || array[temp] != objectVal) {
                    break;
                }
                //否则temp就放入到resIndexList中
                resIndexList.add(temp);
                temp++;
            }
            return resIndexList;
        }
    }
}
