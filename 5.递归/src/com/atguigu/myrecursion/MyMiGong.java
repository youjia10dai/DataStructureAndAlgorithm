package com.atguigu.myrecursion;

import java.util.Arrays;

/**
 * @Author: chenlj
 * @CreateTime: 2020-01-06 16:31
 * @Description: 使用递归实现迷宫(走出迷宫)
 * 1. map 表示地图
 * 2. i,j 表示从地图的哪个位置开始出发 (1,1)
 * 3. 如果小球能到 map[6][5] 位置，则说明通路找到.
 * 4. 约定： 当map[i][j] 为 0 表示该点没有走过 当为 1 表示墙  ； 2 表示通路可以走 ； 3 表示该点已经走过，但是走不通
 * 5. 在走迷宫时，需要确定一个策略(方法) 下->右->上->左 , 如果该点走不通，再回溯
 */
public class MyMiGong {

    // 设置终点
    private static final int END_ROW = 1;
    private static final int END_COL = 8;

    // 表示路
    public static final int ROUTE = 0;

    // 表示墙
    public static final int WALL = 1;

    // 表示走过了
    public static final int WALK = 2;

    // 表示错误
    public static final int ERROR = 3;


    // 先创建一个二维数组，模拟迷宫
    // 地图
    public static int[][] map = new int[8][10];

    public static void main(String[] args) {
        // 初始化迷宫
        initMap();
        // 画出迷宫
        showMap();
        // 走迷宫
        boolean isOut = setWay(1, 1);
        System.out.println("========================进行走迷宫========================");
        // 画出迷宫
        showMap();
        System.out.println("用户是否做出迷宫:" + isOut);
    }

    /**
     * 在迷宫中,每个格子只能走一遍,所以只会走向值为0的格子
     * 只有走到终点才会一路返回true,其他的都返回false
     * 走迷宫的模式 下右上左
     * @param row
     * @param col
     */
    private static boolean setWay(int row, int col) {
        if (map[row][col] != 0) {
            return false;
        }
        // 走出一步
        map[row][col] = WALK;
        // 判断是否走到终点
        if (map[END_ROW][END_COL] == WALK) {
            // 递归调用的所有方法,到了这里都会返回true
            return true;
        }
        // 走四个方向
        if (setWay(row + 1, col)) {
            System.out.println("往下走");
            return true;
        } else if(setWay(row, col + 1)) {
            System.out.println("往右走");
            return true;
        }else if(setWay(row + 1, col)) {
            System.out.println("往上走");
            return true;
        } else if(setWay(row, col - 1)) {
            System.out.println("往左走");
            return true;
        }
        map[row][col] = ERROR;
        return false;
    }

    public static void initMap() {
        // 四周画上墙
        setRow(0);
        setRow(map.length - 1);
        setCol(0);
        setCol(2);
        setCol(map[0].length - 1);
        // 初始化障碍墙
        setRow(3);
        setRow(5);
        map[3][8] = ROUTE;
        map[5][1] = ROUTE;
        map[1][2] = ROUTE;
        map[3][1] = ROUTE;
        map[5][3] = ROUTE;
    }

    public static void setRow(int index) {
        int[] row = map[index];
        for (int i = 0; i < row.length; i++) {
            row[i] = WALL;
        }
    }

    public static void setCol(int index) {
        for (int i = 0; i < map.length; i++) {
            int[] row = map[i];
            row[index] = WALL;
        }
    }

    public static void showMap() {
        for (int i = 0; i < map.length; i++) {
            int[] row = map[i];
            for (int col : row) {
                System.out.print(col + "  ");
            }
            System.out.println();
        }
    }

}