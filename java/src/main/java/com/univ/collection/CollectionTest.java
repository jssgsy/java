package com.univ.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/** 
 * @author univ 
 * @date 2016年1月19日 下午2:57:58 
 * @version v1.0
 * @Description: 
 */
public class CollectionTest {

	/**
	 * 集合的交集，并集，差集测试
	 */
	@Test
	public void test(){
		//交集演示
		List<Integer> A1 = new ArrayList<Integer>();
		Collections.addAll(A1, 1,2,3,4);
		System.out.println("A1: " + A1);
		List<Integer> A2 = new ArrayList<Integer>();
		Collections.addAll(A2, 3,4,5,6);
		System.out.println("A2: " + A2);
		A1.retainAll(A2);
		System.out.println("A1交A2: " + A1);//注意此时A1就是交集
		
		//并集演示
		List<Integer> A3 = new ArrayList<Integer>();
		Collections.addAll(A3, 1,2,3,4);
		System.out.println("A3" + A3);
		List<Integer> A4 = new ArrayList<Integer>();
		Collections.addAll(A4, 3,4,5,6);
		System.out.println("A4: " + A4);
		A3.addAll(A4);
		System.out.println("A3并A4: " + A3);//注意此时A3就是交集
		
		//差集演示
		List<String> A5 = new ArrayList<String>();
		Collections.addAll(A5, "1","2","3","4");
		System.out.println("A5: " + A5);
		List<String> A6 = new ArrayList<String>();
		Collections.addAll(A6, "1","2","5","6");
		System.out.println("A6: " + A6);
		A6.removeAll(A5);
		System.out.println("A6差A5: " + A6);//注意此时A1就是交集
	}
	
	@Test
	public void test1(){
		List<Integer> old = new ArrayList<Integer>();
		Collections.addAll(old, 1,2,3,5);
		
		List<Integer> tempList = new ArrayList<Integer>();
		tempList.addAll(old);
		System.out.println(tempList);
		System.out.println(old);
		
	}
}

