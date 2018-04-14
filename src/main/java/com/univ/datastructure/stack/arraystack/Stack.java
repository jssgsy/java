package com.univ.datastructure.stack.arraystack;
/** 
 * @author: liuml
 * @date: 2016年4月2日 下午6:06:20 
 * @version: 1.0 
 * @description: 用数组实现栈结构
 * 涉及到的问题：
 * 1. 扩容。
 * 	默认栈大小为10，每次需要扩容的时候增加十个元素的容量；
 * 2. topPositon:可理解成栈的实际容量。
 */

public class Stack {

	public final int DEFAULT_CAPACITY = 10;//默认栈大小
	public final int INCREMENT_CAPACITY = 10;//每次扩容时增加十个元素的大小
	private int topPosition = 0;//始终指向下一个需要存放的元素的位置(这是重点)
	
	//底层的数据结构
	private Object[] elementData = new Object[DEFAULT_CAPACITY];
	
	public Stack(){
		
	}
	
	public Stack(int capacity){
		if (capacity < 0 ) {
			throw new IllegalArgumentException("capacity: " + capacity);
		}
		elementData = new Object[capacity];
		
	}
	/*
	 * 入栈
	 */
	public Object push(Object element){
		if (topPosition == elementData.length) {//栈已满，扩容
			Object[] temp =  new Object[elementData.length + INCREMENT_CAPACITY];
			
			for (int i = 0; i < topPosition; i++) {
				temp[i] = elementData[i];
			}
			elementData = temp;
		}
		elementData[topPosition] = element;
		topPosition++;
		return element;
	}
	
	/*
	 * 栈顶元素出栈并返回栈顶元素
	 */
	public Object pop(){
		if (!isEmpty()) {
			topPosition--;
			Object top = elementData[topPosition];
			return top;
		} else {
			throw new IllegalStateException("empty stack...");
		}
		
		
	}
	
	/*
	 * 取出栈顶元素（栈顶元素不出栈）
	 */
	public Object peek(){
		if (!isEmpty()) {
			return elementData[topPosition-1];
		}else{
			throw new IllegalStateException("empty stack...");
		}
		
	}
	
	/*
	 * 是否为空栈
	 */
	public boolean isEmpty(){
		return topPosition == 0 ? true : false;
	}
	
	
	public static void main(String[] args) {
		Stack s = new Stack(0);
		s.push("aa");
		s.push("ee");
		s.push("dd");
		s.push("aad");
		System.out.println(s.peek());
		System.out.println(s.pop());
		s.push("bb");
		s.push("cc");
		System.out.println(s.pop());
		System.out.println(s.pop());
	}
}


