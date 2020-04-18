package com.zhangxing.algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import com.zhangxing.sort.BubbleSort;

/**
 * @author zhangxing
 * @Description: 克鲁斯卡尔解决公交问题
 * @date 2020/4/18 10:20
 * 基本思想：
 * 按照权值从小到大的顺序选择n-1条边，并保证n-1条边不构成回路.
 * 具体做法：首先构造一个只含n个顶点的森林，然后依权值从小到大从联通网中选择边加入到森林中，并使森林中不产生回路。
 * 直到森林变成一颗最小生成树。
 */
@SuppressWarnings("all")
public class KruskalAlgorithm {
    private int edgeNum;
    private char[] vertexs = new char[7];
    private int[][] matrix = new int[7][7];
    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        KruskalAlgorithm initKruskal = init();
        initKruskal.Kruskal();
    }

    private static KruskalAlgorithm init() {
        int edgeNum = 0;
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = {
                {0, 12, INF, INF, INF, 16, 14},
                {12, 0, 10, INF, INF, 7, INF},
                {INF, 10, 0, 3, 5, 6, INF},
                {INF, INF, 3, 0, 4, INF, INF},
                {INF, INF, 5, 4, 0, 2, 8},
                {16, 7, 6, INF, 2, 0, 9},
                {14, INF, INF, INF, 8, 9, 0}
        };
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
        KruskalAlgorithm kruskal = new KruskalAlgorithm(edgeNum, vertexs, matrix);
        kruskal.print(vertexs, matrix);
        return kruskal;
    }

    public void Kruskal() {
        //表示最后结果数组的索引
        int index = 0;
        //用于保存“已有最小生成树”中的每个顶点在最小生成树中的终点
        int[] ends = new int[edgeNum];
        //创建结果数组，保存最后的最小生成树
        EData[] res = new EData[edgeNum];
        //获取图中初始的所有的边
        EData[] edges = getEdges();
        //首先排序，按照边的大小
        sortEdges(edges);
        //遍历edges。将边添加到最小生成树，同时判断是否形成了回路
        for (int i = 0; i < edges.length; i++) {
            //获取第i条边的2个顶点
            int position_1 = getPosition(edges[i].start);
            int position_2 = getPosition(edges[i].end);
            //获取position_1这个顶点在已有最小生成树中的终点
            int end_1 = getEnd(ends, position_1);
            int end_2 = getEnd(ends, position_2);
            //判断是否构成回路
            if (end_1 != end_2) {
                ends[end_1] = end_2;
                res[index++] = edges[i];
            }
        }
        //输出res输出
        System.out.println("最小生成树：");
        for (int i = 0; i < index; i++) {
            System.out.println(res[i]);
        }


    }

    private KruskalAlgorithm(int edgeNum, char[] vertexs, int[][] matrix) {
        this.edgeNum = edgeNum;
        this.vertexs = vertexs;
        this.matrix = matrix;
    }

    private void print(char[] vertexs, int[][] matrix) {
        System.out.println("邻接矩阵:");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%15d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData temp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = temp;
                }
            }
        }
    }

    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

}


class EData {
    char start;
    char end;
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "<" + start +
                "," + end +
                ">, weight=" + weight +
                '}';
    }
}
