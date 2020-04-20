package com.zhangxing.datastratures.recursion;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-6 19:51
 */
public class Queue8 {
    static int MAX = 8;
    static int count = 0;
    static int[] QueueArray = new int[MAX];

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println(count);


    }

    /**
     * 放置皇后
     */
    private void check(int n) {
        if (n == MAX) {
            print();
            System.out.println("上面是对的");
            System.out.println("************");
            return;
        }
        //依次放入皇后，并判断有无冲突
        for (int i = 0; i < MAX; i++) {
            //先把皇后放到第一列
            QueueArray[n] = i;
            //判断有误冲突
            if (judge(n)) {
                print();
                System.out.println("===============");
                //接着放n+1个皇后
                check(n + 1);
            }
            //如果冲突，就继续执行QueueArray[n] = i，此时已经经过i++了。就是放在后移的一个位置了。
        }

    }

    /**
     * 查看当前摆放的皇后是否与前面已经摆放的皇后冲突
     */
    private boolean judge(int n) {
        //循环判断与之前n个是否在同一列或者同一斜线
        for (int i = 0; i < n; i++) {
            if (QueueArray[i] == QueueArray[n] || Math.abs(n - i) == Math.abs(QueueArray[n] - QueueArray[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 写一个方法,将皇后摆放的位置打印出来
     */
    private void print() {
        for (int i = 0; i < QueueArray.length; i++) {
            System.out.print(QueueArray[i] + " ");
        }
        count++;
        System.out.println();
    }
}
