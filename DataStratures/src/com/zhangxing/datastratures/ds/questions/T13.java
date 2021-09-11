package com.zhangxing.datastratures.ds.questions;

import java.util.Stack;

/**
 * @Author zhangxing
 * @Date 2021/9/5 8:41
 * @Version 1.0
 * @Description 用栈模拟队列，一个核心就是双栈
 * 2次进出入栈的操作可以实现先进先出
 */
public class T13 {
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(100);
    }
}

class MyQueue {
    Stack<Integer> inStack;
    Stack<Integer> outStack;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        // push进入的是inStack
        inStack.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        // pop前要先检查：
        // 如果outStack中还有元素，需要等它先pop完毕才能加入新值
        // 要将inStack里的元素一次性放入outStack
        if (outStack.isEmpty()) {
            in2out();
        }
        return outStack.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        // pop前要先检查：
        // 如果outStack中还有元素，需要等它先pop完毕才能加入新值
        // 要将inStack里的元素一次性放入outStack
        if (outStack.isEmpty()) {
            in2out();
        }
        return outStack.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return inStack.empty() && outStack.empty();
    }

    private void in2out() {
        while (!inStack.empty()) {
            outStack.push(inStack.pop());
        }
    }
}
