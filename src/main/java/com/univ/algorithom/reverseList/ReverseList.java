package com.univ.algorithom.reverseList;

/** 
 * @author: liuml
 * @date: 2016年3月16日 下午6:17:46 
 * @version: 1.0 
 * @description: 反转单向链表
 */

public class ReverseList {

	public static void main(String[] args) {
		//构建节点链表
		Node node5 = new Node(5, null);
		Node node4 = new Node(4, node5);
		Node node3 = new Node(3, node4);
		Node node2 = new Node(2, node3);
		Node node1 = new Node(1, node2);
		//链表的顺序输出
		showNodes(node1);
		
		//整个链表反转后输出
		Node head = reverse(node1);
		showNodes(head);
		
		//空链表输出
		Node n = null;
		head = reverse(n);
		showNodes(head);
	}

	/*
	 * 算法核心：
	 * 	1.在反转a节点之前需要先记录下a的下一个节点;
	 * 	2.需要引入两个变量temp和nextToReverse;
	 * 	3.head和nextToReverse的位置固定(temp在中间起过渡作用，所以也才有了上面的第二点)：
	 * 		head:指向链表的头结点（不论是反转前还是反转后）；
	 * 		nextToReverse:下一个将要被反转的节点；
	 */
	public static Node reverse(Node head){
		if (null == head) {
			return null;
		}
		if (null == head.getNextNode()) {
			return head;
		}
		//处理第一个节点的反转
		Node temp = head.getNextNode();
		Node nextToReverse = temp.getNextNode();
		temp.setNextNode(head);
		head.setNextNode(null);
		head = temp;	
		
		//处理其余节点的反转
		while(null != nextToReverse){
			temp = nextToReverse.getNextNode();//记录下一个需要反转的节点
			nextToReverse.setNextNode(head);
			head = nextToReverse;
			nextToReverse = temp;
		}	
		
		return head;
	}
	public static void showNodes(Node head) {
		if (null == head) {
			System.out.println("this is a empty Node list");
		}
		while(null != head){
			System.out.print(head.getAge() + " ");
			head = head.getNextNode();
		}
	}

}

class Node {
	private int age;
	private Node nextNode;

	public Node() {
	}

	public Node(int age, Node nextNode) {
		super();
		this.age = age;
		this.nextNode = nextNode;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Node getNextNode() {
		return nextNode;
	}

	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
}


