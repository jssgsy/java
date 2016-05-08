package com.univ.algorithom.merge;

import java.util.Arrays;

import org.junit.Test;

public class MergeSort {

	/*
	 * 归并排序
	 * 思想：将待排序数组分成两个等长小数组，为每一个小数组排序，然后再将他们合并成一个大的有序数组
	 */
	@Test
	public void test(){
		int[] a = {100,21,75,14,17,25,49,58,66,70};
		int[] b = new int[a.length];//重点1：b只是一个过渡数组，先存放merge有序的元素，然后将这些有序的复制到a中
		int low = 0;		
		int high = a.length-1;
		mergeSort(a,b,low,high);
		System.out.println(Arrays.toString(a));
	}

	/*
	 * 归并排序
	 */
	private void mergeSort(int[] a, int[] b, int left, int right) {
		if(left >= right){
			return ;
		}
		int mid = (left + right)/2;
		mergeSort(a,b,left, mid);
		mergeSort(a,b,mid+1,right);
		merge(a,b,left,mid,right);
		
	}

	private void merge(int[] a, int[] b, int left, int mid, int right) {
		int k = left; //细节1：b中下一个应该被赋值的位置,注意值是left
		int i = left;	//遍历左边小数组的下标
		int j = mid+1;	//遍历右边小数组的下标
		for(; i <= mid && j <= right;){
			if(a[i] <= a[j]){
				b[k] = a[i];
				i++;
			}else{
				b[k] = a[j];
				j++;
			}
			k++;
		}
		//这里需要熟悉一下jdk中关于数组赋值的函数
		if(i == mid + 1){	//说明左边小数组先被遍历完，需要将右边小数组仍未遍历的元素复制到数组b中
			for(int n = j; n <= right; n++){
				b[k] = a[n];
				k++;
			}
		}
		if(j == right + 1){	//说明右边小数组先被遍历完，需要将左边小数组仍未遍历的元素复制到数组b中
			for(int n = i; n <= mid; n++){
				b[k] = a[n];
				k++;
			}
		}
		
		//重点2： 一轮merge之后有序的元素还需要复制到a中，不然体现不出merge后已经有序，那上面的a也就时刻都没变，merge就是无用功
		for(int m = left; m <= right; m++){
			a[m] = b[m];
		}
		
		
		
		
		
	}
}
