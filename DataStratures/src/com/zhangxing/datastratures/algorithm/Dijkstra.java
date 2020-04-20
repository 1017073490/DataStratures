package com.zhangxing.datastratures.algorithm;

import java.util.Arrays;

/**
 * @author zhangxing
 * @Description: 迪杰斯特拉解决最短路径问题
 * @date 2020/4/19 9:36
 * 解决某一个顶点到其它顶点的最短路径
 */
public class Dijkstra {

    public static void main(String[] args) {
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[7][7];
        final int N = 65535;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.dijkstra(6);
        graph.showRes();
    }

}


class Graph {
    public char[] vertex;
    public int[][] matrix;
    public isVisitedVertex visitedVertex;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void showRes(){
        visitedVertex.showRes();
    }

    public void dijkstra(int index) {
        visitedVertex = new isVisitedVertex(vertex.length, index);
        update(index);   //更新index顶点到周围顶点的距离和前驱结点
        for (int i = 1; i < vertex.length; i++) {
            index = visitedVertex.updateArr();
            update(index);
        }
    }


    private void update(int index) {
        int len = 0;
        //根据遍历我们的邻接矩阵的matrix[index]行
        for (int i = 0; i < matrix[index].length; i++) {
            //len:出发顶点到index的距离 + 从index顶点到j顶点的距离
            len = visitedVertex.getDis(index) + matrix[index][i];
            //如果i顶点没有被访问过，并且len小于出发顶点到i顶点的距离，就需要更新
            if (!visitedVertex.in(i) && len < visitedVertex.getDis(i)) {
                visitedVertex.updateDis(i, len);
                visitedVertex.updatePre(i, index);
            }
        }
    }
}


class isVisitedVertex {
    //记录各个顶点是否访问过，1表示访问过，0表示未访问。动态更新
    public int[] already_arr;
    //每个下标对应的值为前一个顶点下标。动态更新
    public int[] pre_visited;
    //记录出发顶点到其他所有顶点的距离。比如G点为出发顶点，就会记录G点到其他顶点的距离。动态更新
    public int[] dis;

    /**
     * @param length 顶点的个数
     * @param index  出发顶点对应的下标
     */
    public isVisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化dis数组
        Arrays.fill(dis, 65535);
        this.already_arr[index] = 1;
        this.dis[index] = 0;
    }

    /**
     * fun:判断index顶点是否被访问过
     *
     * @param index
     * @return 如果访问过，就返回true,否则false
     */
    public boolean in(int index) {
        return already_arr[index] == 1;

    }

    /**
     * fun:更新出发顶点到index顶点的距离
     *
     * @param index
     * @param len
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * fun:更新pre顶点的前驱为index顶点
     *
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     * fun:返回出发顶点到index顶点的距离
     *
     * @param index
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 继续选择并返回新的访问顶点，比如这个G完成后，就是选择A点作为新的访问顶点（注意：不是出发顶点）
     *
     * @return
     */
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        //更新index顶点被访问过了
        already_arr[index] = 1;
        return index;
    }

    /**
     * 将3个数组的情况输出一下
     */
    public void showRes() {
        System.out.println("=====================");
        for (int i : already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : dis) {
            System.out.print(i + " ");
        }
    }
}