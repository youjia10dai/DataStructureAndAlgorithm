package com.atguigu.mysort;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author: chenlj
 * @CreateTime: 2020-01-14 15:11
 * @Description: 冒泡排序
 */
public class MyBubbleSort {

    static int[] array = new int[80000];

    static {
        for (int i = 0; i < 80000; i++) {
            array[i] = (int) (Math.random() * 8000000); //生成一个[0, 8000000) 数
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, 20,-96,78,23,12,63};
        printArray(arr);
        bubbleSort(arr);
        printArray(arr);
    }

    /**
     * 冒泡排序规则(每次选择出最大值)
     * (1) 一共进行 数组的大小-1 次 大的循环
     * (2)每一趟排序的次数在逐渐的减少
     * (3) 如果我们发现在某趟排序中，没有发生一次交换， 可以提前结束冒泡排序。这个就是优化
     *
     * @param array
     */
    public static void bubbleSort(int[] array) {
        int loopCount = array.length - 1;
        // 是否有进行交换操作,如果没有交换操作说明数组已经有序
        boolean isChange = false;
        for (int i = 0; i < loopCount; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if(array[j] > array[j + 1]) {
                    change(array, j, j+1);
                    isChange = true;
                }
            }
            if(!isChange) {
                break;
            }
            isChange = false;
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
