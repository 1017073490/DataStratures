package com.zhangxing.datastratures.ds.offer.D1;

import java.util.*;

/**
 * @Author zhangxing
 * @Date 2021/8/27 22:26
 * @Version 1.0
 * @Description
 */
public class T1 {
    public static void main(String[] args) {
        int[] num = new int[]{1, 2, 3, 4};
        containsDuplicate(num);
        int[] nums1 = new int[]{0, 0}, nums2 = new int[]{1};
        int m = 0, n = 1;
        merge(nums1, m, nums2, n);
    }

    public static boolean containsDuplicate(int[] nums) {
        HashMap map = new HashMap(16);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return true;
            }
            map.put(nums[i], 1);
        }
        return false;
    }

    public static int maxSubArray(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int pre = 0;
        int res = nums[0];
        for (int num : nums) {
            // 之前的最大值加上当前的值大，还是当前的值大。
            pre = Math.max(pre + num, num);
            // 之前的结果与之前的最大值（经过上一行就是当前最大值）比较
            res = Math.max(res, pre);
        }
        return res;
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap map = new HashMap(16);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{i, (int) map.get(nums[i])};
            }
            map.put(target - nums[i], i);
        }
        return nums;
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // nums后面是空的，可以使用逆向双指针
        m -= 1;
        n -= 1;
        int k = nums1.length - 1;
        while (n >= 0) {
            // m存在当nums1[]前m个数为0的情况，n所以可以是负数，这个坑死我了！
            // 针对n>=0，一旦nums2[]结束了循环，那整个就是排好序的了，因为nums1[]剩余的本来就是排序好的
            if (m < 0 || nums2[n] >= nums1[m]) {
                nums1[k--] = nums2[n];
                n--;
            } else {
                nums1[k--] = nums1[m];
                m--;
            }
        }
    }




}
