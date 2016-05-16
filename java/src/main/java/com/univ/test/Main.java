package com.univ.test;

public class Main<T> {
	static int[] b;
	public static void main(String[] args) {

		int[] a = {208,254,473,153,389,579,398};

		System.out.println(findMaxMinus(a,a.length));
	}
	private static int findMaxMinus(int[] a, int len) {
		int maxElement = a[0];
		boolean hasZero = false;//判断数组a中是否包含零元素
		for(int i = 1; i < len; i++){//找出数组a的最大值
			if(a[i] > maxElement){
				maxElement  = a[i];
			}
			if(a[i] == 0){
				hasZero = true;
			}
		}

		int[] b = new int[maxElement+1];//利用此数组实现O(n)内使数组排序
		for(int i = 0; i < len; i++){
			b[a[i]] = a[i];//这句是核心
		}

		int[] c = new int[len];
		for(int i = 0, j = 0; i < maxElement+1; i++){
			if(b[i] != 0){
				c[j] = b[i];
				j++;
			}
		}
		show(c);
		int maxMinus;
		if (!hasZero) {
			maxMinus = c[1] - c[0];
		} else {
			maxMinus = c[0];
		}
		for(int i = 0; i < len-1; i++){
			if(c[i+1] - c[i] > maxMinus){
				maxMinus = c[i+1] - c[i];
			}
		}
		
		return maxMinus;
	}
	private static void show(int[] c) {
		for (int i = 0; i < c.length; i++) {
			System.out.print(c[i] + " ");
		}
		System.out.println();
		
	}






	

}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
