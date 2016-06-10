package com.univ.datastruct.list;

import com.univ.initialization.Single;
import org.junit.Test;

/**
 * created by Univ
 * 16/6/8 14:15
 * 单链表
 * todo:可以考虑附加头节点,这样可以在操作上能够将头节点与其他节点一起对待,简化代码;
 */
public class SingleList {

    /**
     * 指向链表的头节点
     */
    private Node head;

    /**
     * 链表中节点的个数,便于程序编写
     */
    private int size;

    /**
     * 新增一个头节点
     *
     * @param val
     */
    public void add(int val) {
        if (isEmpty()) {
            head = new Node(val);
        } else {
            head = new Node(val, head);
        }
        size++;
    }

    /**
     * 删除指定索引上的元素
     *
     * @param index todo:是否需要在类中定义size属性用以表示链表节点个数?
     *              重点:
     *              1. 参数的校验;
     *              2. 分头节点删和非头节点(包含尾节点)删;
     */
    public void remove(int index) {
        if (isEmpty()) {
            throw new IllegalArgumentException("链表为空");
        }

        if (index < 0 || index > (size - 1)) {
            throw new IllegalArgumentException("要删除元素的索引index不属于[0," + (size - 1) + "]之中");
        }

        //删除头节点和非头节点需要分开操作
        if (index == 0) {//删除头节点
            head = head.next;
        } else {//删除非头节点(包含尾节点)
            Node pre = head;//保持在indexToDel之前
            int indexToDel = 1;
            while (pre != null) {
                if (indexToDel == index) {
                    break;
                } else {
                    indexToDel++;
                    pre = pre.next;
                }
            }
            pre.next = pre.next.next;
        }

        size--;


    }

    /**
     * 获取指定索引上的元素值
     *
     * @param index
     * @return
     */
    public int get(int index) {
        if (isEmpty()) {
            throw new IllegalArgumentException("链表为空");
        }

        if (index < 0 || index > (size - 1)) {
            throw new IllegalArgumentException("要获取元素的索引index不属于[0," + (size - 1) + " + ]之中");
        }

        Node node = head;//不要动原链表,很重要,下面有很多类似的操作
        int indexToGet = 0;
        while (node != null) {
            if (indexToGet == index) {
                break;
            } else {
                indexToGet++;
                node = node.next;
            }
        }
        return node.val;

    }

    /**
     * 判断是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }

    public int[] toArray() {
        int[] arr = new int[size];

        Node node = head;
        int i = 0;
        while (node != null) {
            arr[i++] = node.val;
            node = node.next;
        }
        return arr;
    }

    /**
     * 链表反转:产生一个新链表返回,注意此时原链表并没有发生变化;
     *
     * @return
     */
    public SingleList reverse() {
        if (head == null) {
            return null;
        }
        SingleList list = new SingleList();
        Node node = head;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        return list;
    }

    /**
     * 链表反转:对原链表进行反转
     * 重点:反转第二个节点和其他节点需要区别对待(反转第二个节点时需要将第一个节点的next指向null)
     *
     * @return
     */
    public SingleList reverse2() {

        if (head == null || head.next == null) {//链表为空或者链表只有一个节点
            return this;
        }

        //反转第二个节点
        Node nextToReverse = head.next;
        Node temp = nextToReverse.next;
        nextToReverse.next = head;
        head = nextToReverse;
        head.next.next = null;
        nextToReverse = temp;

        while (nextToReverse != null) {
            temp = nextToReverse.next;
            nextToReverse.next = head;
            head = nextToReverse;
            nextToReverse = temp;
        }

        return this;
    }

    /**
     * 遍历输出链表
     * todo:后期换成遍历的方式
     */
    public void show() {
        Node first = head;
        while (first != null) {
            System.out.print(first.val + ", ");
            first = first.next;
        }
        System.out.println();
    }


    private static class Node {
        private int val;
        private Node next;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }


}
