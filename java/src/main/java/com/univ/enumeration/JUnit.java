package com.univ.enumeration;

import org.junit.Test;


/** 
 * @author: liuml
 * @date: 2016年3月6日 下午2:39:07 
 * @version: 1.0 
 * @description: 
 */

public class JUnit {
	@Test
	public void test(){
		//enum 实例有一个很重要的方法toString()
		if(A.GREEN.toString().equals("GREEN")){
			System.out.println(A.valueOf("GREEN"));
		}
		
		//枚举常量在switch中的使用方法
		A a = A.BLUE;
		enumSwitch(a);
		
	}
	
	public void enumSwitch(A i){
		switch(i){
		case GREEN:System.out.println("green");break;//这里case中的GREEN不用（也没法）写成A.GREEN,只需要写A的某个取值便可以
		case RED:System.out.println("red");break;
		default:System.out.println("no color");
		}
	}
}

enum A{
	RED,GREEN,BLUE
}//RED,GREEN,BLUE都是A的实例



