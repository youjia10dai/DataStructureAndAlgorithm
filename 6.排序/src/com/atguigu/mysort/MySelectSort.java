package com.atguigu.mysort;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author: chenlj
 * @CreateTime: 2020-01-14 16:04
 * @Description: 选择排序
 */
public class MySelectSort {

    public static void main(String[] args) {
        int[] array = {3, 9, -1, 10, 20, -96, 78, 23, 12, 63};
        printArray(array);
        selectSort(array);
        printArray(array);
    }

    /**
     * 每次选择出最小值(一个个选出来,就有序了)
     * 1. 选择排序一共有 数组大小 - 1 轮排序
     * 2. 每1轮排序，又是一个循环, 循环的规则(代码)
     * 2.1先假定当前这个数是最小数
     * 2.2 然后和后面的每个数进行比较，如果发现有比当前数更小的数，就重新确定最小数，并得到下标
     * 2.3 当遍历到数组的最后时，就得到本轮最小数和下标
     *
     * @param array 数组
     */
    private static void selectSort(int[] array) {
        int loopCount = array.length -1;
        for(int i = 0; i < loopCount; i++) {
            int min = array[i];
            int minIndex = i;
            for(int j = i + 1; j < array.length; j++) {
                if(min > array[j]) {
                    min = array[j];
                    minIndex = j;
                }
            }
            if(minIndex != i) {
                change(array, i, minIndex);
            }
        }
    }

    private static void change(int[] array, int first, int second) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    private static void printArray(int[] array) {
        String collect = Arrays.stream(array).mapToObj(x -> x + "").collect(Collectors.joining(",", "----", "----"));
        System.out.println(collect);
    }

}
