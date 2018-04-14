package com.univ.autoboxing;

import org.junit.Test;

/** 
 * @author: liuml
 * @date: 2016年1月25日 上午9:00:34 
 * @version: 1.0 
 * @description: 自动装箱，拆箱测试
 */

public class AutoBoxingTest {

	/**
	 * 包装类型和原始类型的比较判断(== and equals)
	 */
	@Test
	public void test1(){
		Integer a = 1;
		int i = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 2;
		Integer e = 321;
		Integer f = 321;
		Long g = 3L;
		System.out.println(c == d);//false
		System.out.println(e == f);//false
		System.out.println(c == (a+b));//true
		System.out.println(g == (a+b));//true
		System.out.println(c.equals(a+b));//true
		System.out.println(g.equals(a+b));//false:包装类的equals方法不处理数据转型的关系
		System.out.println(a == i);//true
	}
}


