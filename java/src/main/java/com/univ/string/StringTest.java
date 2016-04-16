package com.univ.string;

import org.junit.Test;

public class StringTest {

	/*
	 * 请实现一个函数，把字符串中的每个空格替换成“%20”。
	 * 例如输入“We are happp.",则输出"We%20are%20happy."
	 */
	@Test
	public void replaceBlankChar(){
		String str = "We are hap pp.";
		int blankLen = 0;//str中空格的总数
		for(int i = 0; i < str.length(); i++){
			if (str.charAt(i) == ' ') {
				blankLen++;
			}
		}
		char[] chArr = new char[str.length() + 2*blankLen];//新的字符串长度
		int j = 0;//下一个chArr中需要赋值的位置
		for(int i = 0; i < str.length(); i++){
			if(str.charAt(i) != ' '){
				chArr[j] = str.charAt(i);
				j++;
			}else{
				chArr[j] = '%';
				chArr[j+1] = '2';
				chArr[j+2] = '0';
				j = j + 3;
			}
		}
		String strResult = new String(chArr);
		System.out.println(strResult);
	}
	
	
	
	
	
	
	
	
	
	
	
}
