package com.atguigu.mystack;

import java.util.Scanner;

/**
 * @Author: chenlj
 * @CreateTime: 2019-12-30 17:53
 * @Description: 使用数组实现栈
 */
public class MyArrayStackDemo {

    public static void main(String[] args) {
        MyArrayStack stack = new MyArrayStack(4);
        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        while(loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~~");

    }

}
 class MyArrayStack {

    //栈的指针,初始为-1
    private int top;

    //栈存储的最大值
    private int maxTop;

    //栈
    private int[] stack;

    public MyArrayStack(int size) {
        top =  -1;
        maxTop = size;
        stack = new int[maxTop];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int pop() {
        if(isEmpty()) {
            throw new RuntimeException("栈空，没有数据~");
        }
        return stack[top--];
    }

    public int peek() {
        if(isEmpty()) {
            throw new RuntimeException("栈空，没有数据~");
        }
        return stack[top];
    }

    public void push(int data) {
        if(top < maxTop - 1) {
            stack[++top] = data;
        }else {
            throw new RuntimeException("栈数据已满~");
        }
    }

    public void push(String data){
        push(Integer.parseInt(data));
    }

    public void list() {
        for (int i = top; i > -1; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

}