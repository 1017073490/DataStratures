package com.zhangxing.datastratures.algorithm;

/**
 * @author zhangxing
 * @Description: 动态规划算法的案例
 * @date 2020/4/17 9:40
 */
public class KnapsackProblem {
    /**
     * v[i]、w[i]分别表示第i个物品的价值和重量。
     * v[i][j]表示前i个物品种能够装入容量为j的背包中的最大价值。
     * =================================================
     * v[i][0]=v[0][j]=0  表示表中的第一行第一列都为0
     * w[i]>j：v[i][j]=v[i-1][j]  当准备加入新增的商品的容量大于当前背包的容量时，就使用上一个单元格的策略。
     * j>=w[i]:v[i][j]=max{v[i-1][j],v[i]+v[i-1][j-w[i]]}:
     * 当准备加入的新增的商品的容量小于等于当前背包的容量，
     * v[i-1][j]：上一个单元格装入的最大价值
     * v[i]：当前商品的价值
     * v[i-1][j-w[i]]：装入i-1个商品,到剩余空间j-w[i]的最大值。
     */
    private static int[] Weight = {1, 4, 3};
    private static int[] Value = {1500, 3000, 2000};
    private static int M = 4;
    private static int N = Value.length;
    private static int[][] v = new int[N + 1][M + 1];
    private static int[][] path = new int[N + 1][M + 1];

    public static void main(String[] args) {
        //创建二维数组，表示表示前i个物品种能够装入容量为j的背包中的最大价值
        //初始化首行首列
        init(v);
        dynamicPlanning(v);
        show(v);
        System.out.println("结果:");
        showPath(path);
    }

    private static int[][] init(int[][] v) {
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }
        return v;
    }

    private static void show(int[][] v) {
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void showPath(int[][] path) {
        int i = path.length-1;
        int j = path[0].length-1;
        while (i>0 && j>0){
            if (path[i][j] == 1){
                System.out.printf("商品%d放入背包\n", i);
                j -= Weight[i-1];
            }
            i--;
        }
    }


    /**
     * v[i][0]=v[0][j]=0  表示表中的第一行第一列都为0
     * w[i]>j：v[i][j]=v[i-1][j]  当准备加入新增的商品的容量大于当前背包的容量时，就使用上一个单元格的策略。
     * j>=w[i]:v[i][j]=max{v[i-1][j],v[i]+v[i-1][j-w[i]]}
     */
    private static void dynamicPlanning(int[][] v) {
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[i].length; j++) {
                //不处理首行首列
                if (Weight[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    //v[i][j] = Math.max(v[i - 1][j], Value[i - 1] + v[i - 1][j - Weight[i - 1]]);
                    //为了记录放入情况，不能简单使用上面的公式。
                    if (v[i - 1][j] < Value[i - 1] + v[i - 1][j - Weight[i - 1]]) {
                        v[i][j] = Value[i - 1] + v[i - 1][j - Weight[i - 1]];
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }
    }
}
