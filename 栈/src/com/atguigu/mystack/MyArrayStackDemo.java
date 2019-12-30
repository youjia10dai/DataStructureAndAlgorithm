package com.atguigu.mystack;

/**
 * @Author: chenlj
 * @CreateTime: 2019-12-30 17:53
 * @Description: 使用数组实现栈
 */
public class MyArrayStackDemo {

    public static void main(String[] args) {
        MyArrayStack myStack = new MyArrayStack(5);

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

    public void push(int data) {
        if(top < maxTop - 1) {
            stack[++top] = data;
        }else {
            throw new RuntimeException("栈数据已满~");
        }
    }

    public void list() {
        for (int i = top; i > -1; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

}