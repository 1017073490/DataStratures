package com.zhangxing.datastratures.linkedlist;

import java.util.Stack;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-1 16:11
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stringStack = new Stack<String>();
        stringStack.add("zx");
        stringStack.add("gy");
        stringStack.add("zf");
        while (stringStack.size() > 0) {
            System.out.println(stringStack.pop());
        }
    }
}
