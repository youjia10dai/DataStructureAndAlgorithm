package com.atguigu.mysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: chenlj
 * @CreateTime: 2020-01-19 13:36
 * @Description: 插值查找算法(在二分查找算法基础上修改)
 * 对于数据量较大，关键字分布比较均匀的查找表来说，采用插值查找, 速度较快
 * 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20
 */
public class MyInsertValueSearch {

    private static int count = 0;

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        List<Integer> resIndexList = findByValue(array, 10);
        System.out.println("resIndexList=" + resIndexList);
    }

    private static List<Integer> findByValue(int[] array, int value) {
        List result = new ArrayList<Integer>();
        binarySearch(array, result, 0, array.length - 1, value);
        return result;
    }

    private static void binarySearch(int[] array, List<Integer> result, int start, int end, int value) {
        System.out.println(++count);
        if (value < array[start] || value > array[end]) {
            return;
        }
        // 公式的推导看笔记
        // 替换公式int mid = low + (high - low) * (key - arr[low]) / (arr[high] - arr[low])  ;/*插值索引*/
        int middle = start + (end - start) * (value - array[start]) / (array[end] - array[start]);
        if (array[middle] == value) {
            int temp = middle;
            while (temp > 0 && array[--temp] == value) {
                result.add(temp);
            }
            result.add(middle);
            temp = middle;
            while (temp < array.length - 1 && array[++temp] == value) {
                result.add(temp);
            }
            return;
        }
        if (start - end >= -1) {
            return;
        }
        if (array[middle] > value) {
            binarySearch(array, result, start, middle - 1, value);
        } else if (array[middle] < value) {
            binarySearch(array, result, middle + 1, end, value);
        }
    }
}
