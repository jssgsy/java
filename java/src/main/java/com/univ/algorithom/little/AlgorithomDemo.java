package com.univ.algorithom.little;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

/**
 * @author: liuml
 * @date: 2016年4月3日 下午6:46:45
 * @version: 1.0
 * @description:
 */

public class AlgorithomDemo {

	/*
	 * 两个循环输出九九乘法表,要求最终结果如下：
	 	1*1=1 
		1*2=2 2*2=4 
		1*3=3 2*3=6 3*3=9 
		1*4=4 2*4=8 3*4=12 4*4=16 
		1*5=5 2*5=10 3*5=15 4*5=20 5*5=25 
		1*6=6 2*6=12 3*6=18 4*6=24 5*6=30 6*6=36 
		1*7=7 2*7=14 3*7=21 4*7=28 5*7=35 6*7=42 7*7=49 
		1*8=8 2*8=16 3*8=24 4*8=32 5*8=40 6*8=48 7*8=56 8*8=64 
		1*9=9 2*9=18 3*9=27 4*9=36 5*9=45 6*9=54 7*9=63 8*9=72 9*9=81 
	 */
	/*
	 * 核心：弄清楚i和j的职责
	 * 	row:行，放在外循环（因为要一行一行输出）
	 * 	col：列，col<=row;
	 */
	@Test
	public void nineNineMulitTable1() {
		for (int row = 1; row <=9; row++) {
			for(int col = 1; col <= row; col++){//j<=i
				System.out.print(col + "*" + row + "=" + row * col + " ");
			}
			System.out.println();
		}
	}
	
	/*
	 * 一个循环输出九九乘法表 
	 * col：表示列，当col==row时输出换行，col归零 （在for的自增标记中会变为1），每一行的输出列都需要从1开始！
	 * row:表示行，row加1就表示乘法进行到了第row+1行
	 * 其实这里还是有两个循环，nineNineMulitTable1是一个循环里定义一个变量，这里是一个循环里定义两个变量，
	 * 重点：一个循环中定义两个变量,row作为for的判断变量，而col则作为for的自增变量，但同时row在if中自增。
	 * 总结：其实这样的思路不好，因为并没有减少步骤，应该算是一种奇巧淫技
	 */
	@Test
	public void nineNineMulitTable2() {
		for(int row = 1,col = 1; row <=9; col++){
			System.out.print(col + "*" + row + "=" + row * col + " ");
			if(col == row){
				System.out.println();
				col = 0;
				row++;
			}
		}
	}
	
	
	@Test
	public void test() {
		String str = "pigs love llll";
		String[] split = str.split(" ");
		for (String string : split) {
			System.out.println(string);
		}
		
	}
	

}
