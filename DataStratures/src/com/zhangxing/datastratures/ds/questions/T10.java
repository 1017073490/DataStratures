package com.zhangxing.datastratures.ds.questions;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangxing
 * @Date 2021/8/30 10:23
 * @Version 1.0
 * @Description 很多时候，临界值，空值，null等都需要特殊考虑：是不是与常规情况不同
 */
public class T10 {
    public static void main(String[] args) {
        List<Integer> list2 = primeFactorization(660);
//        list.stream().forEach(System.out::println);
        String s = "        the sky is blue   ";
//        reverseWords(s);
        List<Integer> list = new ArrayList();
        list.add(0);
        List<Long> longs = productExcludeItself(list);
//        longs.forEach(System.out::println);
//        isPalindrome(111);

        String zhangXing123 = lowercaseToUppercase2("zhangXing123");
        System.out.println(zhangXing123);

        lengthOfLastWord("hello woeld");
        String s1 = capitalizesFirst("hello    woeld  wss  ");
        System.out.println(s1);

    }

    public static List<Integer> primeFactorization(int num) {
        // write your code here
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i * i <= num; i++) {
            while (num % i == 0) {
                list.add(i);
                num = num / i;
            }
        }
        if (num != 1) {
            list.add(num);
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

    public static String reverseWords(String s) {
        // write your code here
        if (s.length() == 0 || s == null) {
            return "";
        }
        StringBuffer res = new StringBuffer();
        String[] split = s.split(" ");
        for (int i = split.length - 1; i >= 0; i--) {
            if (!"".equals(split[i])) {
                // 第一个添加不了空格，因为长度还为0，然后添加单词。
                // 再是""+单词的组合，最后一个肯定不会多空格。
                if (res.length() > 0) {
                    res.append(" ");
                }
                res.append(split[i]);
            }
        }
        System.out.println(res);
        System.out.println(res.length());
        return res.toString();
    }

    public static List<Long> productExcludeItself(List<Integer> nums) {
        // write your code here
        List<Long> res = new ArrayList();
        for (int i = 0; i < nums.size(); i++) {
            long tempRes = 1L;
            for (int j = 0; j < nums.size(); j++) {
                if (i != j) {
                    tempRes = tempRes * nums.get(j);
                }
            }
            res.add(tempRes);
        }
        return res;
    }

    public static boolean isPalindrome(int num) {
        // write your code here
        String value = String.valueOf(num);
        StringBuffer buffer = new StringBuffer(value);
        StringBuffer reverse = buffer.reverse();
        if (reverse.toString().equals(value)) {
            return true;
        } else {
            return false;
        }
//        int i = 0;
//        int j = value.length() - 1;
//        while (i < j) {
//            if (!value.substring(i, i + 1).equals(value.substring(j, j + 1))) {
//                return false;
//            }
//            i++;
//            j--;
//        }
//        return true;
    }

    public static String lowercaseToUppercase2(String str) {
        // write your code here
        char[] charArray = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c >= 97 && c <= 122) {
                charArray[i] -= 32;
            }
        }
        return new String(charArray);
    }

    public static int lengthOfLastWord(String s) {
        // write your code here
        String[] split = s.split(" ");
        int max = 0;
        for (int i = 0; i < split.length; i++) {
            max = split[i].length();
            if (split[i].length() > max) {
                max = split[i].length();
            }
        }
        return max;
    }

    public static String capitalizesFirst(String s) {
        // Write your code here
        char[] charArray = s.toCharArray();
        // 统计字母
        int j = 0;
        if (charArray[0] >= 97 && charArray[0] <= 122) charArray[0] -= 32;
        for (int i = 1; i < s.length(); i++) {
            if (charArray[i] != ' ' && charArray[i - 1] == ' ') {
                if (charArray[i] >= 97 && charArray[i] <= 122) charArray[i] -= 32;
            }
        }
        return new String(charArray);
    }
}
