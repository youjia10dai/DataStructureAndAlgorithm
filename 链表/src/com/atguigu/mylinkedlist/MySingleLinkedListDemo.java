package com.atguigu.mylinkedlist;

import java.util.Arrays;
import java.util.Stack;

/**
 * @Author: chenlj
 * @CreateTime: 2019-12-24 13:15
 * @Description: 单向链表
 */
public class MySingleLinkedListDemo {

    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建要给链表
        MySingleLinkedList singleLinkedList = new MySingleLinkedList();

        //1.加入
        //singleLinkedList.add(hero1);
        //singleLinkedList.add(hero3);
        //singleLinkedList.add(hero4);
        //singleLinkedList.add(hero2);

        //2.有序的加入
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
//        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.list();

        //3.修改数据
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况~~");
        singleLinkedList.list();

        //4.删除一个节点
//        singleLinkedList.del(4);
//        singleLinkedList.del(1);
//        singleLinkedList.del(2);
//        singleLinkedList.del(5);
//        System.out.println("删除后的链表情况~~");
//        singleLinkedList.list();

        //5.获取链表中有效数据的数量
        int length = getLength(singleLinkedList);
        System.out.println("length = " + length);

        HeroNode heroNode = findLastIndexNode(singleLinkedList, 2);
        System.out.println("heroNode = " + heroNode);
        reverseList(singleLinkedList);
        singleLinkedList.list();
        System.out.println("====================================================");
        reversePrint(singleLinkedList);
    }

    //方法：获取到单链表的节点的个数(如果是带头结点的链表，需求不统计头节点)
    /**
     *
     * @param list 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(MySingleLinkedList list) {
        HeroNode temp = list.getHead();
        int length = 0;
        while(true) {
            if(temp.next == null){
                break;
            }
            length++;
            temp = temp.next;
        }
        return length;
    }

    //查找单链表中的倒数第k个结点 【新浪面试题】
    //思路
    //1. 编写一个方法，接收head节点，同时接收一个index
    //2. index 表示是倒数第index个节点
    //3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
    //4. 得到size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
    //5. 如果找到了，则返回该节点，否则返回null
    public static HeroNode findLastIndexNode(MySingleLinkedList list, int index) {
        int length = getLength(list);
        if(index <= 0 || index > length){
            System.out.println("获取数据异常");
            return null;
        }
        HeroNode head = list.getHead();
        for(int i = 0; i <= length - index; i++) {
            head = head.next;
        }
        return head;
    }

    //将单链表反转
    public static void reverseList(MySingleLinkedList list) {
        HeroNode tempHead = new HeroNode(0, "", "");
        HeroNode head = list.getHead().next;
        HeroNode nextHead = null;
        while(head != null) {
            // 记录下下一个节点
            nextHead = head.next;
            // 修改将要插入节点
            head.next = tempHead.next;
            // 插入节点
            tempHead.next = head;
            // 获取下一个将插入的节点
            head = nextHead;
        }
        list.setHead(tempHead);
    }

    //方式2：
    //可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
    public static void reversePrint(MySingleLinkedList list) {
        HeroNode head = list.getHead();
        Stack<HeroNode> stack = new Stack<HeroNode>();
        while(head.next != null) {
            stack.add(head.next);
            head = head.next;
        }

        while(stack.size() > 0){
            System.out.println(stack.pop());
        }

    }

}

//定义SingleLinkedList 管理我们的英雄
class MySingleLinkedList {

    //先初始化一个头节点, 头节点不要动, 不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }

    //添加节点到单向链表
    //思路，当不考虑编号顺序时
    //1. 找到当前链表的最后节点
    //2. 将最后这个节点的next 指向 新的节点
    public void add(HeroNode heroNode) {
        getLastHeroNode().next = heroNode;
    }

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //(如果有这个排名，则添加失败，并给出提示)
    public void addByOrder(HeroNode heroNode) {
        // 那子节点去比较
        HeroNode temp = head;
        while(true) {
            if(temp.next == null) {
                temp.next = heroNode;
                break;
            }
            if(temp.next.no == heroNode.no) {
                System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
                break;
            }
            if(temp.next.no > heroNode.no){
                heroNode.next = temp.next;
                temp.next = heroNode;
                break;
            }
            temp = temp.next;
        }
    }

    //修改节点的信息, 根据no编号来修改，即no编号不能改.
    //说明
    //1. 根据 newHeroNode 的 no 来修改即可
    public void update(HeroNode newHeroNode) {
        HeroNode temp = head;
        while(true) {
            if(temp.next == null) {
                System.out.printf("要修改的 %d 节点不存在\n", newHeroNode.no);
                return;
            }
            if(temp.next.no == newHeroNode.no){
                temp.next.name = newHeroNode.name;
                temp.next.nickname = newHeroNode.nickname;
                return;
            }
            temp = temp.next;
        }
    }

    //删除节点
    //思路
    //1. head 不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
    //2. 说明我们在比较时，是temp.next.no 和  需要删除的节点的no比较
    public void del(int no) {
        HeroNode temp = head;
        while(true) {
            if(temp.next == null) {
                System.out.printf("要删除的 %d 节点不存在\n", no);
                return;
            }
            if(temp.next.no == no){
                System.out.printf("删除节点%d\n", no);
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
    }

    //显示链表[遍历]
    public void list() {
        if(isEmpty()) {
            System.out.println("列表数据为空");
            return;
        }
        HeroNode temp = head.next;
        while(true) {
            System.out.println(temp);
            temp = temp.next;
            if(temp == null) {
                break;
            }
        }
    }

    //判断列表数据是否为空
    public boolean isEmpty() {
        return head.next == null;
    }

    //获取链表最后一个元素
    public HeroNode getLastHeroNode() {
        HeroNode temp = head;
        while(true) {
            if(temp.next == null) {
                return temp;
            }
            temp = temp.next;
        }
    }

}

//定义HeroNode ， 每个HeroNode 对象就是一个节点
class HeroNode {

    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    //构造器
    public HeroNode(int no, String name, String nickname) {
        // 不用初始化 HeroNode
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方法，我们重新toString
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }

}