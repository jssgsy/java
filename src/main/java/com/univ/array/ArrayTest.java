package com.univ.array;

import java.util.Arrays;

import org.junit.Test;

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

	/**
	 * 数组定义需要注意的地方
	 * 	1.
	 */
	@Test
	public void test1(){
		/**
		 * 数组的大小是固定的,所以其定义时必须指定大小。而这有两种方式:
		 * 	1。定义时显示在[]中指定大小;
		 * 	2。定义时赋予初始化表达式,可以自动计算出数组大小;
		 * 补充:
		 * 		不能同时利用上面两种方式指定数组大小
		 */
		int[] a1 = new int[3];//1.可以在[]中指定数组大小
		int[] a2 = new int[]{3, 5};//2.利用初始化表达式
		//int[] a3 = new int[2]{3, 5};//不能同时利用上面两种方式指定数组大小

		/**
		 * 二维数组是数组的数组,所以其定义也是类似.
		 * 补充:必须指定高维数组的大小后才能指定低维数组的大小;
		 *
		 */
		int[][] b1 = new int[3][];//1.可以在[]中指定数组大小
		int[][] b2 = new int[3][4];//1.可以在[]中指定数组大小
		//int[][] b2 = new int[][3];//必须指定高维数组的大小后才能指定低维数组的大小;

		int[][] b3 = new int[][]{//2.利用初始化表达式
				{3,5},
				{1,3}
		};
		/*int[][] b4 = new int[2][]{//不能同时利用上面两种方式指定数组大小
				{3,5},
				{1,3}
		};*/

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

    /**
     * 拷贝数组的一部分[from,to),是左闭右开
     *  copyOfRange(int[] original,int from,int to)
     */
	@Test
    public void copyArrayRange(){
        int[] oldArr = new int[]{3,6,5,2};
        int[] newArr = Arrays.copyOfRange(oldArr, 1, 3);
        System.out.println(Arrays.toString(newArr));
    }
	
	//将二维数组当做一位数组对待会好理解一些，退化后的一位数组中的每个元素都是一个数组（类似于广义表）
	@Test
	public void multiArray(){
		int[] arr = new int[]{1,2,3};
		int[][] arrs = new int[][]{
				{1,2,3},
				{4,5}
				};//当做一位数组，每个元素又都是一个数组
		//遍历二维数组：仍然当做一维数组
		for (int[] is : arrs) {
			for (int i : is) {
				System.out.println(i);
			}
		}
	}
	
	/*
	 * 多维数组遍历
	 * 对于三维数组a[i][j][k],i:页,j:行,k:列
	 */
	@Test
	public void test(){
		int[][][] arrs = new int[][][]{
				{
						{1, 2, 3},
						{4, 5},
						{2, 3, 5, 6, 7},
						{1}
				}
		};
		System.out.println(arrs.length);
		for(int i = 0; i < arrs.length; i++){
			for(int j = 0; j < arrs[i].length; j++){
				for(int k = 0; k < arrs[i][j].length; k++){
					System.out.print(arrs[i][j][k] + " ");
				}
			}
			System.out.println();
		}
	}
	
	/*
	 * 二维数组中，每一行按照从左到右递增，每一列从上到下递增。在其中找出给定的数值。
	 * 思路：从右上角开始找。
	 * 每当这个元素比目标数字大，则去掉该列，每当这个元素比目标数字小，则去掉该行，重复这个过程，
	 * 思维重点：
	 * 1. 虽然这里是二维数组，但并不需要嵌套循环；
	 * 2. 加深对for循环三个子句的理解，比如这里第三个子句就是在循环体中赋值。
	 */
	@Test
	public void findTarget(){
		int target = 10;
		int[][] a = {//假设这里的二维数组是矩形的
				{1,2,8,9},
				{2,4,9,12},
				{4,7,10,13},
				{6,8,11,15}
		};
		int result = findTarget(a, a.length, a[0].length, target);//因为这里假设二维数组是矩形的，所以每列的个数都是a[0].length
		System.out.println(result);
	}
	
	private int findTarget(int[][] a, int rowLen, int colLen, int target) {
		for(int row = 0, col = colLen - 1; row < rowLen && col >= 0;){
			System.out.println("正在和target比较的是: " + a[row][col]);
			if (a[row][col] > target) {
				col--;
			} else if(a[row][col] < target){
				row++;
			}else{
				System.out.println("target在第" + (row+1) + "行" + "第" + (col+1) + "列处找到。");
				return a[row][col];
			}
		}
		return -1;
	}

	//输出数组，供其他函数调用
	public void showArray(int[] arr){
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
}


