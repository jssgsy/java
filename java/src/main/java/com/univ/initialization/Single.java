package com.univ.initialization;

/**
 * @author: liuml
 * @date: 2015年11月8日 下午3:08:39
 * @version: 1.0
 * @description: 单个类的初始化
 * 总结：
 * 1.static成员变量和static块的执行顺序是平级的，按其声明顺序（注意，包括了static成员变量），并且只执行一次；
 * 2.普通代码块和非static成员变量的执行顺序是平级的，按其声明顺序,每次new对象时都会被执行；
 * 3.构造函数;
 * 
 */
class Sample {
	Sample(String s) {
		System.out.println(s);
	}

	Sample() {
		System.out.println("Sample默认构造函数被调用");
	}
}

public class Single {
	
	{
		System.out.println("普通代码块");
	}	//4		在非static成员变量初始化之前（如这里的sam1），所以不能访问类的非static成员变量
	
	Sample sam1 = new Sample("sam1成员初始化");	//5
	
	static Sample sam = new Sample("静态成员sam初始化");	//1	static（不论是static成员变量还是static块）的执行顺序是依据其声明顺序的
	
	static {
		System.out.println("static块执行");	//2
		if (sam == null)
			System.out.println("sam is null");
		sam = new Sample("静态块内初始化sam成员变量");	//3
	}

	Single() {
		System.out.println("Single默认构造函数被调用");	//6
	}

	public static void main(String str[]) {
		new Single();
		new Single();
	}
}
