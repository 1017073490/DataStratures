package com.zhangxing.datastratures.algorithm;

import java.util.Arrays;

/**
 * @author zhangxing
 * @Description: 佛洛依德算法解决最短路径问题
 * @date 2020/4/20 9:23
 * 第一轮循环把A作为中间结点的所有情况都进行遍历，就会得到更新距离表和前驱关系表。
 * 以后每轮记录B,C,D...
 * 场景：计算各个顶点到其他顶点的最短距离
 * VS 迪杰斯特拉:
 * 迪杰斯特拉通过选定的被访问结点，求出从出发访问顶点到其他顶点的最短路径。
 * 佛洛依德算法中的每一个顶点都是出发访问顶点，其他的每个点都要被当成被访问顶点，
 * 进而求出每个顶点到其他顶点的距离。
 */
public class FloydAlgorithm {
    private static final int N = 65535;

    public static void main(String[] args) {
        FloydAlgorithm floyd = new FloydAlgorithm();
        fGraph fGraph = floyd.init();
        fGraph.floyd();
        fGraph.show();
    }

    private fGraph init(){
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, 5, 7, N, N, N, 2},
                {5, 0, N, 9, N, N, 3},
                {7, N, 0, N, 8, N, N},
                {N, 9, N, 0, N, 4, N},
                {N, N, 8, N, 0, 5, 4},
                {N, N, N, 4, 5, 0, 6},
                {2, 3, N, N, 4, 6, 0}
        };
        return new fGraph(vertex.length, matrix, vertex);
    }
}


class fGraph {
    private char[] vertex;  //存放顶点的数组
    private int[][] dis;    //保存从各个顶点到其余顶点的距离，最后的结果也保留在该数组
    private int[][] pre;    //保存到达目标顶点的前驱顶点

    /**
     * @param length 大小
     * @param matrix 邻接矩阵
     * @param vertex 顶点数组
     */
    public fGraph(int length, int[][] matrix, char[] vertex) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        //对pre数组进行初始化，存放的是下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    public void show() {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis.length; j++) {
                System.out.print(vertex[pre[i][j]] + " ");
            }
            System.out.println();
            for (int j = 0; j < dis.length; j++) {
                System.out.print("(" + vertex[i] + "->" + vertex[j] + ":" + dis[i][j] + ") ");
            }
            System.out.println();
        }
    }

    public void floyd() {
        int len = 0;
        //对中间结点进行遍历，j就是中间结点的下标
        for (int j = 0; j < dis.length; j++) {
            //从i顶点开始出发[A,B,C,D,E,F,G]
            for (int i = 0; i < dis.length; i++) {
                //到达终点结点
                for (int k = 0; k < dis.length; k++) {
                    len = dis[i][j] + dis[j][k];
                    //=>从i顶点出发，经过j中间结点，到达k顶点距离
                    if (len < dis[i][k]) {
                        //如果小于直连的距离
                        dis[i][k] = len;
                        pre[i][k] = pre[j][k];
                    }
                }
            }
        }
    }
}

