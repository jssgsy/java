package com.univ.algorithom.sort;

/**
 * @author: liuml
 * @date: 2016年4月8日 上午10:33:20
 * @version: 1.0
 * @description: 基数排序 
 * 	错误的思路：先按百位排，然后传入原数组（修改后）按十位排，最后传入原数组按个位排，因为这相当于只是按个位排。
 *  正确的思路     ：要保持百位数字相同的情况下再按十位数字排才有意义，所以按百位排后传入的不能是原数组而应该是桶（桶内元素的百位已经相同）；
 *  需要用到递归算法
 */

public class RadixSortTest {
	public static final int RADIX = 10;//基数

	public static void main(String[] args) {
		int[] a = { 1, 633, 10,35,59, 0, 32, 664, 179, 457, 825, 714 };
		showArray(a);
		a = RadixSort(a, 0, a.length - 1, 3);
	}	

	public static int[] RadixSort(int[] a, int left, int right, int d) {
		int rangeToSort = right - left + 1;
		if (rangeToSort <= 1 || d == 0) {//待排序数组中元素小于1个，直接返回
			return a;
		}
		int[] bucketCapacity = new int[RADIX]; // bucketCapacity[i]:i出现的次数，即第i个桶的容量（应该存放的元素个数）
		for (int i = left; i <= right; i++) { // 计算每个桶的容量
			bucketCapacity[getDigit(a[i], d)]++;
		}
		
		int[] tempBucketCapacity = new int[RADIX]; //每个桶的容量,供后面查找l,r使用
		for (int i = 0; i < RADIX; i++) {
			tempBucketCapacity[i] = bucketCapacity[i];
		}

		int[] bucketPos = new int[RADIX];

		for (int i = 1; i < RADIX; i++) {//求出每个桶（编号从0开始）的位置，很重要！
			bucketPos[i] = bucketPos[i - 1] + bucketCapacity[i - 1];
		}

		int[] temp = new int[rangeToSort];
		for (int i = left; i <= right; i++) {
			int digit = getDigit(a[i], d);
			int posToPut = bucketPos[digit];// 元素应该存放的真正的位置
			temp[posToPut] = a[i];
			if (bucketCapacity[digit] > 1) {// 桶中的容量大于1
				bucketPos[digit]++;// 此桶中下一个元素应该存放的位置
				bucketCapacity[digit]--;// 桶的容量减一（已经被占用一个），所以才需要引入tempBucketCapacity保存桶的原始大小
			}
		}

		for (int i = left; i <= right; i++) {//将排好序的temp中的元素归位到原数组中
			a[i] = temp[i - left];
		}
		showArray(a);

		for (int i = 0; i < RADIX; i++) {//递归调用每一个桶
			int r = bucketPos[i];// 第i个桶的结束位置
			int l = r - tempBucketCapacity[i] + 1;// 第i个桶的起始位置
			RadixSort(a, l, r, d - 1);
		}
		return a;
	}

	/*
	 * 获取数字n指定位(d)上的数字,如d=3,则表示获取10的（3-1）次幂上的数字，即百位上的数字;
	 * 注意char转int需要先将char转String然后再转成int
	 */
	private static int getDigit(int n, int d) {
		String strNum = Integer.toString(n);
		if (d > strNum.length()) {
			return 0;
		}
		return Integer.valueOf(String.valueOf(strNum.charAt(strNum.length() - d)));
	}
	
	private static void showArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
}
