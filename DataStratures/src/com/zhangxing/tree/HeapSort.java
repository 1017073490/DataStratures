package com.zhangxing.tree;

import com.zhangxing.util.TimeUtils;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-11 13:01
 * 首先，将待排序序列构造成一个大顶堆
 * 此时，序列的最大值就是堆顶的根节点
 * 将其与末尾元素交换，此时末尾就是最大值
 * 将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。
 * 如此反复进行，就能得到一个有序序列。
 */
public class HeapSort {
    public static void main(String[] args) {
        //升序要求大顶堆
        int[] arr = {4, 6, 8, 5, 9, 12, 87, -3};
        heapSort(arr);
    }

    public static void heapSort(int[] arr) {
        int temp = 0;
        System.out.println("堆排序：");
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        //
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 将一个数组（二叉树）调整成一个大顶堆
     *
     * @param arr    待调整数组
     * @param i      非叶子结点在数组中的索引
     * @param length 对多少元素进行调整，length逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        //k指向i结点的左子结点
        for (int k = i * 2 + 1; k < length; k = i * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > temp) {
                //子结点大于父结点。就把较大的值赋给当前结点。
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        //for循环结束后，我已经将以i为父结点的树的最大值，放在了最顶上（局部）
        arr[i] = temp;
    }
}
