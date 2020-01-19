package com.atguigu.myhashtab;


import java.util.Scanner;

/**
 * @Author: chenlj
 * @CreateTime: 2020-01-19 17:12
 * @Description: 实现哈希表
 */
public class MyHashTabDemo {

    public static void main(String[] args) {

        //创建哈希表
        MyHashTable hashTab = new MyHashTable();

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("add:  添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建 雇员
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
//                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }

    }

}

class MyHashTable<T extends MyHash> {

    private LinkedList[] array = new LinkedList[5];

    public MyHashTable() {
        for (int i = 0; i < array.length; i++) {
            array[i] = new LinkedList();
        }
    }

    public void add(T t) {
        int hashValue = t.getHashValue();
        array[hashValue].add(t);
    }

    public void list() {
        for (int i = 0; i < array.length; i++) {
            System.out.print("当前链表ID为:" + i);
            array[i].list();
        }
    }

}

/**
 * 链表
 */
class LinkedList<T> {

    private Pointer<T> head;

    public void add(T t) {
        if (head == null) {
            head = new Pointer(t);
            return;
        }
        Pointer<T> next = head;
        while (next.next != null) {
            next = next.next;
        }
        next.next = new Pointer(t);
    }

    public void list() {
        if(head == null) {
            System.out.println("链表为空");
            return;
        }
        Pointer<T> next = head;
        while(next != null) {
            System.out.println(next.data);
            next = next.next;
        }
    }

}

class Pointer<T> {

    /**
     * 下一个指针
     */
    public Pointer<T> next;

    // 真实存储的数据
    public T data;

    public Pointer(T data) {
        this.data = data;
    }
}

/**
 * 实体类
 */
class Emp implements MyHash{

    public int id;

    public String name;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 获取hash值
     *
     * @return
     */
    public int getHashValue() {
        return id % 5;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}