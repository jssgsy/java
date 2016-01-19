package com.univ.algorithom;
/** 
 * @author univ 
 * @date 2016年1月5日 下午4:06:59 
 * @version v1.0
 * @Description: 回文算法
 */
public class Palindrom {

	public static void main(String[] args) {
		String str = "ab";
		System.out.println(isPalindrom1(str));
		
	}
	
	/**
	 * 判断str是否是一个回文字符串
	 * @param str
	 * @return	true if str is a palindrom,else false
	 */
	public static boolean isPalindrom(String str){//abccba
		int mid = str.length()/2-1;
		if (str.length()%2 == 0) {//str中的字符个数为偶数
			for (int i = 0; i <= mid; i++) {
				if (str.charAt(mid-i) != str.charAt(mid+i+1)) {
					return false;
				} else {
					continue;
				}
			}			
		} else {//str中的字符个数为奇数
			for (int i = 0; i <= mid; i++) {
				if (str.charAt(mid-i) != str.charAt(mid+i+2)) {
					return false;
				} else {
					continue;
				}
			}		
		}
		return true;
	}
	
	/**
	 * 下面是更好的思路，不需要区分奇偶的情况,直接从两头往中间
	 */
	public static boolean isPalindrom1(String str){//abccba
		int len = str.length();
		for(int i = 0;i<=len/2-1;i++){
			if (str.charAt(i) != str.charAt(len-i-1)) {
				return false;
			} else {
				continue;
			}
		}
		return true;
	}
}

