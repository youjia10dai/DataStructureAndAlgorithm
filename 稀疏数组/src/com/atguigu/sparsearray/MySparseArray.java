package com.atguigu.sparsearray;


/**
 * @Author: chenlj
 * @CreateTime: 2019-12-20 17:57
 * @Description: 稀松数组的实现
 */
public class MySparseArray {

    // 数组的默认值为0
    private static final int DEFAULT_VALUE = 0;

    //
    private static final int VALID_COUNT = 0;

    public static void main(String[] args) {
        //初始化二维数组
        int[][] sourceArray = initSourceArray(11, 11);
        //打印二维数组
        printArray(sourceArray);
        //将二维数组转化为稀松数组
        int[][] spaceArray = parseSpaceArray(sourceArray);
        printArray(spaceArray);
        //将稀松数组转换为二维数组
        int[][] targetArray = parseTwoArray(spaceArray);
        printArray(targetArray);
    }

    /**
     * 将稀松数组转换成二维数组
     * @param spaceArray
     * @return
     */
    private static int[][] parseTwoArray(int[][] spaceArray) {
        if(spaceArray == null){
            return null;
        }
        int[][] targetArray = new int[spaceArray[0][0]][spaceArray[0][1]];
        for (int i = 1; i < spaceArray.length; i++) {
            int[] data = spaceArray[i];
            targetArray[data[0]][data[1]] = data[2];
        }
        return targetArray;
    }

    /**
     * 将数组稀松化
     *
     * @param sourceArray
     * @return
     */
    private static int[][] parseSpaceArray(int[][] sourceArray) {
        //获取有效数组数量
        int validCount = getValidCount(sourceArray);
        //初始化稀松数组
        int[][] spaceArray = initSpaceArray(validCount, sourceArray);
        return spaceArray;
    }

    private static int[][] initSpaceArray(int validCount, int[][] sourceArray) {
        if(validCount == VALID_COUNT) {
            return null;
        }
        int row = 0;
        int[][] spaceArray = new int[validCount + 1][3];
        spaceArray[row][0] = sourceArray.length;
        spaceArray[row][1] = sourceArray[0].length;
        spaceArray[row][2] = validCount;
        row++;
        for (int i = 0; i < sourceArray.length; i++) {
            int[] data = sourceArray[i];
            for (int j = 0; j < data.length; j++) {
                int value = data[j];
                if(value != DEFAULT_VALUE) {
                    spaceArray[row][0] = i;
                    spaceArray[row][1] = j;
                    spaceArray[row][2] = value;
                    row++;
                }
            }
        }
        return spaceArray;
    }

    private static int getValidCount(int[][] sourceArray) {
        int sum = 0;
        for (int[] array : sourceArray) {
            for (int data : array) {
                if (data != DEFAULT_VALUE) {
                    sum++;
                }
            }
        }
        return sum;
    }

    private static void printArray(int[][] sourceArray) {
        if (sourceArray == null) {
            return;
        }
        for (int[] array : sourceArray) {
            for (int data : array) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

    /**
     * 初始原数组
     */
    private static int[][] initSourceArray(int row, int col) {
        // 创建一个原始的二维数组 11 * 11
        // 0: 表示没有棋子， 1 表示 黑子 2 表蓝子
        int sourceArray[][] = new int[row][col];
        sourceArray[1][2] = 1;
        sourceArray[6][3] = 2;
        sourceArray[8][10] = 1;
        sourceArray[4][9] = 2;
        sourceArray[3][8] = 1;
        return sourceArray;
    }

}