package com.univ.algorithom.string;

import org.junit.Test;

public class StringTest {

	/* 返回字符串中第一个只出现一次的字符
	 * 1.用自定义的数组模拟散列表；
	 * 2.借助HashMap更简单；
	 */
	@Test
	public void bucket(){
		String str = "aabbcdd";
		Character f = getFirstNoDuplicate2(str, str.length());//注意这里不要使用char，万一方法返回为null,则转换出错
		System.out.println(f);
	}

	private Character getFirstNoDuplicate2(String str, int len) {
		int[] bucket = new int[256];//一个字符一个字节8位，总共需要256个元素的大小
		for(int i = 0; i < len; i++){
			Character ch = str.charAt(i);
			bucket[ch]++;//char类型可以直接当数组下标使用，此时自动装箱
		}
		for(int i = 0; i < 256; i++){//注意，这里是256，不是len
			if (bucket[i] == 1) {
				Character s = Character.valueOf((char)i);
				return s;
			}
		}
		return null;
	}

}
