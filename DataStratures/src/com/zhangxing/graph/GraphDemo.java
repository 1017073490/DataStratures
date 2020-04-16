package com.zhangxing.graph;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author zhangxing
 * @Description: 图的创建和实现
 * @date 2020/4/16 10:19
 * 深度优先：
 * <p>
 * 广度优先：
 * 1.访问初始结点v并标记结点v为已访问
 * 2.结点v入队列
 * 3.当队列非空时，继续执行。否则结束
 * 4.出队列，取得对头结点u
 * 5.查找结点u的下一个邻接结点w
 * 6.若w不存在，则返回3.否则循环执行下面操作
 * 6-1.若w尚未被访问。则访问结点w并标记为已访问
 * 6-2.结点w入队
 * 6-3.查找结点u的继w邻接结点后的下一个邻接结点w.转6
 */
public class GraphDemo {
    private ArrayList<String> vertexList;
    private int[][] edges;
    private int numOfEdges;
    //定义Boolean数组，记录某个结点是否被访问过
    private boolean[] isVisited;


    public static void main(String[] args) {
        String vertexValue[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
        GraphDemo graphDemo = new GraphDemo(vertexValue.length);
        for (String vertex : vertexValue) {
            graphDemo.insertVertex(vertex);
        }
        graphDemo.addEdge(0, 1, 1);
        graphDemo.addEdge(0, 2, 1);
        graphDemo.addEdge(1, 3, 1);
        graphDemo.addEdge(1, 4, 1);
        graphDemo.addEdge(3, 7, 1);
        graphDemo.addEdge(4, 7, 1);
        graphDemo.addEdge(2, 5, 1);
        graphDemo.addEdge(2, 6, 1);
        graphDemo.addEdge(5, 6, 1);
        graphDemo.showGraph();
        graphDemo.bfs();


    }

    public GraphDemo(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    public void bfs(){
        //遍历所有的结点进行bfs
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    private void bfs(boolean[] isVisited, int i) {
        //队列头结点的对应下标
        int u;
        //邻接结点的下标
        int w;
        //队列，记录结点访问的顺序
        LinkedList<Object> queue = new LinkedList<Object>();
        //访问结点
        System.out.print(getValByIndex(i) + "->");
        isVisited[i] = true;
        //结点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            //取出队列的头结点的下标
            u = (Integer) queue.removeFirst();
            //得到第一个邻接结点的下标w
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValByIndex(w) + "->");
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                //以u为前驱结点找到w后面的下一个邻接结点。体现出了广度优先
                w = getNextNeighbor(u, w);
            }
        }
    }

    /**
     * 对dfs进行重载，遍历我们所有的结点，并进行dfs
     */
    public void dfs() {
        //遍历所有的结点进行dfs
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * @param isVisited
     * @param i
     */
    private void dfs(boolean[] isVisited, int i) {
        //首先访问该结点
        System.out.print(getValByIndex(i) + "->");
        //将这个结点设置为已经访问过
        isVisited[i] = true;
        //查找结点i的第一个邻接结点
        int w = getFirstNeighbor(i);
        //
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 得到第一个邻接结点的下标
     *
     * @param index
     * @return 存在就返回对应的下标
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接结点的下标来获取下一个邻接结点
     *
     * @param v1
     * @param v2
     * @return
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    public int getNumOfVertex() {
        return vertexList.size();
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    public String getValByIndex(int i) {
        return vertexList.get(i);
    }

    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * @param v1     表示第一个点的下标
     * @param v2     表示第二个点的下标
     * @param weight
     */
    public void addEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }


}
