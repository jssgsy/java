package com.univ.algorithom.little;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author: liuml
 * @date: 2016年4月3日 下午6:46:45
 * @version: 1.0
 * @description: 演示最基本的算法
 */

public class AlgorithomDemo {

    /*
     * 两个循环输出九九乘法表,要求最终结果如下：
         1*1=1
        1*2=2 2*2=4
        1*3=3 2*3=6 3*3=9
        1*4=4 2*4=8 3*4=12 4*4=16
        1*5=5 2*5=10 3*5=15 4*5=20 5*5=25
        1*6=6 2*6=12 3*6=18 4*6=24 5*6=30 6*6=36
        1*7=7 2*7=14 3*7=21 4*7=28 5*7=35 6*7=42 7*7=49
        1*8=8 2*8=16 3*8=24 4*8=32 5*8=40 6*8=48 7*8=56 8*8=64
        1*9=9 2*9=18 3*9=27 4*9=36 5*9=45 6*9=54 7*9=63 8*9=72 9*9=81
     */
    /*
     * 核心：弄清楚i和j的职责
     * 	row:行，放在外循环（因为要一行一行输出）
     * 	col：列，col<=row;
     */
    @Test
    public void nineNineMulitTable1() {
        for (int row = 1; row <= 9; row++) {
            for (int col = 1; col <= row; col++) {//j<=i
                System.out.print(col + "*" + row + "=" + row * col + " ");
            }
            System.out.println();
        }
    }

    /*
     * 一个循环输出九九乘法表
     * col：表示列，当col==row时输出换行，col归零 （在for的自增标记中会变为1），每一行的输出列都需要从1开始！
     * row:表示行，row加1就表示乘法进行到了第row+1行
     * 其实这里还是有两个循环，nineNineMulitTable1是一个循环里定义一个变量，这里是一个循环里定义两个变量，
     * 重点：一个循环中定义两个变量,row作为for的判断变量，而col则作为for的自增变量，但同时row在if中自增。
     * 总结：其实这样的思路不好，因为并没有减少步骤，应该算是一种奇巧淫技
     */
    @Test
    public void nineNineMulitTable2() {
        for (int row = 1, col = 1; row <= 9; col++) {
            System.out.print(col + "*" + row + "=" + row * col + " ");
            if (col == row) {
                System.out.println();
                col = 0;
                row++;
            }
        }
    }


    @Test
    public void test() {
        String str = "pigs love llll";
        String[] split = str.split(" ");
        for (String string : split) {
            System.out.println(string);
        }

    }

    //-------------------------------斐波那契(fibonacci)-----------------------------
    // 1. 使用递归的方式
	/*
	a. f(0)与f(1)会重复多次计算，有些浪费
	 */
    @Test
    public void fib1() {
        System.out.println(fib(5));
    }

    private int fib(int n) {
        if (n < 0) {
            throw new UnsupportedOperationException("N必须大于等于2=0");
        }
        if (n == 1 || n == 0) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }

    // 2. 构建一个斐波那契数组
    @Test
    public void fib2() {
        int N = 10;
        int[] fibArr = new int[N];
        fibArr[0] = fibArr[1] = 1;
        for (int i = 2; i < 10; i++) {
            fibArr[i] = fibArr[i - 1] + fibArr[i - 2];
        }
        System.out.println(Arrays.toString(fibArr));
    }

    // 3. 使用for循环完成斐波那契
	/*
	1. 需要两个中间变量来保存上一次的计算结果;
	2. 时间复杂度最低;
	 */
    @Test
    public void fib3() {
        int N = 8;
        System.out.println(fib31(N));
    }

    private int fib31(int n) {
        if (n < 0) {
            throw new UnsupportedOperationException("n必须大于等于0");
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        int first = 1;
        int second = 1;
        int result = 0;
        for (int i = 2; i <= n; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }


}
