package com.atguigu.mysort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @Author: chenlj
 * @CreateTime: 2020-01-16 11:17
 * @Description: 希尔排序
 */
public class MyShellSort {

    public static void main(String[] args) {// 15  15  14   14  15
//        int[] array = new int[40000000];
//        for (int i = 0; i < 40000000; i++) {
//            array[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
//        }
        int[] array = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0, -56, 5, 45, 32, 7, 12};
        printArray(array);
//        System.out.println("排序前");
//        Date data1 = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date1Str = simpleDateFormat.format(data1);
//        System.out.println("排序前的时间是=" + date1Str);
        shellSort(array);
        printArray(array);
//        Date data2 = new Date();
//        String date2Str = simpleDateFormat.format(data2);
//        System.out.println("排序前的时间是=" + date2Str);
    }

    /**
     * shell排序
     * 先对数组进行分组(/2的进行分组),然后对每一组进行插入排序
     * 当最后只有一组的时候,进行最后一次插入排序,数组就是有序的了
     * <p>
     * 注意:这里的代码效率不高,因为这里的插入排序是一组一组的,提高效率方法就是只做一次插入排序
     *
     * @param array
     */
    private static void shellSort1(int[] array) {
        // 进行分组
        for (int group = array.length / 2; group > 0; group = group / 2) {
            // 循环每一组
            for (int i = 0; i < group; i++) {
                // 对每一组进行插入排序
                for (int j = i + group; j < array.length; j = j + group) {
                    int current = array[j];// 记录当前的值
                    int aa = 1;
                    while ((j - group * aa) > -1 && current < array[j - group * aa]) {
                        array[j - group * (aa - 1)] = array[j - group * aa];
                        aa = aa + 1;
                    }
                    if (j - group * aa < 0) {
                        array[i] = current;
                    } else {
                        array[j - group * (aa - 1)] = current;
                    }
                }
//                printArray(array);
            }
        }
    }

    private static void shellSort(int[] array) {
        // 进行分组
        for (int group = array.length / 2; group > 0; group /=  2) {
            // 进行插入排序, 从第二个数开始
            for (int i = group; i < array.length; i++) {
                int current = array[i];
                int index = i - group;
                while (index >= 0 && current < array[index]) {
                    array[index + group] = array[index];
                    index -= group;
                }
//                if(index < 0) {
//                    array[i - group] = current;
//                    array[index + group] = current;
//                }else {
//                    array[index + group] = current;
//                }
                array[index + group] = current;
            }
        }
    }


    private static void printArray(int[] array) {
        String collect = Arrays.stream(array).mapToObj(x -> x + "").collect(Collectors.joining(",", "----", "----"));
        System.out.println(collect);
    }

}
