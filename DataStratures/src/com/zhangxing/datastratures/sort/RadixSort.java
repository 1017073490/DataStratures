package com.zhangxing.datastratures.sort;

import com.zhangxing.datastratures.util.TimeUtils;

import java.util.Random;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-8 11:00
 * 基数排序法
 * 空间换时间的经典算法
 * 将整数按位数切割成不同的数字，然后按每个位数分别比较。
 * 将所有待比较数值统一为同样的位数长度，位数短的前面补0。
 * 然后，从最低位开始，以此进行一次排序。
 * 这样从最低位排序一直到最高位排序完以后，数列就变成了有序数列。
 */
public class RadixSort {
    //static int[] array = {53, 3, 542, 748, 14, 214, 100, 1227, 421};

    public static void main(String[] args) {
        int[] array = new int[8000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Random().nextInt(1000000);
        }
        TimeUtils.getTime();
        radixSort(array);
        TimeUtils.getTime();
    }

    public static void radixSort(int[] array) {
        //定义一个二维数组，每个桶就是一个一维数组
        int[][] bucket = new int[10][array.length];
        //此外，为了记录每个桶中实际存放了多少个数据，需定义一个一维数组来记录各个桶每次放入的数据个数
        int[] bucketElementCounts = new int[10];
        //得到最大数的位数
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        int maxLength = (max + "").length();

        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //每一轮开始。针对每个元素的个位进行排序处理
            for (int j = 0; j < array.length; j++) {
                //取出个位、十位、百位。。。
                int digitOfElement = array[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = array[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序（一维数组的下标依次取出数据）
            int index = 0;
            //遍历每一个桶
            for (int k = 0; k < bucket.length; k++) {
                //有数据才放入到原数组
                if (bucketElementCounts[k] != 0) {
                    //这样循环输出
                    for (int m = 0; m < bucketElementCounts[k]; m++) {
                        array[index] = bucket[k][m];
                        index++;
                    }
                }
                //每一轮结束后，需要将每个bucketElementCounts[k]置为0
                bucketElementCounts[k] = 0;
            }
            //System.out.println(Arrays.toString(array));
        }


    }
}
