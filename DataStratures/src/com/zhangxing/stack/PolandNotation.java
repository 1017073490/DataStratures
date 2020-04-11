package com.zhangxing.stack;

import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-5 20:01
 */
public class PolandNotation {
    private static Pattern pattern = Pattern.compile("[0-9]*");

    public static void main(String[] args) {

//        //先定义一个拟波兰表达式
//        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
//        //现将suffixExpression放到ArrayList中
//        //将ArrayList传递给一个方法，遍历ArrayList配合栈完成计算
//        List<String> rpnList = getListString(suffixExpression);
//        System.out.println(rpnList);
//        int res = calculate(rpnList);
//        System.out.println(res);
        System.out.println("====================================");
        //直接对str进行操作不方便，先将中缀表达式转化成对应的List
        String expression = "13+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的List：" + infixExpressionList);
        //再转化成后缀表达式对应的list
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的List：" + suffixExpressionList);
        System.out.println(calculate(suffixExpressionList));

    }

    /**
     * @param ls 中缀表达式对应的List
     * @return 后缀表达式对应的List
     */
    public static List<String> parseSuffixExpressionList(List<String> ls) {

        //初始化2个栈，但是因为s2这个栈没有POP操作，并且最后还需要逆序输出。
        //符号栈
        Stack<String> s1 = new Stack<String>();
        //存储中间结果
        List<String> s2 = new ArrayList<String>();
        //遍历ls
        for (String s : ls) {
            //如果是一个数，直接加入s2
            if (isNum(s)) {
                //这里有问题，始终进不来这个判断
                //后来发现是在转化list的时候，每个元素前多了个空格。
                s2.add(s);
            } else if ("(".equals(s)) {
                s1.push(s);
            } else if (")".equals(s)) {
                //依次弹出s1中的运算符，直到遇到左括号。此时，丢弃该对括号
                while (!"(".equals(s1.peek())) {
                    s2.add(s1.pop());
                }
                //消除一对括号
                s1.pop();
            } else {
                //当item的优先级小于等于栈顶运算符，将s1栈顶的运算符弹出并加入到s2中，再次进行比较
                //此时，我们缺少一个比较优先级的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(s)) {
                    s2.add(s1.pop());
                }
                s1.push(s);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    /**
     * @param s 中缀表达式
     * @return 对应的List
     */
    public static List<String> toInfixExpressionList(String s) {
        List<String> ls = new ArrayList<String>();
        //用于遍历中缀字符串表达式
        int i = 0;
        //对多位数的拼接
        String str;
        //每遍历一个字符就放到c中
        char c;
        do {
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else {
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    /**
     * 将逆波兰表达式的数据和运算符放到ArrayList中
     *
     * @param suffixExpression
     * @return
     */
    public static List<String> getListString(String suffixExpression) {
        //先进行分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String s : split) {
            list.add(s);
        }
        return list;
    }

    public static boolean isNum(String s) {
        return s.matches("\\d+");
    }

    public static int calculate(List<String> rpnList) {
        //创建栈，只需要一个栈即可
        Stack<String> stack = new Stack<String>();
        //遍历我们得到的list的
        for (String s : rpnList) {
            //先判断是不是数
            if (isNum(s)) {
                stack.push(s);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if ("+".equals(s)) {
                    res = num1 + num2;
                } else if ("-".equals(s)) {
                    res = num1 - num2;
                } else if ("*".equals(s)) {
                    res = num1 * num2;
                } else if ("/".equals(s)) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException();
                }
                stack.push("" + res);
            }

        }
        //最后就是运算结果
        return Integer.parseInt(stack.pop());
    }

}

class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation) {
        int res = 0;
        switch (operation.charAt(0)) {
            case '+':
                res = ADD;
                break;
            case '-':
                res = SUB;
                break;
            case '*':
                res = MUL;
                break;
            case '/':
                res = DIV;
                break;
            default:
                break;
        }
        return res;
    }
}
