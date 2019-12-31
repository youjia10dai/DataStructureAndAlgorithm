package com.atguigu.mystack.practice;

public class LinkedStack<T> {

    // 栈
    private MySingleLinkedList<T> stack;

    //栈的指针,初始为-1
    private int top;

    //栈存储的最大值
    private int maxTop;

    public LinkedStack(int size) {
        top = -1;
        maxTop = size;
        stack = new MySingleLinkedList();
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public T peek() {
        if(isEmpty()) {
            return null;
        }
        return stack.getLastNode().getData();
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据~");
        }
        Node<T> lastNode = stack.getLastNode();
        stack.del(lastNode);
        top--;
        return lastNode.getData();
    }

    public void push(T data) {
        if (top < maxTop - 1) {
            top++;
            stack.add(new Node(data));
        } else {
            throw new RuntimeException("栈数据已满~");
        }
    }

    public void list() {
        stack.list();
    }
}