package com.zhangxing.datastratures.ds.questions;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangxing
 * @Date 2021/8/26 11:24
 * @Version 1.0
 * @Description
 */
public class T8 {
    public static void main(String[] args) {
        List<String> list = fizzBuzz(20);
        System.out.println(Math.pow(10, 3));
        int[] res = {8, 9, 9, 9};
        int[] ints = plusOne(res);
//        Arrays.stream(ints).forEach(System.out::println);
        boolean palindrome = isPalindrome(15);
        System.out.println(palindrome);
    }

    public static float maxOfArray(float[] A) {
        // write your code here
        float max = A[0];
        for (int i = 1; i < A.length; i++) {
            if (A[i] > max) {
                max = A[i];
            }
        }
        return max;
    }

    public List<Integer> generate(int size) {
        // write your code here
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            res.add(i + 1);
        }
        return res;
    }

    public void moveZeroes(int[] nums) {
        // write your code here
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        for (; j < nums.length; ) {
            nums[j++] = 0;
        }
    }

    public int maxNum(List<Integer> nums) {
        // write your code here
        int max = nums.get(0);
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) > max) {
                max = nums.get(i);
            }
        }
        return max;
    }

    public void swapIntegers(int[] A, int index1, int index2) {
        // write your code here
        int temp = A[index1];
        A[index1] = A[index2];
        A[index2] = temp;
    }

    public static List<String> fizzBuzz(int n) {
        // write your code here
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                list.add("fizz buzz");
            } else if (i % 3 == 0) {
                list.add("fizz");
            } else if (i % 5 == 0) {
                list.add("buzz");
            } else {
                list.add("" + i);
            }
        }
        return list;
    }

    public int getAnswer(int num) {
        // write your code here.
        int frequency = 0;
        while (num != 1) {
            if (num % 2 == 0) {
                num /= 2;
            } else {
                num = 3 * num + 1;
            }
            frequency++;
        }
        return frequency;
    }

    public static int[] plusOne(int[] digits) {
        // write your code here
        int flag = 0;
        // 先把加一操作执行完毕
        digits[digits.length - 1] += 1;
        int[] arr = new int[digits.length + 1];
        for (int i = digits.length - 1; i > 0; i--) {
            if (digits[i] == 10) {
                digits[i] = 0;
                digits[i - 1] += 1;
            }
        }
        if (digits[0] == 10) {
            arr[0] = 1;
            for (int i = 1; i < arr.length - 1; i++) {
                arr[i] = 0;
            }
            return arr;
        } else {
            return digits;
        }
    }

    public static boolean isPalindrome(int n) {
        // Write your code here
        StringBuffer binaryString = new StringBuffer();
        while (n / 2 != 0) {
            // 常规除2求余的方法计算二进制
            binaryString.append(n % 2);
            n /= 2;
        }
        // 按理说，这个binaryString反转过来才是正确的二进制的形式
        // 但是是判断是否回文，正反一样。
        binaryString.append(n);
        for (int i = 0, j = binaryString.length() - 1; i < j; i++, j--) {
            // 不管奇数还是偶数个，这个i<j时都是最后一次判断
            // 比如0，1，2，3，4。中间一个数不需要判断
            // 0,1,2,3,4,5时，判断到2和3相等之后结束
            if (binaryString.charAt(i) != binaryString.charAt(j)) {
                return false;
            }
        }
        return true;
    }


}
