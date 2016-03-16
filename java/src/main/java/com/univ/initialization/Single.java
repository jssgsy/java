package com.univ.initialization;

/**
 * @author: liuml
 * @date: 2015年11月8日 下午3:08:39
 * @version: 1.0
 * @description: 单个类的初始化
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
	}	//4
	
	static Sample sam = new Sample("静态成员sam初始化");	//1
	
	Sample sam1 = new Sample("sam1成员初始化");	//5
	
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
		Single a = new Single();
	}
}
