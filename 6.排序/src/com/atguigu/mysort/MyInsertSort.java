package com.atguigu.mysort;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author: chenlj
 * @CreateTime: 2020-01-14 16:41
 * @Description: 插入排序
 */
public class MyInsertSort {

    //20
    public static void main(String[] args) {
        int[] array = {3, 9, -1, 10, 20, -96,3,3, 78, 23, 12, 63, 100, -8, -89,2,6,78,65,12,10,23,45,63,78};

        System.out.println("插入排序前");
        printArray(array);
        insertSort(array);
        printArray(array);
    }


    /**
     * 插入排序
     * 数组数据一个一个插入进去
     * @param array
     */
    private static void insertSort(int[] array) {
        int loopCount = array.length;
        for(int i = 1; i < loopCount; i++) {
            int current = array[i];//当前值,添加参数主要是为了好理解
            for(int j = i - 1; j > -1; j--) {
                if(current > array[j]) {
                    // 就在j处插入
                    if(j == i - 1)
                        break;
                    array[j + 1] = current;
                    break;
                }
                array[j + 1] = array[j];
            }
            // 这个判断不要放到上面的for循环中,会做很多次判断,效率低下
            if(current < array[0]) {
                array[0] = current;
            }
        }
    }

    private static void moveArray(int[] array, int start, int end) {
        for(int i = end; i > start ; i--) {
            array[i] = array[i -1];
        }
    }


    private static void printArray(int[] array) {
        String collect = Arrays.stream(array).mapToObj(x -> x + "").collect(Collectors.joining(",", "----", "----"));
        System.out.println(collect);
    }

}
