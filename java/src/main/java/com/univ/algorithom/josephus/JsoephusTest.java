package com.univ.algorithom.josephus;

import java.util.Scanner;
/** 
 * @author: liuml
 * @date: 2016年3月18日 下午6:40:19 
 * @version: 1.0 
 * @description: 约瑟夫问题
 * 	num人围成一圈，从1开始编号，从编号1开始报数，报第k个数的人出局，接下来的人继续上面的过程，直至留下一人游戏结束。
 * 重点在于josephus()方法：外围循环一次，出局一个人，内围循环一次，找到下一个要出局的人。
 */

public class JsoephusTest {

	private static Node node = new Node("节点1", null);//先创建第一个节点
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		System.out.println("请输入参加游戏的人数： ");
		int num = in.nextInt();
		System.out.print("请输入游戏要玩的数字：");  
		int k = in.nextInt();
		
		
		createJosephusCycle(num);
		
		showCycleList(JsoephusTest.node);
		Node winner = josephus(JsoephusTest.node,num,k);
		System.out.print("winner is : ");
		showCycleList(winner);
	}
	
	//创建n个节点的约瑟夫环,引入一个temp完成循环赋值
	public static void createJosephusCycle(int num){		
		Node head = JsoephusTest.node;
		for(int i = 2;i<=num;i++){
			Node temp = new Node("节点" + i,null);
			node.setNextNode(temp);
			node = node.getNextNode();
		}
		node.setNextNode(head);
		node = head;//创建之前node（第一个节点）是哪个节点，创建之后使之仍然是哪个节点
	}
	
	/**
	 * @param node
	 * @return 获胜的节点
	 */
	public static Node josephus(Node node,int num,int k){
		Node toDelete = node;//位置很重要，不要放到循环中定义
		for(int i = 0;i<num-1;i++){//循环一次便出局一个节点，总共需要遍历n-1次
			for(int j = 1;j<k;j++){//循环一次便找到下一个该出局的节点，注意这里j从1到k-1
				node = toDelete;
				toDelete = node.getNextNode();				
			}
			System.out.println(toDelete.getName()+"出局");
			node.setNextNode(toDelete.getNextNode());
			toDelete = node.getNextNode();	
		}
		return node;
	}
	/*
	 * 遍历单向循环单链表
	 * 	从第一个节点开始验证是不是尾节点
	 */
	public static void showCycleList(Node node){
		if (null == node) {
			System.out.println("this is an empty list");
			return ;
		}
		Node head = node;
		while(head != node.getNextNode()){		
			System.out.println(node.getName());
			node = node.getNextNode();
		}
		System.out.println(node.getName());//输出最后一个节点
	}
	
}


class Node{
	private String name;
	private Node nextNode;
	public Node(){
		
	}
	
	public Node(String name, Node nextNode){
		this.name = name;
		this.nextNode = nextNode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Node getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
	
	
}