package com.atguigu.myqueue;

import java.util.Scanner;

/**
 * @Author: chenlj
 * @CreateTime: 2019-12-23 15:55
 * @Description: 实现
 */
public class MyArrayQueueDemo {

    public static void main(String[] args) {
        //测试一把
        //创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~");
    }

}

// 使用数组模拟队列-编写一个ArrayQueue类
class ArrayQueue {

    private int[] array; // 数组
    private int front; // 前部的
    private int rear; // 后面的
    private int maxSize; // 表示数组的最大容量

    // 创建队列的构造器
    public ArrayQueue(int arrMaxSize) {
        array = new int[arrMaxSize];
        this.maxSize = arrMaxSize;
        // 初始值
        front = -1;
        rear = -1;
    }

    // 判断队列是否满
    public boolean isFull() {
        return maxSize - 1 == rear;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 添加数据到队列
    public void addQueue(int n) {
        if (!isFull()) {
            array[++rear] = n;
        } else {
            System.out.println("队列已经满了");
        }
    }

    // 获取队列的数据, 出队列
    public int getQueue() {
        if (isEmpty()) {
            // 通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        return array[++front];
    }

    // 显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空的，没有数据~~");
            return;
        }
        for (int i = 0; i < array.length; i++) {
            System.out.println("array[" + i + "]:" + array[i]);
        }
    }

    // 显示队列的头数据， 注意不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            // 通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        return array[front + 1];
    }
}

