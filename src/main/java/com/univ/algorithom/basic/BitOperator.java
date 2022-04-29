package com.univ.algorithom.basic;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 位运算相关
 *  * 求整数n的二进制表示中1的个数；{@link BitOperator#numberOf_v1(int)}
 *  * 判定整数n是奇数还是偶数；{@link OddEven#isEven_v2(int)}
 *
 * @author univ
 * 2022/4/28 11:27 上午
 */
public class BitOperator {

    @Test
    public void test() {
        List<Integer> list = Arrays.asList(Integer.MIN_VALUE , -1, 127, 9234, -18473, -127, Integer.MAX_VALUE);
        for (Integer n : list) {
            System.out.println(n + "的二进制表示为： " + Integer.toBinaryString(n));
            System.out.println(n + "的二进制表示中1的个数为： " + numberOf_v1(n)); //numberOf1_v2(n)
        }
    }
    /**
     * 输入：一个整数
     * 输出：此整数的二进制表示中1的个数(包含符号位)
     * 此方法最简便解法，但最隐晦
     *
     * 核心：
     * 1. 如果n不为0，则其二进制表示中至少有一位是1；
     * 2. X & X = X；
     * 3. (n - 1) & n 的结果相当于把n的二进制表示中最右边的一位1给抹除了；
     *      这条需要实际操作体会下；当作一条定律记住；
     *      假设n的二进制表示中最后一个1位于第k个位置处，则n - 1如下
     *      **** 1 000000   (n的二进制表示)
     *      0000 0 000001   (减1)
     *      **** 0 111111   (n - 1的结果)
     *
     *      **** 1 000000   (n的二进制表示) 注意和下面一行对比
     *      **** 0 111111   (n - 1的结果) 注意和上面一行对比
     *      重点：从左往右看，(n - 1)相当于
     *          1. 把n从第1位、第2位、一直到第(k-1)处的值不变，即示例中的四个*；
     *          2. 把从第k位一直到最右边的值取反(即都置为0了)；
     *      所以(n - 1) & n 相当于把n的二进制表示中最右边的一位1给抹除了；
     *
     * @param n
     * @return
     */
    public int numberOf_v1(int n) {
        int count = 0;
        while (n != 0) { // 不为0则其二进制表示中至少有一位是1
            count++;
            n = (n - 1) & n;
        }
        return count;
    }

    /**
     * 最直观的解法：n的二进制表示中，从右往左依次看所在位是否为1；
     *
     * 缺点：固定死了循环次数为32(如果是long则需要固定为64次)
     * @param n
     * @return
     */
    public int numberOf1_v2(int n) {
        int it = 1;
        int count = 0;
        int i = 1;
        while (i <= 32) {
            if ((it & n) != 0) {
                count++;
            }
            it = it << 1;
            i++;
        }
        return count;
    }

}