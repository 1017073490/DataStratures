package com.zhangxing.datastratures.ds.questions;

/**
 * @Author zhangxing
 * @Date 2021/8/25 13:17
 * @Version 1.0
 * @Description
 * @Shop https://leetcode-cn.com/leetbook/detail/top-interview-questions/
 */
public class T6 {
    public static void main(String[] args) {
        int[] nums = {1, 4, 4, 5, 7, 7, 8, 8, 8, 8, 9, 9, 10};
        int target = 1;
        int leftTarget = 7;
        int rightTarget = 8;
        int search = binarySearch(nums, target);
        int leftSearch = leftBinarySearch(nums, leftTarget);
        int rightSearch = rightBinarySearch(nums, rightTarget);
        System.out.println("二分：" + search);
        System.out.println("左侧界定：" + leftSearch);
        System.out.println("右侧界定：" + rightSearch);
    }

    /**
     * @param nums:   The integer array.
     * @param target: Target to find.
     * @return: The first position of target. Position starts from 0.
     * @reference: https://www.cnblogs.com/kyoner/p/11080078.html
     * Tip1: 分析二分查找的一个技巧是不要出现 else，而是把所有情况用 else if 写清楚，这样可以清楚地展现所有细节
     */
    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        // 两端都闭区间 [left, right]
        int right = nums.length - 1;
        // 搜索区间为空的时候应该终止
        // while(left <= right)的终止条件是 left == right + 1，写成区间的形式就是 [right + 1, right]
        // 因为while(left < right)的终止条件是 left == right，写成区间的形式就是 [right, right]
        // 这时候搜索区间非空，还有一个数 2，但此时 while 循环终止了。直接返回 -1 就可能出现错误
        while (left <= right) {
            if (nums.length == 0) {
                return -1;
            }
            // 计算 mid 时需要技巧防止溢出，建议写成: mid = left + (right - left) / 2
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                // 本算法的搜索区间是两端都闭的，即 [left, right]。那么当我们发现索引 mid 不是要找的 target 时，
                // 去搜索 [left, mid - 1] 或者 [mid + 1, right]
                // 因为 mid 已经搜索过，应该从搜索区间中去除。
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    // 左侧边界或右侧边界不建议用线性查找，这个复杂度就上来了。原本O(logN)，现在成了O(N)

    public static int leftBinarySearch(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        // write your code here
        int left = 0;
        // 左闭右开区间 [left, right),因为索引大小为 nums.length 是越界的。
        int right = nums.length;
        // left < right条件对应right = nums.length 左闭右开
        // 终止的条件是 left == right，此时搜索区间 [left, left) 恰巧为空，所以可以正确终止。
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 找到 target 时不要立即返回，而是缩小「搜索区间」的上界 right，在区间 [left, mid) 中继续搜索，
                // 即不断向左收缩，达到锁定左侧边界的目的。
                right = mid;
            } else if (nums[mid] < target) {
                // 因为我们的「搜索区间」是 [left, right) 左闭右开，
                // 所以当 nums[mid] 被检测之后，下一步的搜索区间应该去掉 mid 分割成两个区间，
                // 即 [left, mid) 或 [mid + 1, right)。
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        // target大于所有数
        if (left == nums.length) {
            return -1;
        }
        return nums[left] == target ? left : -1;
    }

    public static int rightBinarySearch(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        // 最后的left就是输出的数
        if (left == 0) {
            return -1;
        }
        return nums[left - 1] == target ? left - 1 : -1;
    }
}
