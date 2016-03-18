package com.univ.array;

import java.util.Arrays;

import org.junit.Test;
import org.omg.PortableServer.ForwardRequestHelper;

/** 
 * @author: liuml
 * @date: 2016年3月18日 上午8:58:17 
 * @version: 1.0 
 * @description: 数组相关测试
 * 注意：
 * 	1.java中定义数组时不能指定数组大小，
 * 		int[4] a;//这是错误的
 * 		int[] a = new int[4];//这是对的
 */

public class ArrayTest {

	@Test
	public void defineArray(){
		//基本类型数组的定义方法
		int[] a = {1,3,5};
		int[] b = new int[]{1,3,5};//1.这里的两个[]都不能加数字;2.new int[]{1,3,5}其实是一个匿名数组，用在String中多
		int[] c = new int[3];
		c[0]=c[1]=c[2]=0;
		
		//String类型数组定义（和上面的基本类型数组的定义方法一样）
		String[] str = {"aaa","bbb","ccc"};
		String[] str1 = new String[]{"aaa","bbb","ccc"};
		String[] str2 = new String[3];
		str2[0]=str2[1]=str2[2]="aaa";
		
		//类类型数组定义
		ArrayTest[] arr = new ArrayTest[]{new ArrayTest(),new ArrayTest()};
		ArrayTest[] arr1 = new ArrayTest[2];
		arr1[0]=arr1[1]=new ArrayTest();
		String str5 = "";
		
	}
	
	//将一个数组的内容拷贝至另一个数组，使用Arrays.copyOf()方法
	@Test
	public void copyArray(){
		int[] oldArr = new int[]{3,6,5,2};
		int[] newArr = Arrays.copyOf(oldArr, oldArr.length);//此时oldArr和newArr是两个完全不同的数组
		newArr[2] = 100;
		showArray(oldArr);//3 6 5 2 
		showArray(newArr);//3 6 100 2 
	}
	
	//将二维数组当做一位数组对待会好理解一些，退化后的一位数组中的每个元素都是一个数组（类似于广义表）
	@Test
	public void multiArray(){
		int[] arr = new int[]{1,2,3};
		int[][] arrs = new int[][]{
				{1,2,3},
				{4,5}
				};//当做一位数组，每个元素又都是一个数组
		//遍历二维数组：仍然当做一位数组
		for (int[] is : arrs) {
			for (int i : is) {
				System.out.println(i);
			}
		}
	}
	
	
	
	//输出数组，供其他函数调用
	public void showArray(int[] arr){
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
}


