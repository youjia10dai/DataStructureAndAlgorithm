package com.atguigu.myrecursion;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: chenlj
 * @CreateTime: 2020-01-13 19:40
 * @Description: 解决8皇后问题
 */
public class MyQueue8 {

    /**
     *  皇后的数量
     */
    private static final int QUEUE_COUNT = 8;

    /**
     *  存放皇后的数组
     */
    private static Integer[] queueArray = new Integer[QUEUE_COUNT];

    /**
     * 皇后的摆法
     */
    private static int count = 0;

    /**
     * 冲突的数量
     */
    private static int conflictCount = 0;

    static {
        Arrays.fill(queueArray, 0);
    }

    public static void main(String[] args) {
        check(0);
        System.out.println("count = " + count);
        System.out.println("conflictCount = " + conflictCount);
    }

    /**
     * 放皇后
     * @param current
     */
    public static void check(int current) {
        if(current == QUEUE_COUNT) {
            printQueue();
            count++;
            return;
        }
        // 放0到7
        for(int i = 0; i < QUEUE_COUNT; i++) {
            queueArray[current] = i;
            if(!isConflict(current)) {
                // 没有冲突,放下一个皇后
                check(current + 1);
            }
        }
    }

    /**
     * 检测第current皇后有没有冲突
     * @return
     */
    public static boolean isConflict(int current) {
        conflictCount++;
        // 最后一个皇后的值
        Integer last = queueArray[current];
        for(int i = 0; i < current; i++){
            // 不在同一列
            if(queueArray[i].intValue() ==  last.intValue()) {
                return true;
            }
            //是否在同一斜线
            if(Math.abs(queueArray[i] - last) == Math.abs(i - current)) {
                return true;
            }
            //是否在同一斜线
//            if(queueArray[i] - i == last - current) {//上升
//                转换成为 :queueArray[i] - last = i- current
//                return false;
//            }
//            if(queueArray[i] + i == last + current) {//下降
//                转换成为 :queueArray[i] - last = current -i 加上绝对值之后就变成上方的公式
//                abs(queueArray[i] - last) =
//                return false;
//            }
        }
        return false;
    }


    private static void printQueue() {
        Stream<Integer> stream = Arrays.stream(queueArray);
        String queueStr = stream.map((x) -> {
            if (x == null) {
                return "";
            }
            return (x + 1) + "";
        }).collect(Collectors.joining(",", "----", "----"));
        System.out.println(queueStr);
    }

}