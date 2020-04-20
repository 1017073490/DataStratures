package com.zhangxing.datastratures.recursion;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-4-6 15:42
 */
public class LabyrinthBacktracking {
    public static int[][] map = new int[8][7];
    public static void main(String[] args) {
        //创建二维数组模拟迷宫

        //使用1表示墙。
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int j = 0; j < map.length; j++) {
            map[j][0] = 1;
            map[j][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        //输出地图
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("使用递归回溯找终点=====================================");
        findWay(map, 1, 1);
        showMap();
    }

    public static void showMap(){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 说明：(6,5)-->map[6][5]代表终点
     * 约定：map[i][j]=0表示没有走过
     * map[i][j]=1表示不能走
     * map[i][j]=2表示可以走
     * map[i][j]=3表示可走过但是走不通
     * 策略：->下->右->上->左
     *
     * @param map 表示地图
     * @param i   i+j组成起始点
     * @param j
     * @return 找到返回true
     */
    public static boolean findWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            //如果这个点没有走过，先走试试看
            if (map[i][j] == 0) {
                //假定可以走
                map[i][j] = 2;
                showMap();
                System.out.println("=====================================");
                if (findWay(map, i + 1, j)) {
                    return true;
                } else if (findWay(map, i, j + 1)) {
                    return true;
                } else if (findWay(map, i - 1, j)) {
                    return true;
                } else if (findWay(map, i, j - 1)) {
                    return true;
                } else {
                    //代表次点是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                //map[i][j] ！= 0。有几种情况
                return false;
            }
        }
    }
}
