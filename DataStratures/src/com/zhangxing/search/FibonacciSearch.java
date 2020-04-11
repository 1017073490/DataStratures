package com.zhangxing.search;

import com.zhangxing.sort.QuickSort;
import com.zhangxing.sort.ShellSort;

import java.util.Arrays;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-8 16:26
 * 斐波拉契查找算法
 */
public class FibonacciSearch {
    public static int MAX = 20;

    public static void main(String[] args) {
        int[] array = {1, 9, 11, -1, 37, 341, 34, 89};
        ShellSort.shellSortImprove(array);
        System.out.println(Arrays.toString(array));
        System.out.println(fibonacciSearch(array, 371));

    }

    public static int fibonacciSearch(int[] array, int objectVal) {
        int low = 0;
        int high = array.length - 1;
        //k为斐波那契数列中刚好大于n的数所在数组的下标。初始为0
        int k = 0;
        int mid = 0;
        int[] fibonacci = fibonacci();
        while (high > fibonacci[k] - 1) {
            k++;
        }
        //因为fibonacci[k]的值可能大于a的长度，因此我们需要使用Arrays类构造一个新的数组
        //这边不足fibonacci[k]长度的部分会使用0填充，但是我们需要用array最后一个数来填充
        int[] temp = Arrays.copyOf(array, fibonacci[k]);
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = array[high];
        }
        //使用while来循环处理，找到我们的数key
        while (low <= high) {
            mid = low + fibonacci[k - 1] - 1;
            if (objectVal < temp[mid]) {
                //应该继续在数组的前部分查找。
                high = mid - 1;
                k--;
            } else if (objectVal > temp[mid]) {
                //应该继续在数组的后部分查找。
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }

    public static int[] fibonacci() {
        int[] fib = new int[MAX];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }
}
