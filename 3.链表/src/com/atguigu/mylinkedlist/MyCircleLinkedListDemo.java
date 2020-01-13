package com.atguigu.mylinkedlist;


/**
 * @Author: chenlj
 * @CreateTime: 2019-12-25 11:29
 * @Description: 环形链表(解决约瑟夫问题)
 */
public class MyCircleLinkedListDemo {
    public static void main(String[] args) {
        // 测试一把看看构建环形链表，和遍历是否ok
        MyCircleLinkedList circleSingleLinkedList = new MyCircleLinkedList();
//        circleSingleLinkedList.addBoy(102);// 加入5个小孩节点
//        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(1, 1,2);
    }
}

class MyCircleLinkedList {

    private Boy first;

    // 添加小孩节点，构建成一个环形的链表
    public void addBoy(int nums) {
        if(nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        initFirstBoy();
        Boy curBoy = first;//
        for(int i = 2; i <= nums; i++){
            Boy boy = new Boy(i);
            curBoy.setNext(boy);
            curBoy = boy;
        }
        if(curBoy != first) {
            curBoy.setNext(first);
        }
    }

    private void initFirstBoy() {
        first = new Boy(1);
        first.setNext(first);
    }

    // 遍历当前的环形链表
    public void showBoy() {
        if(isEmpty()) {
            System.out.println("没有任何小孩~~");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if(curBoy.getNext() == first){
                return;
            }
            curBoy = curBoy.getNext();
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    // 根据用户的输入，计算出小孩出圈的顺序
    /**
     *
     * @param startNo
     *            表示从第几个小孩开始数数
     * @param talkCount
     *            表示数几下
     * @param childCount
     *            表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int talkCount, int childCount) {
        if(childCount < 1 || talkCount < 1 || startNo < 1 || startNo > childCount) {
            System.out.println("参数输入有误， 请重新输入");
            return;
        }
        addBoy(childCount);
        // 一共会数 childCount - 1 次
        Boy curBoy = first;
        for(int i = 1; i <= startNo - 1; i++){
            curBoy = curBoy.getNext();
        }
        for(int i = 1; i <= childCount - 1; i++) {
            Boy tempPreBoy = curBoy;;// 上一个男孩
            for(int j = 1; j < talkCount; j++){
                curBoy = curBoy.getNext();
            }
            // 删除当前的
            System.out.printf("小孩%d出圈\n", curBoy.getNo());
            tempPreBoy.setNext(curBoy.getNext());
            curBoy = curBoy.getNext();
        }
        System.out.printf("最后留在圈中的小孩编号%d \n", curBoy.getNo());
    }

}

class Boy {
    private int no;// 编号
    private Boy next; // 指向下一个节点,默认null

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

}