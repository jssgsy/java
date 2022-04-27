package com.univ.algorithom.basic;

import org.junit.Test;

/**
 * 斐波那契 f(n) = f(n - 1) + f(n - 2)
 * @author univ
 * 2022/4/27 10:23 上午
 */
public class Fibonacci {

    @Test
    public void test() {
        // 递归形式很慢
        // System.out.println(recursive(100));

        // 由结果可知，当n=47时就溢出了
        for (int i = 1; i < 100; i++) {
            System.out.println("fib(" + i + "): " + traverse(i));
        }
    }


    /**
     * 递归形式，性能较差，没法处理大n。一是递归栈深度，一个是会涉及大量重复计算(尝试用树形加以理解)
     * @param n
     * @return
     */
    public int recursive(int n) {
        if (n <= 0) {
            return 0;
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        return recursive(n - 1) + recursive(n - 2);
    }

    /**
     * 遍历形式，性能较之递归形式好很多
     * @param n
     * @return
     */
    public int traverse(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int first = 1;
        int second = 1;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = second + first;
            first = second;
            second = result;
        }
        return result;
    }
}
