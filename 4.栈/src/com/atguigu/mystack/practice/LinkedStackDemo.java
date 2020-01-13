package com.atguigu.mystack.practice;

import sun.applet.Main;

import java.util.Scanner;

/**
 * @Author: chenlj
 * @CreateTime: 2019-12-31 10:17
 * @Description: 使用链表的方式实现栈
 */
public class LinkedStackDemo {

    public static void main(String[] args) {
        LinkedStack<Integer> stack = new LinkedStack<Integer>(4);
        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while (loop) {
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



class MySingleLinkedList<T> {

    private Node<T> head  = new Node(-1);

    /**
     * 获取头节点
     *
     * @return
     */
    public Node<T> getHead() {
        return head;
    }

    /**
     * 判断是否空
     *
     * @return
     */
    public boolean isEmpty() {
        return head.getNext() == null;
    }

    public void add(Node node) {
        Node<T> lastNode = getLastNode();
        if (lastNode == null) {
            head.setNext(node);
        } else {
            lastNode.setNext(node);
        }
    }

    public void del(Node node) {
        if (node == null)
            return;
        Node<T> temp = head;
        while (temp.getNext() != null) {
            if (temp.getNext().getData().equals(node.getData())) {
                temp.setNext(temp.getNext().getNext());
                break;
            }
            temp = temp.getNext();
        }
    }

    public Node<T> getLastNode() {
        if (isEmpty()) {
            return null;
        }
        Node<T> temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        return temp;
    }

    public void list() {
        if (isEmpty()) {
            return;
        }
        Node<T> temp = head.getNext();
        list(temp, 0);
    }

    public void list(Node node, int index) {
        int i = index;
        if (node.getNext() != null) {
            list(node.getNext(), ++index);
        }
        System.out.println("stack[" + i + "]=" + node.getData());
    }

}

class Node<T> {

    private T data;

    private Node next;

    public Node() {

    }

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}