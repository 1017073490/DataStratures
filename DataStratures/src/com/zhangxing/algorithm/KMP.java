package com.zhangxing.algorithm;

import java.util.Arrays;

/**
 * @author zhangxing
 * @Description: 暴力匹配与KMP算法对比
 * @date 2020/4/17 12:18
 */
public class KMP {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = getPartValue("ABCDABD");
        System.out.println(Arrays.toString(next));
        int index = KMP(str1, str2, next);
        System.out.println("index:" + index);

    }

    private static int violenceMatch(String str1, String str2) {
        char[] array1 = str1.toCharArray();
        char[] array2 = str2.toCharArray();
        int arrOfLength1 = array1.length;
        int arrOfLength2 = array2.length;
        int i = 0;
        int j = 0;
        while (i < arrOfLength1 && j < arrOfLength2) {
            //匹配不越界
            if (array1[i] == array2[j]) {
                i++;
                j++;
            } else {
                //如果失配，令i=i-(j-1),j=0;
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == arrOfLength2) {
            return i - j;
        }
        return -1;
    }

    /**
     * @param str1 源字符串
     * @param str2 子串
     * @param next 部分匹配表
     * @return
     */
    private static int KMP(String str1, String str2, int[] next) {
        //需要处理不等的情况

        //遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i + 1 - j;
            }
        }
        return -1;
    }

    private static int[] getPartValue(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //dest.charAt(i) != dest.charAt(j),我们需要从next[j-1]获取新的j
            //***这是KMP核心***
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }
            if (dest.charAt(i) == dest.charAt(j)) {
                //条件满足时，部分匹配值就是+1;
                j++;
            }
            next[i] = j;
        }
        return next;

    }


}
