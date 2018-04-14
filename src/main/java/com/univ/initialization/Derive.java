package com.univ.initialization;
/** 
 * @author: liuml
 * @date: 2015年11月8日 下午3:07:29 
 * @version: 1.0 
 * @description: 带有继承关系的初始化
 */
class Base{
	public Base(){
		System.out.println("父类无参构造函数");
	}
	
	public Base(String name){
		System.out.println("父类有参构造函数，参数为：" + name);
	}
	
	{
		System.out.println("父类普通代码块");
	}//普通代码块在每次new时都会在相应的构造函数前执行一次	
	
	static Base b2 = new Base("父类静态变量初始化");//注意,这里会调用普通代码块
	static{
		System.out.println("父类静态代码块");
	}//静态代码块只在类装载时执行一次
}

public class Derive extends Base{
	{
		System.out.println("子类普通代码块");
	}
	
	public Derive(){
		System.out.println("子类无参构造函数");
	}
	
	public Derive(String name){
		System.out.println("子类有参构造函数，参数为： " + name);
	}
	
	static{
		System.out.println("子类静态代码块");
	}
	
	static Derive j2 = new Derive("子类静态成员变量初始化");//new子类对象之前会先调用父类的普通代码块及父类相应的构造函数,很重要
	
	public static void main(String str[]) {
		new Derive();
	}
}

