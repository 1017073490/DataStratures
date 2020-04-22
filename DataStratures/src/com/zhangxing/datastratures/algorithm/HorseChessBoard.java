package com.zhangxing.datastratures.algorithm;

import com.zhangxing.datastratures.sort.Sort;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author zhangxing
 * @Description: 骑士周游回溯算法
 * @date 2020/4/21 9:33
 */
public class HorseChessBoard {
    private static int X;   //列
    private static int Y;   //行
    //创建一个数组，标记棋盘的各个位置是否被访问过
    private static boolean visited[];
    private static boolean finished;


    public static void main(String[] args) {
        //测试骑士周游算法
        X = 8;
        Y = 8;
        int row = 1;
        int column = 1;
        int[][] chessBoard = new int[X][Y];
        visited = new boolean[X * Y];
        long start = System.currentTimeMillis();
        travelChessBoard(chessBoard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end - start));
        //输出棋盘最后的情况
        for (int[] rows : chessBoard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }

    /**
     * @param chessBoard
     * @param row        当前位置的行
     * @param column     当前位置的列
     * @param step
     */
    public static void travelChessBoard(int[][] chessBoard, int row, int column, int step) {
        chessBoard[row][column] = step;
        visited[row * X + column] = true;
        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> nextList = next(new Point(column, row));
        //对nextList进行排序。
        sort(nextList);
        //遍历
        while (!nextList.isEmpty()) {
            Point point = nextList.remove(0);
            //判断该点是否已经访问过
            if (!visited[point.y * X + point.x]) {
                travelChessBoard(chessBoard, point.y, point.x, step + 1);
            }
        }
        if (step < X * Y && !finished) {
            chessBoard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 根据当前的位置计算马儿还能走哪些位置。
     *
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> points = new ArrayList<Point>();
        Point point = new Point();
        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y - 1) >= 0) {
            points.add(new Point(point));
        }
        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y - 2) >= 0) {
            points.add(new Point(point));
        }
        if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y - 2) >= 0) {
            points.add(new Point(point));
        }
        if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y - 1) >= 0) {
            points.add(new Point(point));
        }
        if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y + 1) < Y) {
            points.add(new Point(point));
        }
        if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y + 2) < Y) {
            points.add(new Point(point));
        }
        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y + 2) < Y) {
            points.add(new Point(point));
        }
        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y + 1) < Y) {
            points.add(new Point(point));
        }

        return points;
    }

    public static void sort(ArrayList<Point> points){
        //减少回溯
        Collections.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int size1 = next(o1).size();
                int size2 = next(o2).size();
                if (size1 < size2){
                    return -1;
                }else if (size1 == size2){
                    return 0;
                }else {
                    return 1;
                }

            }
        });
    }
}
