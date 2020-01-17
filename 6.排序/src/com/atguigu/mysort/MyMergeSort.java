package com.atguigu.mysort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @Author: chenlj
 * @CreateTime: 2020-01-17 11:40
 * @Description: 归并排序
 */
public class MyMergeSort {

    public static void main(String[] args) {
        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0, -56, 5, 45, 32, 7, 12, 100, 523, 966, 8, 6, 3, 94};
        printArray(array);
        mergeSort(array);
        printArray(array);
//        testTime();
    }

    public static void testTime() {
        int[] array = new int[80000000];
        for (int i = 0; i < 80000000; i++) {
            array[i] = (int) (Math.random() * 80000000); // 生成一个[0, 8000000) 数
        }
        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);
//        printArray(array);
        mergeSort(array);
//        printArray(array);
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
    }

    public static void mergeSort(int[] array) {
        divideIntoGroups(array, 0, array.length - 1, new int[array.length]);
    }

    /**
     * 将数据拆分
     *
     * @param array
     * @param left
     * @param right
     * @param temp
     */
    private static void divideIntoGroups(int[] array, int left, int right, int[] temp) {
        if (right > left) {
            // 获取中间值
            int middle = (right + left) / 2;
            divideIntoGroups(array, left, middle, temp);//存在middle=left
            divideIntoGroups(array, middle + 1, right, temp);//middle + 1=right
            merge(array, left, middle, right, temp);
        }
    }

    /**
     * 将数据合并
     * 通过下标middle分成了 两个 有序数组
     * 将数组的排序放到temp中
     * @param array
     * @param left
     * @param right
     * @param temp
     */
    private static void merge(int[] array, int left, int middle, int right, int[] temp) {
        int tempIndex = left;
        int tempLeft = left;
        int tempRight = middle + 1;
        while (tempLeft <= middle && tempRight <= right) {
            if (array[tempLeft] < array[tempRight]) {
                temp[tempIndex++] = array[tempLeft++];
            } else {
                temp[tempIndex++] = array[tempRight++];
            }
        }
        while (tempLeft <= middle) {
            temp[tempIndex++] = array[tempLeft++];
        }
        while (tempRight <= right) {
            temp[tempIndex++] = array[tempRight++];
        }
        for (int i = left; i <= right; i++) {
            array[i] = temp[i];
        }
    }

    private static void printArray(int[] array) {
        String collect = Arrays.stream(array).mapToObj(x -> x + "").collect(Collectors.joining(",", "----", "----"));
        System.out.println(collect);
    }

    private static void change(int[] array, int first, int second) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

}