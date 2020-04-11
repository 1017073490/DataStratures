package com.zhangxing.search;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-8 14:44
 * 线性查找
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] array = {1, 9, 11, -1, 34, 89};
        int objectVal = 11;
        int index = seqSearch(array, objectVal);
        if (index==-1){
            System.out.println("找不到。。。");
        }
        System.out.println("目标值"+objectVal+"在索引"+index+"处。");
    }

    public static int seqSearch(int[] array, int value){
        for (int i = 0; i < array.length; i++) {
            if (value==array[i]){
                return i;
            }
        }
        return -1;
    }
}
