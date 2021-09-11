package com.zhangxing.datastratures.ds.offer.sorted;

import java.util.Arrays;

/**
 * @Author zhangxing
 * @Date 2021/9/1 7:29
 * @Version 1.0
 * @Description
 */

@SuppressWarnings("all")
public class Bubble {
    public static void main(String[] args) {
        int[] array = {3, 9, -1, 10, 20, -34, 104, 127, 813, 2};
        int a[] = {123, 3};
        mergeSort(a);
//        quick(array, 0, array.length - 1);
        Arrays.stream(a).forEach(System.out::println);
    }

    public static void bubble(int[] array) {
        // 比较array.length-1次即可
        // 是否是稳定排序？是的，原来相同的2个值之间不会发生换位。
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1; j < array.length - i; j++) {
                // 逆序则交换
                if (array[j] < array[j - 1]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static void insert(int[] array) {
        // 也是比较array.length-1次即可
        // 是否稳定呢？保证大于等于当前值插在后面就可以稳定。
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            // 应该用while循环
            int j = i;
            while (j > 0 && temp < array[j - 1]) {
                // 插入需要比较的次数不定，所以使用while，不符合就退出
                // 前一个值后移
                array[j] = array[j - 1];
                j--;
            }
            // 放入，退出循环证明要么找到下标，要么j循环到第一个位置
            array[j] = temp;
        }
    }

    public static void select(int[] array) {
        // 也是比较array.length-1次即可
        // 是否稳定呢？不稳定：
        // 假如开始前两个数相同，但是后面的最小值会将第一个值交换到后面，导致原来2个相同的数排序交换
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    // 出现更小值，记录下标，用于最后交换
                    minIndex = j;
                }
            }
            // 遍历完毕，交换
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    public static void quick(int[] array, int left, int right) {
        if (left < right) {
            int index = getIndex(array, left, right);
            // 递归执行
            quick(array, left, index - 1);
            quick(array, index + 1, right);
        }
    }

    private static int getIndex(int[] array, int left, int right) {
        // 稳定嘛？不稳定：
        // 与选择类似，一开始有2个相同的数字，但是最后面那个较小数会将第一个数调换到后面
        int temp = array[left];
        while (left < right) {
            // 注意，左右依次执行，下面用while是因为不知道判断次数，一旦满足则出来
            // 右左分别执行一次，继续执行下一次右左寻找，指导i=j找到目标索引。
            // 从右边序列开始，往前循环遍历。遇到小于temp的值就换位，将当前位空出来
            while (left < right && array[right] >= temp) {
                right--;
            }
            array[left] = array[right];
            // 从左边序列开始，往前循环遍历。遇到小于temp的值就换位，将当前位空出来
            while (left < right && array[left] <= temp) {
                left++;
            }
            array[right] = array[left];
        }
        // 遍历完成后，需要将一开始的temp值移动到目标index，
        // 至此，左边的都是小于temp的数，右边的都是大于temp的数
        array[left] = temp;
        return left;
    }

    public static void mergeSort(int[] array) {
        // 在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        // 稳定嘛？稳定：
        // 这种根据值的大小复制到temp数组中，不会产生原有相同数值产生调换顺序的情况
        int[] temp = new int[array.length];
        mergeSort(array, 0, array.length - 1, temp);
    }

    public static void mergeSort(int[] array, int left, int right, int[] temp) {
        if (left < right) {
            // 每次需要mergeSort一次时，就需要一个mid
            int mid = (left + right) / 2;
            mergeSort(array, left, mid, temp);
            mergeSort(array, mid + 1, right, temp);
            // 分为了，需要合并操作了
            merge(array, left, mid, right, temp);
        }
    }

    private static void merge(int[] array, int left, int mid, int right, int[] temp) {
        // 先初始化一些信息，左边数组起始下标，右边数组起始下标，临时数组起始下标
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            // 只要2边都没有到末尾，继续执行
            if (array[i] <= array[j]) {
                temp[t++] = array[i++];
            } else {
                temp[t++] = array[j++];
            }
        }
        // 左边的剩余全部填充
        while (i <= mid) {
            temp[t++] = array[i++];
        }
        // 右边的剩余全部填充
        while (j <= right) {
            temp[t++] = array[j++];
        }
        // 将临时数组赋值到left到right的下标中
        t = 0;
        while (left <= right) {
            array[left++] = temp[t++];
        }
    }


}
