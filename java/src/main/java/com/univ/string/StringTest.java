package com.univ.string;

import org.junit.Test;

public class StringTest {

    /*
     * 请实现一个函数，把字符串中的每个空格替换成“%20”。
     * 例如输入“We are happp.",则输出"We%20are%20happy."
     */
    @Test
    public void replaceBlankChar() {
        String str = "We are hap pp.";
        int blankLen = 0;//str中空格的总数
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                blankLen++;
            }
        }
        char[] chArr = new char[str.length() + 2 * blankLen];//新的字符串长度
        int j = 0;//下一个chArr中需要赋值的位置
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                chArr[j] = str.charAt(i);
                j++;
            } else {
                chArr[j] = '%';
                chArr[j + 1] = '2';
                chArr[j + 2] = '0';
                j = j + 3;
            }
        }
        String strResult = new String(chArr);
        System.out.println(strResult);
    }


    /**
     * 实现一个算法来判断一个字符串中的字符是否唯一。
     * 来源:http://www.hawstein.com/posts/1.1.html。
     * <p>
     * 假定组成字符串的字符都是ascii字符。
     */
    @Test
    public void isCharInStringUnique() {

        String str = " dqwic";
        if (isCharUnique(str)) {
            System.out.println("字符串: " + str + " 中的字符都是唯一的。");
        } else {
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


    /**
     * 找出字符串中第一个只出现一次的字符。
     * <p>
     * 假定组成字符串的字符都是ascii字符.如果包含非ascii字符,可以将count[]替换成hashmap
     */
    @Test
    public void firstCharInString() {

        String str = "jjbiealdkfafweoefbi";
        int len = str.length();
        int count[] = new int[128];
        for (int i = 0; i < len; i++) {
            count[str.charAt(i)]++;
        }
        for (int i = 0; i < len; i++) {
            if (count[str.charAt(i)] == 1) {
                System.out.println("字符串" + str + "第一个只出现一次的字符为: " + str.charAt(i));
                break;
            }
        }

    }


    /**
     * 字符全排列方法1.这里只针对字符串不包含重复字符的情况。

     * 补充:
     * 1. substring(int beginIndex,int endIndex)是左开右闭[beginIndex,endIndex)
     */
    @Test
    public void testPrintAllArrange1() {
        String str = "abc";
        permu1(str);
    }

    private void permu1(String str) {
        int len = str.length();
        if (len > 1) {
            for (int i = 0; i < len; i++) {
                StringBuilder sb = new StringBuilder();//用来存放新的短的字符串,不要放到循环体外
                sb.append(str.substring(0, i));
                sb.append(str.substring(i + 1, len));
                System.out.print(str.charAt(i));
                permu1(sb.toString());
            }


        } else {
            System.out.println(str);
        }
    }

    /**
     * 字符全排列方法2.从permu1演变而来。
     *
     * 核心思路一样,基于"字符串的全排列和组成字符串的字符顺序是没有关系的"。
     *
     */
    @Test
    public void testPrintAllArrange2() {
        char[] buf = {'a', 'b', 'c','d'};
        permu2(buf,0,buf.length-1);

    }

    private void permu2(char[] buf, int from, int to) {
        int len = to - from + 1;
        if (len > 1) {
            for (int i = from; i <= to; i++) {
                char temp = buf[i];
                buf[i] = buf[from];
                buf[from]=temp;

                permu2(buf,from+1,to);

                buf[from] = buf[i];
                buf[i] = temp;
            }


        } else {
            for (int i = 0; i <= to; i++) {
                System.out.print(buf[i]);
            }
            System.out.println();
        }
    }

}
