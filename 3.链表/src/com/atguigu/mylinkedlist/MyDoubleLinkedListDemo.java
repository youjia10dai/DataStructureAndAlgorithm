package com.atguigu.mylinkedlist;

import java.util.Arrays;

/**
 * @Author: chenlj
 * @CreateTime: 2019-12-25 09:32
 * @Description: 双向链表
 */
public class MyDoubleLinkedListDemo {

    public static void main(String[] args) {
        MyDoubleLinkedList doubleLinkedList = new MyDoubleLinkedList();

        PersonNode person1 = new PersonNode(1, "宋江", "及时雨");
        PersonNode person2 = new PersonNode(2, "卢俊义", "玉麒麟");
        PersonNode person3 = new PersonNode(3, "吴用", "智多星");
        PersonNode person4 = new PersonNode(4, "林冲", "豹子头");

        doubleLinkedList.addByOrder(person4);
        doubleLinkedList.addByOrder(person1);
        doubleLinkedList.addByOrder(person3);
        doubleLinkedList.addByOrder(person2);

        doubleLinkedList.list();
        System.out.println("=================================================");

        person4 = new PersonNode(4, "零冲", "豹子头");
        doubleLinkedList.update(person4);
        doubleLinkedList.list();
        System.out.println("=================================================");
//        doubleLinkedList.del(1);
        doubleLinkedList.del(2);
//        doubleLinkedList.del(3);
        doubleLinkedList.del(4);
        doubleLinkedList.list();
    }

}

class MyDoubleLinkedList {

    private PersonNode head = new PersonNode(0, "", "");// 头节点

    public PersonNode getHead() {
        return head;
    }

    public void setHead(PersonNode head) {
        this.head = head;
    }

    public void add(PersonNode personNode) {
        PersonNode lastHeroNode = getLastHeroNode();
        lastHeroNode.next = personNode;
        personNode.pre = lastHeroNode;
    }

    public void addByOrder(PersonNode personNode) {
        PersonNode temp = head;
        while (temp.next != null) {
            if (temp.next.no == personNode.no) {
                System.out.printf("准备插入的人员的编号 %d 已经存在了, 不能加入\n", personNode.no);
                return;
            }
            if (temp.next.no > personNode.no) {
                // 构建四条线(注意构建的顺序)
                personNode.next = temp.next;
                temp.next.pre = personNode;
                personNode.pre = temp;
                temp.next = personNode;
                return;
            }
            temp = temp.next;
        }
        // 添加到末尾
        temp.next = personNode;
        personNode.pre = temp;
    }

    public void update(PersonNode personNode) {
        PersonNode temp = head;
        while (temp.next != null) {
            if(temp.next.no == personNode.no) {
                temp.next.name = personNode.name;
                temp.next.nickname = personNode.nickname;
                break;
            }
            temp =  temp.next;
        }
    }

    public void del(int no) {
        PersonNode temp = head;
        while (temp.next != null) {
            if(temp.next.no == no) {
                // 构建两条线
                if(temp.next.next != null){
                    temp.next.next.pre = temp;
                }
                temp.next = temp.next.next;
                break;
            }
            temp =  temp.next;
        }
    }

    public void list() {
        PersonNode temp = head;
        while (temp.next != null) {
            System.out.println(temp.next);
            temp = temp.next;
        }
    }

    public boolean isEmpty() {
        return head.next == null;
    }

    public PersonNode getLastHeroNode() {
        PersonNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        return temp;
    }
}

class PersonNode {

    public int no;
    public String name;
    public String nickname;
    public PersonNode next;// 下一个节点
    public PersonNode pre;// 上一个节点

    public PersonNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "PersonNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}