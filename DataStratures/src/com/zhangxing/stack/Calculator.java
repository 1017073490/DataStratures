package com.zhangxing.stack;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-5 11:11
 */
public class Calculator {
    public static void main(String[] args) {
        //表达式。此代码暂时还有BUG 2020-04-05
        String expression = "3+2*9-6-3+2*9-6";
        System.out.println(expression);
        //创建2个栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义相关需要的变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        char oper = ' ';
        int result = 0;
        char ch = ' ';
        String keepNum = "";
        while (true) {
            //第一次得到expression中每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch的情况
            if (operStack.isOper(ch)) {
                //判断当前符号栈是否为空
                if (!operStack.isEmpty()) {
                    //如果符号栈有操作符，就进行比较，如果当前操作符的优先级小于或者等于栈中的操作符
                    //就需要从数栈中pop出2个数，再从符号栈中pop出一个符号
                    //进行运算，得到结果，入数栈。再将当前的操作符入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = (char) operStack.pop();
                        result = numStack.calculate(num1, num2, oper);
                        numStack.push(result);
                        operStack.push(ch);
                    } else {
                        //否则直接入符号栈
                        operStack.push(ch);
                    }
                } else {
                    operStack.push(ch);
                }
            } else {
                //如果是数直接入栈,注意ASCII值
                keepNum += ch;
                //如果ch已经是expression的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        //清空keepNum
                        keepNum = "";
                    }
                }
                //如果后一位是运算法就入栈

            }
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        //表达式扫描完毕
        while (true) {
            //如果符号栈为空，则计算到最后的结果。数字栈中最后的数就是结果
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = (char) operStack.pop();
            result = numStack.calculate(num1, num2, oper);
            numStack.push(result);
        }
        System.out.println("结果是:" + numStack.pop());

    }
}


class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int peek() {
        return stack[top];
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("stack is full...");
            return;

        }
        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            System.out.println("stack is empty...");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void showStack() {
        if (isEmpty()) {
            System.out.println("stack is empty...");
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

    public int priority(int oper) {
        //java中，int和char是可以混用的
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    public int calculate(int num1, int num2, char oper) {
        int result = 0;
        switch (oper) {
            case '+':
                result = num2 + num1;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num2 * num1;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }
}
