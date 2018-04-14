package com.univ.exception;
/** 
 * @author: liuml
 * @date: 2015年8月4日 下午7:38:00 
 * @version: 1.0 
 * @description: 自定义一个exception
 * 1.继承自Exception或任意其子类；
 * 2.提供默认的构造函数；
 * 3.提供描述异常的带参构造函数；
 */

public class MyException extends Exception {
	public MyException(){
		
	}
	
	public MyException(String message){
		super(message);
	}
}


