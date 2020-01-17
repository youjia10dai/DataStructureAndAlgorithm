package com.atguigu.mysort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @Author: chenlj
 * @CreateTime: 2020-01-16 21:42
 * @Description: 快速排序,参照的原型是  印象笔记 21.快速排序
 */
public class MyQuickSort {

    public static void main(String[] args) {
//        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0, -56, 5, 45, 32, 7, 12,100,523,966,8,6,3,94};
//        printArray(array);
//        quickSort(array, 0 , array.length -1);
//        printArray(array);
        testTime();
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
        quickSort(array, 0 , array.length -1);
//        printArray(array);
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
    }


    /**
     * 快速排序
     * 以基准数为准,小于的放左边,大于的放右边
     * @param array
     */
    private static void quickSort(int[] array, int left, int right) {
        int base = array[left];
        int leftTemp = left + 1;
        int rightTemp = right;
        // 以最左边的数为 基准数
        while(rightTemp > leftTemp) {
            if(array[leftTemp] > base) {
                while(array[rightTemp] > base) {
                    rightTemp--;
                }
                if(rightTemp > leftTemp) {
                    change(array, leftTemp, rightTemp);
                }else {
                    break;
                }
            }
            leftTemp++;
        }
        if(array[leftTemp] > base) {
            leftTemp = leftTemp - 1;
        }
        change(array, leftTemp, left);
//        printArray(array);
        if(leftTemp - 1 - left > 0) {
            quickSort(array, left, leftTemp - 1);
        }
        if(right - (leftTemp + 1) > 0) {
            quickSort(array, leftTemp + 1, right);
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