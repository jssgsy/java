package com.univ.algorithom.list;

import java.util.Scanner;

import org.junit.Test;

/**
 * 链表相关算法
 */
public class ListTest {

	/**
	 * 寻找倒数第k个节点
     * 非最优解法
	 * 思路：倒数第k个节点就顺数第（n-k+1）个节点
	 * 1. 循环一遍取得链表的节点个数n；
	 * 2. 遍历至顺数第n-k+1个节点
	 */
    public static void main(String[] args) {
		Node tempHead = buildNodeList();
		if (tempHead == null) {
			System.out.println("链表为空...");
			return ;			
		}
		Node head = tempHead;
		int len = 0;//链表长度
		while(tempHead != null){
			len++;
			tempHead = tempHead.next;
		}
		printList(head);
		Scanner in = new Scanner(System.in);
		System.out.println("请输入要求的是倒数第几个节点的值: " );
		int k = in.nextInt();
		if (k > len || k <= 0) {
			throw new IllegalArgumentException("输入值有误");
		}
		int i = 1;
		while(i < (len - k + 1)){
			head = head.next;
			i++;
		}
		System.out.println("倒数第" + k + "个节点即顺数第" + (len-k+1) + "个节点的值为： " + head.val) ;
	}

	private static Node<Integer> buildNodeList() {
		Node<Integer> n1 = new Node<>(1, null);
		Node<Integer> n2 = new Node<>(2,n1);
		Node<Integer> n3 = new Node<>(3,n2);
		Node<Integer> n4 = new Node<>(4,n3);
		Node<Integer> n5 = new Node<>(5,n4);
		return n5;
	}

	@Test
    public void testMergeTwoSortedList() {
        Node<Integer> head1 = new Node<>(1, new Node<>(9, new Node<>(10,
                new Node<>(15, new Node<>(20, new Node<>(21, new Node<>(24, null)))))));
        printList(head1);

        Node<Integer> head2 = new Node<>(2, new Node<>(3, new Node<>(4,
                new Node<>(13, new Node<>(14, null)))));
        printList(head2);

        printList(mergeTwoSortedList(head1, head2));// 遍历的方法
        // printList(mergeTwoSortedList_v2(head1, head2));// 递归的方法
    }
    /**
     * 合并两个有序列表，并使之有序。
     * 方法1：遍历，类似归排序中的merge方法。{@link com.univ.algorithom.sort.MergeSort#merge(int[], int, int, int)}
     * @param head1
     * @param head2
     * @return 新链表的头节点
     *
     * 会破坏掉合并前两个列表的结构
     */
    public <Value extends Comparable<Value>> Node<Value> mergeTwoSortedList(Node<Value> head1, Node<Value> head2) {
        if (null == head1) {
            return head2;
        }
        if (null == head2) {
            return head1;
        }
        Node<Value> head;  // 新链表头节点
        Node<Value> p1 = head1;    // 用来遍历第一个列表
        Node<Value> p2 = head2;    // 用来遍历第二个列表
        if (head1.val.compareTo(head2.val) <= 0) {  // 首先确定新列表的头节点及及各列表开始遍历处
            head = head1;
            p1 = head1.next;
        } else {
            head = head2;
            p2 = head2.next;
        }
        Node<Value> p = head;  //  用来给结果链接赋值
        while (p1 != null || p2 != null) {
            if (p1 == null) {   // 说明第一个列表已处理完成
                // 这里的while与下面的while可直接去掉判断语句。
                // 思路：当某个指针为空时，将新列表的下个列表赋给另一个列表的当前值。
                // 本质是一样的，只是一个在内循环中处理另一个未处理完成的列表，一个在外循环中处理直至两个列表均处理完
                while (p2 != null) {
                    p.next = p2;
                    p = p.next;
                    p2 = p2.next;
                }
            } else if (p2 == null) {    // 说明第二个列表已处理完成
                while (p1 != null) {
                    p.next = p1;
                    p = p.next;
                    p1 = p1.next;
                }
            } else {
                if (p1.val.compareTo(p2.val) <= 0) {
                    p.next = p1;
                    p = p1;
                    p1 = p.next;
                } else {
                    p.next = p2;
                    p = p2;
                    p2 = p.next;
                }
            }
        }
        return head;
    }

    /**
     * 方法2：递归
     * {@link #mergeTwoSortedList(Node, Node)}
     * @param head1
     * @param head2
     * @param <Value>
     * @return 新列表的头节点
     *
     * 注：与{@link #mergeTwoSortedList(Node, Node)}一样也会破坏掉合并前两个列表的结构
     */
    public <Value extends Comparable<Value>> Node<Value> mergeTwoSortedList_v2(Node<Value> head1, Node<Value> head2) {
        if (null == head1) {
            return head2;
        }
        if (null == head2) {
            return head1;
        }
        Node<Value> head;
        if (head1.val.compareTo(head2.val) <= 0) {
            head = head1;
            head.next = mergeTwoSortedList_v2(head1.next, head2);
        } else {
            head = head2;
            head.next = mergeTwoSortedList_v2(head1, head2.next);
        }
        return head;
    }

	private static void printList(Node head) {
        Node node = head;
        System.out.print("链表为：");
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println("");
    }
}

class Node<Value extends Comparable<Value>>{
	Value val;
    Node<Value> next;
	public Node(Value val, Node next){
		this.val = val;
		this.next = next;
	}
}

