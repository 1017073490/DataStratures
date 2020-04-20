package com.zhangxing.datastratures.algorithm;

/**
 * @author zhangxing
 * @Description: 二分查找的非递归实现
 * @date 2020/4/17 8:57
 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 10, 11, 67, 100};
        int index = binarySearch(arr, -8);
        System.out.println("index:" + index);

    }

    private static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            //说明继续查找
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
