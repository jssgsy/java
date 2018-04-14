package com.univ.algorithom.sort;

import java.util.Arrays;

import org.junit.Test;

public class BucketSort {

	/*
	 * 桶排序
	 * 需求：排序的数字是年龄，也就是说待排序的数字的值都在[1,100]之间。
	 */
	@Test
	public void bucketSort(){
		final int bucketNum = 100;//需要桶的数量：100
		int[] ages = {2,5,6,20,34,89,42,21,36,2,5,6,20,34,89,42,21,36,35,23,99,1,14,35};//待排序数组,确保值在[1,100]
		int[] bucket = new int[bucketNum];
		
		//统计每个数字出现的次数
		//注意bucket[i]的含义：值为i+1的数字出现的次数。
		for(int i = 0; i < ages.length; i++){
			int age = ages[i];
			bucket[age-1]++;
		}
		
		//最后排序
		//思路：依次遍历100个桶，并将捅中元素按其出现的次数给ages数组赋值
		int k = 0;//ages数组中下一个需要被赋值的位置
		for(int i = 0; i < bucketNum; i++){
			int bucketCapacity = bucket[i];//值为i+1的元素出现的次数
			for(int j = 0; j < bucketCapacity; j++){
				ages[k] = i+1;
				k++;
			}
		}
		System.out.println(Arrays.toString(ages));
	}
	
	
	
	
}
