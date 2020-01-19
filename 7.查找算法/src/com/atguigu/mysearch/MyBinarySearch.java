package com.atguigu.mysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: chenlj
 * @CreateTime: 2020-01-19 10:07
 * @Description: 二分查找
 */
public class MyBinarySearch {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10 , 11, 12, 13,14,15,16,17,18,19,20};
        List<Integer> resIndexList = findByValue(array, 20);
        System.out.println("resIndexList=" + resIndexList);
    }

    private static List<Integer> findByValue(int[] array, int value) {
        List result = new ArrayList<Integer>();
        binarySearch(array, result, 0, array.length - 1, value);
        return result;
    }

    private static void binarySearch(int[] array, List<Integer> result, int start, int end, int value) {
        int middle = (start + end) / 2;
        if (array[middle] == value) {
            int temp = middle;
            while(temp > 0 && array[--temp] == value) {
                result.add(temp);
            }
            result.add(middle);
            temp = middle;
            while(temp < array.length -1 && array[++temp] == value) {
                result.add(temp);
            }
            return;
        }
        if (start - end >= 0) {
            return;
        }
        if (array[middle] > value) {
            binarySearch(array, result, start, middle -1, value);
        } else if (array[middle] < value) {
            binarySearch(array, result, middle + 1, end, value);
        }
    }

}
