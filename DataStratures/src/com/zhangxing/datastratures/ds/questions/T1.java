package com.zhangxing.datastratures.ds.questions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author zhangxing
 * @Date 2021/8/22 15:00
 * @Version 1.0
 * @Description
 */
public class T1 {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 18;
        int[] myIndex = twoSum(nums, target);
        Arrays.stream(myIndex).forEach(System.out::println);
    }

    private static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(16);
        for (int i = 0; i < nums.length; i++) {
            int num = target - nums[i];
            if (map.containsKey(num)) {
                return new int[]{map.get(num), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

}
