package com.zhangxing.datastratures.algorithm;

import java.util.Arrays;

/**
 * @author zhangxing
 * @Description: Prim算法解决修路问题生成图
 * @date 2020/4/17 20:22
 * 最小生成树（MST）
 * 给定一个带权的无向连通图，如何选取一颗生成树，使得树上所有边上权的总和为最小。
 * 主要有：普利姆算法和克鲁斯卡尔算法。
 *
 * 过程：
 */
public class PrimAlgorithm {


    public static void main(String[] args) {
        new PrimAlgorithm().prim();
    }

    public void prim(){
        int MAX = 100;
        char[] data = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int verxs = data.length;
        //描述邻接矩阵
        int[][] weight = {
                {MAX, 5, 7, MAX, MAX, MAX, 2},
                {5, MAX, MAX, 9, MAX, MAX, 3},
                {7, MAX, MAX, MAX, 8, MAX, MAX},
                {MAX, 9, MAX, MAX, MAX, 4, MAX},
                {MAX, MAX, 8, MAX, MAX, 5, 4},
                {MAX, MAX, MAX, 4, 5, MAX, 6},
                {2, 3, MAX, MAX, 4, 6, MAX}
        };
        //创建MGraph
        MGraph mGraph = new MGraph(verxs);
        MinTree minTree = new MinTree();
        minTree.initGraph(mGraph, verxs, data, weight);
        minTree.prim(mGraph,2);
    }
}


@SuppressWarnings("all")
class MinTree {
    /**
     * @param mGraph 图对象
     * @param verxs  图对应的顶点个数
     * @param data   图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void initGraph(MGraph mGraph, int verxs, char[] data, int[][] weight) {
        for (int i = 0; i < verxs; i++) {
            mGraph.data[i] = data[i];
            for (int j = 0; j < verxs; j++) {
                mGraph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph mGraph) {
        for (int[] link : mGraph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * @param mGraph 图
     * @param v      第几个结点开始
     */
    public void prim(MGraph mGraph, int v) {
        //标记结点是否被访问过
        int[] isVisited = new int[mGraph.verxs];
        isVisited[v] = 1;
        //h1和h2记录2个结点的下标
        int h1 = -1;
        int h2 = -1;
        int minWeight = 100;
        //因为有mGraph.verxs个结点，Prim算法结束后产生mGraph.verxs-1条边
        for (int i = 1; i < mGraph.verxs; i++) {

            //这是确定每一次生成的子图，和哪个结点和这次遍历的结点的距离最近
            for (int j = 0; j < mGraph.verxs; j++) {
                for (int k = 0; k < mGraph.verxs; k++) {
                    if (isVisited[j] == 1 &&
                            isVisited[k] == 0 && mGraph.weight[j][k] < minWeight) {
                        //寻找当前遍历的结点与未访问过的结点间的权值最小的边
                        minWeight = mGraph.weight[j][k];
                        h1 = j;
                        h2 = k;
                    }
                }
            }
            System.out.println("边：<" + mGraph.data[h1] + "," + mGraph.data[h2] + "> 权值:" + minWeight);
            isVisited[h2] = 1;
            //重置
            minWeight = 100;
        }
    }
}


@SuppressWarnings("all")
class MGraph {
    int verxs;
    char[] data;
    int[][] weight;

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}
