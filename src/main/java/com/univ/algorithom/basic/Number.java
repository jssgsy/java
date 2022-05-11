package com.univ.algorithom.basic;

import java.util.Arrays;

import org.junit.Test;

/**
 * 数字相关
 * @author univ
 * 2022/5/11 9:31 上午
 */
public class Number {

    /**
     * {@link #firstNum(int)}
     */
    @Test
    public void testFirstNum() {
        for (Integer num : Arrays.asList(12345, 2345, 345, 45, 5)) {
            System.out.println("数字" + num + "的最高位为：" + firstNum(num));
        }
    }

    /**
     * 求整数数字的第一位(最高位),
     * 如输入 1234，则输出1；
     * 如输入 234，则输出2；
     * 如输入 34，则输出3；
     * 如输入 4，则输出4；
     *
     * 思路：显然，1234的第一位就是1234/1000，而234的第一位就是234/100，所以问题转化成了如何确实除数是1000还是100还是10
     *
     * @param num
     * @return
     */
    int firstNum(int num) {
        int div = 1;    // 从个位开始
        while (num / div > 9) {
            div = div * 10;
        }
        return num / div;
    }

    /**
     * {@link #lastNum(int)}
     */
    @Test
    public void testLastNum() {
        for (Integer num : Arrays.asList(12345, 1234, 123, 12, 1)) {
            System.out.println("数字" + num + "的最低位为：" + lastNum(num));
        }
    }

    /**
     * 求整数数字的最后一位(最低位),
     * 如输入 1234，则输出4；
     * 如输入 234，则输出4；
     * 如输入 34，则输出4；
     * 如输入 4，则输出4；
     *
     * 思路：
     *  1234除以10=(商123, 余4)；
     *  234除以10=(商23, 余4)；
     *  34除以10=(商3, 余4)；
     *  4除以10=(商0, 余4)；
     * 由此可知，取最后一位直接除10取余即可
     *
     * @param num
     * @return
     */
    int lastNum(int num) {
        return num % 10;
    }

    /**
     * {@link #lengthOfNum(int)}
     */
    @Test
    public void testLengthOfNum() {
        for (Integer num : Arrays.asList(12345, 2345, 345, 45, 5)) {
            System.out.println("数字" + num + "的长度为：" + lengthOfNum(num));
        }
    }

    /**
     * 数字(正数)的长度，如1234的长度为4，234的长度为3
     * 准确的说，是想表达数字num对应的10的最高次幂，如1234等于的就是1000
     * 思路来源：{@link #firstNum(int)}
     * @param num
     *
     * @return
     */
    int lengthOfNum(int num) {
        int div = 1;
        int len = 1;
        while (num / div > 9) {
            len++;
            div = div * 10;
        }
        return len;
    }
}
