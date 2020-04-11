package com.zhangxing.sparsearray;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * @author zhangxing
 * @version 1.0
 * @date 2020-3-31 18:30
 */
public class SparseArray {
    public static int NUM = 3;
    public static void main(String[] args) throws Exception {

        //先创建一个原始的二维数组
        //0：表示没有棋子  1：表示黑子  2：表示蓝子
        int[][] chessAry = new int[11][11];
        chessAry[1][2] = 1;
        chessAry[2][3] = 2;
        chessAry[4][5] = 2;
        System.out.println("原始二维数组");
        for (int[] rows : chessAry) {
            for (int line : rows) {
                System.out.printf("%d\t",line);
            }
            System.out.println();
        }

        //二维数组转为稀疏数组
        //1.先遍历获得有效数据
        int sum  = 0;
        for (int i = 0; i < chessAry.length; i++) {
            for (int j = 0; j < chessAry[0].length; j++) {
                if (chessAry[i][j] != 0){
                    sum++;
                }

            }
        }

        //2.创建对应的稀疏数组
        int[][] sparseArr = new int[sum+1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        int count = 0;
        for (int i = 0; i < chessAry.length; i++) {
            for (int j = 0; j < chessAry[0].length; j++) {
                if (chessAry[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessAry[i][j];
                }
            }
        }
        System.out.println();
        System.out.println("稀疏数组为：");
        FileOutputStream fos = new FileOutputStream("test.data");
        OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");

        for (int i = 0; i < sparseArr.length; i++) {
            for (int j = 0; j < NUM; j++) {
                System.out.printf("%d\t",sparseArr[i][j]);
                osw.write(sparseArr[i][j]);
            }
            System.out.println();
        }
        osw.close();
        fos.close();
        //待完成作业。。。。。。。。。。。。。。。。

        //3.稀疏数组恢复为原始二维数组
        int[][] chessAryTwo = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chessAryTwo[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        System.out.println("恢复的原始二维数组：");
        for (int[] rows : chessAryTwo) {
            for (int line : rows) {
                System.out.printf("%d\t",line);
            }
            System.out.println();
        }


    }
}
