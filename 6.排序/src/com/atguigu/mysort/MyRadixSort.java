package com.atguigu.mysort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @Author: chenlj
 * @CreateTime: 2020-01-17 15:17
 * @Description: 基数排序(桶排序)
 */
public class MyRadixSort {

    public static void main(String[] args) {
//        int[] array = {8, 9, 1, 7, 2, 3,15,1};
//        printArray(array);
//        radixSort(array);
//        printArray(array);
        testTime();
    }

    public static void testTime() {
        int[] array = new int[40000000];
        for (int i = 0; i < 40000000; i++) {
            array[i] = (int) (Math.random() * 80000000); // 生成一个[0, 8000000) 数
        }
        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);
//        printArray(array);
        radixSort(array);
//        printArray(array);
        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
    }

    /**
     * 先按个位数比较,再按十位数比较
     * 有是个桶
     * @param array
     */
    private static void radixSort(int[] array) {
        //1.创建桶
        int[][] bucket = new int[10][array.length];
        //2.创建桶的标记,标记每个桶中的数量
        int[] bucketIndex = new int[10];
        //3.数组中最大的位数,默认为1
        int max = array[0];
        for(int i = 0; i < array.length; i++) {
            if(max < array[i]) {
                max = array[i];
            }
        }
        int maxLength = (max + "").length();
        for(int i = 1, n = 1; i <= maxLength; i++, n*=10) {
            for(int j = 0; j < array.length; j++) {
                int index = array[j] / n % 10;
                // 根据桶的下标将数据存储到桶中
                bucket[index][bucketIndex[index]] = array[j];
                bucketIndex[index] = bucketIndex[index] + 1;
            }
            // 将桶中的数据放到放到数组中
            bucketIntoArray(array, bucket, bucketIndex);
        }
    }

    private static void bucketIntoArray(int[] array, int[][] bucket, int[] bucketIndex) {
        int index = 0;
        for(int i = 0; i < bucketIndex.length; i++) {
            int[] buc = bucket[i];
            for(int k = 1; k <= bucketIndex[i]; k++) {
                array[index++] = buc[k -1];
            }
            // 重置桶的下标
            bucketIndex[i] = 0;
        }
    }

    private static void printArray(int[] array) {
        String collect = Arrays.stream(array).mapToObj(x -> x + "").collect(Collectors.joining(",", "----", "----"));
        System.out.println(collect);
    }

}
