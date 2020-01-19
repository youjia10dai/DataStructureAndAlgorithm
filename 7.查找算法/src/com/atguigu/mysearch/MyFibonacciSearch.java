package com.atguigu.mysearch;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author: chenlj
 * @CreateTime: 2020-01-19 15:23
 * @Description: 斐波那契(黄金分割法)查找算法
 */
public class MyFibonacciSearch {

    public static int maxSize = 20;

    private static int index = 0;

    public static void main(String[] args) {
        int [] arr = {1,8, 10, 89, 1000,1001, 1234};
        System.out.println("index=" + fibSearch(arr, 1000));// 0
    }

    private static int fibSearch(int[] array, int value) {
        // 1.创建黄金分割数组
        int[] goldArray = getGoldArray();
        printArray(goldArray);

        // 2.将数组的大小扩充到满足黄金分割(将数组填充成最小的满足黄金分割的数组)
        int[] temp = fillArray(array, goldArray);
        printArray(temp);

        int left = 0;//
        int right = array.length -1;

        while(right > left) {
            int middle = left + goldArray[index - 1] - 1;// -1 这里是下标减一
            if(temp[middle] > value) {
                right = middle -1;// 设置右边的边界
                index--;// 获取上一个黄金分割点
            }else  if(temp[middle] < value) {
                left = middle + 1;// 设置左边的边界
                index = index -2;// 设置完边界之后,就变成(5 + 2)
            }else{
                return middle;
            }
        }
        return -1;
    }

    private static int[] fillArray(int[] array, int[] goldArray) {
        int right = array.length - 1;
        while(array.length > goldArray[index]) {
            index++;
        }
        int[] temp = Arrays.copyOf(array, goldArray[index]);
        for(int i = right + 1; i < temp.length; i++) {
            temp[i] = array[right];
        }
        return temp;
    }

    private static int[] getGoldArray() {
        int[] goldArray = new int[maxSize];
        goldArray[0] = 1;
        goldArray[1] = 1;
        int index = 2;
        while(index < maxSize) {
            goldArray[index] = goldArray[index - 1] + goldArray[index - 2];
            index++;
        }
        return goldArray;
    }

    private static void printArray(int[] array) {
        String collect = Arrays.stream(array).mapToObj(x -> x + "").collect(Collectors.joining(",", "----", "----"));
        System.out.println(collect);
    }
}
