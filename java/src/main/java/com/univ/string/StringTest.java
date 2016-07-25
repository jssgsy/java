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


	/**
	 * 实现一个算法来判断一个字符串中的字符是否唯一。
     * 来源:http://www.hawstein.com/posts/1.1.html。
     *
     * 假定组成字符串的字符都是ascii字符。
     */
    @Test
	public void isCharInStringUnique(){

        String str = " dqwic";
        if (isCharUnique(str)) {
            System.out.println("字符串: " + str + " 中的字符都是唯一的。");
        }else {
            System.out.println("o o");
        }
    }

    private boolean isCharUnique(String str) {

        boolean unique[] = new boolean[128];    //128
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (!unique[str.charAt(i)]) {//说明是第一次遍历到此字符,并设置为已经被遍历过
                unique[str.charAt(i)] = true;
            } else {
                return false;
            }
        }
        return true;
    }


}
