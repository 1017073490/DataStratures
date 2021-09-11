package com.zhangxing.datastratures.ds.questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author zhangxing
 * @Date 2021/8/27 7:34
 * @Version 1.0
 * @Description
 */
public class T9 {
    public static void main(String[] args) {
        T9 t = new T9();
        int[] sorted = new int[]{1, 3, 2, 4};
//        sortIntegersByInsert(sorted);
//        Arrays.stream(sorted).forEach(System.out::println);
        List<Integer> prime = prime(100);
//        prime.stream().forEach(System.out::println);
        System.out.println(t.secondMax(sorted));
    }

    public static void sortIntegersBySelect(int[] A) {
        // write your code here

        for (int i = 0; i < A.length; i++) {
            int minIndex = i;
            for (int j = i; j < A.length; j++) {
                if (A[j] < A[minIndex]) {
                    minIndex = j;
                }
            }
            // 选择排序的关键：找出最小值的那个下标，然后第一个数和它交换
            // 然后依次交换第二个，第三个。。。
            int temp = A[minIndex];
            A[minIndex] = A[i];
            A[i] = temp;
        }
    }

    public static void sortIntegersByInsert(int[] A) {
        // write your code here
        // 第一个无需比较
        for (int i = 1; i < A.length; i++) {
            int temp = A[i];
            int j = i - 1;
            while (j >= 0 && A[j] > temp) {
                // 插入的关键
                // 只要这个数仍大于需要插入的数，就把这个数后移。要为目标位置空出一个数
                A[j + 1] = A[j];
                j--;
            }
            // 找到这个位置，因为之前j--，所以需要插入到A[j + 1]中
            A[j + 1] = temp;
        }
    }

    public static void sortIntegersByBubble(int[] A) {
        // write your code here
        // i的最大值代表次数
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = 1; j < (A.length - i); j++) {
                if (A[j - 1] > A[j]) {
                    // 冒泡的关键，每次对比相邻的2个数，大的数后移
                    int temp = A[j];
                    A[j] = A[j - 1];
                    A[j - 1] = temp;
                }
            }
        }
    }

    public static List<Integer> prime(int n) {
        // write your code here
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                list.add(i);
            }
        }
        return list;
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int secondMax(int[] nums) {
        // write your code here
        // write your code here
        int max = Math.max(nums[0], nums[1]);
        int second = Math.min(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            // 关键在于维护最大数与次大数
            if (nums[i] > max) {
                // 最大数变了，那么原来的最大数就变成次大数
                second = max;
                max = nums[i];
            } else if (nums[i] > second) {
                // 上面和这里只能满足一个
                // 所以这里用else if
                second = nums[i];
            }
        }
        return second;
    }

    public static int majorityNumber(List<Integer> nums) {
        // write your code here
        // 用一个map来整理出现的值和次数，最后返回某个键的值大于nums.size()/2即可
        HashMap<Integer, Integer> map = new HashMap(16);
        for (int i = 0; i < nums.size(); i++) {
            int temp = nums.get(i);
            if (map.containsKey(nums.get(i))) {
                Integer o = map.get(temp);
                map.put(temp, ++o);
            } else {
                map.put(temp, 1);
            }
        }
        for (Integer val : map.keySet()) {
            // map.keySet()返回此映射中所包含的键的 set
            // 可以根据其循环遍历键或值
            if (map.get(val) > nums.size() / 2) {
                return val;
            }
        }
        return 0;
    }

    public static List<List<Integer>> calcYangHuisTriangle(int n) {
        // write your code here
        if (n == 0) {
            return null;
        }
        List<List<Integer>> res = new LinkedList<>();
        // 定义List<>以添加数字
        List<Integer> temp = new LinkedList<>();
        temp.add(1);
        res.add(temp);
        for (int i = 1; i < n; i++) {
            List<Integer> temp2 = new LinkedList<>();
            temp2.add(1);
            for (int j = 1; j < i; j++) {
                // 只对非开头末尾遍历，分别为上一行前一列->(i-1,j-1)与上一行当前列(i-1,j)
                int sum = res.get(i - 1).get(j - 1) + res.get(i - 1).get(j);
                temp2.add(sum);
            }
            temp2.add(1);
            res.add(temp2);
        }
        return res;
    }

    public static int[] rotate(int[] nums, int k) {
        // Write your code here
        int[] temp = new int[k];
        int acc = k % nums.length;
        for (int i = 0; i < acc; i++) {
            temp[i] = nums[(nums.length - acc) + i];
        }
        for (int i = nums.length - acc - 1; i >= 0; i--) {
            nums[i + acc] = nums[i];
        }
        for (int i = 0; i < acc; i++) {
            nums[i] = temp[i];
        }
        return nums;
    }

    public static void reverseArray(int[] nums) {
        // write your code here
//        if (nums.length != 0) {
//            StringBuffer buffer = new StringBuffer();
//            for (int num : nums) {
//                buffer.append(num);
//                buffer.append(",");
//            }
//            String newBuffer = buffer.substring(0, buffer.length() - 1);
//            String[] split = newBuffer.split(",");
//            for (int i = 0; i < nums.length; i++) {
//                nums[i] = Integer.parseInt(split[nums.length - 1 - i]);
//            }
//        }
        int i = 0;
        int j = nums.length - 1;
        int temp = 0;
        while (i < j) {
            temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i += 1;
            j -= 1;
        }
    }

}
