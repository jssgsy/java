package com.univ.algorithom.list;

import java.util.Scanner;

import org.junit.Test;

public class ListTest {

	/*
	 * 寻找倒数第k个节点
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
		
		System.out.println("the number of the list is : " + len);
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

	private static Node buildNodeList() {
		Node n1 = new Node(1,null);
		Node n2 = new Node(2,n1);
		Node n3 = new Node(3,n2);
		Node n4 = new Node(4,n3);
		Node n5 = new Node(5,n4);
		return n5;
	}
	
	@Test
	public void test(){

	}
}

class Node{
	int val;
	Node next;
	public Node(int val, Node next){
		this.val = val;
		this.next = next;
	}
}

