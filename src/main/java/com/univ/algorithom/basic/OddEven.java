package com.univ.algorithom.basic;

import org.junit.Test;

/**
 * 奇偶
 * 偶数：能被2整除的数(包含0)，如2， 4， 6， 8
 * 奇数：不能被2整除的数，如1， 3， 5， 7
 * @author univ
 * 2022/4/28 9:54 下午
 */
public class OddEven {

    @Test
    public void test() {
        for (int i = -5; i <= 5; i++) {
            if (isEven_v1(i)) {
                System.out.println(i + "是偶数");
            } else {
                System.out.println(i + "是奇数");
            }
        }
        System.out.println("------");
        for (int i = -5; i <= 5; i++) {
            if (isEven_v2(i)) {
                System.out.println(i + "是偶数");
            } else {
                System.out.println(i + "是奇数");
            }
        }
    }
    /**
     * 给定整数n，判定是否为偶数
     * 常规解法
     * @param n
     * @return
     */
    public boolean isEven_v1(int n) {
        // 能被2整除即为偶数
        return n % 2 == 0;
    }

    /**
     * 给定整数n，判定是否为偶数
     * 这是更高效的方法，也是位运算的一个应用.
     * 核心：{@link OddEven#printBinary()}
     *  任意奇数的二进制表示中最后一位是1，而任意偶数的二进制表示中最后一位是0
     * @param n
     * @return
     */
    public boolean isEven_v2(int n) {
        // 能被2整除即为偶数
        return (n & 1) == 0;
    }

    /**
     * 验证奇数的二进制表示中最后一位是1，而任意偶数的二进制表示中最后一位是0
     */
    @Test
    public void printBinary() {
        for (int i = -5; i <= 5; i++) {
            System.out.println(Integer.toBinaryString(i));
        }
    }

}
