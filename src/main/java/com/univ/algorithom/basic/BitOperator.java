package com.univ.algorithom.basic;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 位运算相关
 *  * 求整数n的二进制表示中1的个数；{@link BitOperator#numberOf1_v2(int)}
 *  * 判定整数n是奇数还是偶数；{@link OddEven#isEven_v2(int)}
 *
 * @author univ
 * 2022/4/28 11:27 上午
 */
public class BitOperator {

    /**
     * 数字二进制表示中1的个数
     * {@link #numberOf1_v2(int)} {@link #numberOf1_v1(int)}
     */
    @Test
    public void testNumberOf1() {
        List<Integer> list = Arrays.asList(Integer.MIN_VALUE , -1, 127, 9234, -18473, -127, Integer.MAX_VALUE);
        for (Integer n : list) {
            System.out.println(n + "的二进制表示为： " + Integer.toBinaryString(n));
            System.out.println(n + "的二进制表示中1的个数为： " + numberOf1_v2(n)); //numberOf1_v1(n)
        }
    }

    /**
     * 最直观的解法：n的二进制表示中，从右往左依次看所在位是否为1；
     *
     * 缺点：固定死了循环次数为32(如果是long则需要固定为64次)
     * @param n
     * @return
     */
    public int numberOf1_v1(int n) {
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

    /**
     * 最优解法，但最隐晦
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
    public int numberOf1_v2(int n) {
        int count = 0;
        while (n != 0) { // 不为0则其二进制表示中至少有一位是1
            count++;
            n = (n - 1) & n;
        }
        return count;
    }

    /**
     * {@link #isBit1(int, int)}
     */
    @Test
    public void testIsBit1() {
        int num = 2;
        for (int i = 0; i < Integer.SIZE; i++) {
            System.out.println("数字" + num + "的第" + i + "位是否为1：" + isBit1(num, i));
        }
    }


    /**
     * 判断某个整数的二进制表示中，某个索引处的比特是否为1
     *
     * 思路来源：
     *  1. 如果是判断最右边一位是否为1，则直接与1求与运算看是否不为0即可。
     *  2. 其次对于二进制中的1，通常从右往前找，不过为了统一，这里的index还是遵循从左到右，在代码中处理即可；
     *
     * @param num
     * @param index 从0开始
     * @return
     */
    public static boolean isBit1(int num, int index) {
        if (index >= Integer.SIZE) {
            return false;
        }
        // 1需要左移的个数
        int j = Integer.SIZE - index - 1;
        return (num & (1 << j)) != 0;
    }

    @Test
    public void testIndexOfFirst1() {
        System.out.println("-1的二进制表示为" + Integer.toBinaryString(-1));
        for (Integer num : Arrays.asList(-1, 1, 2, 3, 4, 5, 6, 7)) {
            System.out.println(num + "从左到右第一个为1的索引为：" + indexOfFirst1FromLeft(num));
            // System.out.println(num + "从右到左第一个为1的索引为：" + indexOfFirst1FromRight(num));
        }
    }

    /**
     * 找到给定整数的二进制中从左往右的第一个为1(包含符号位)的下标；
     * @param num
     * @return 索引从0开始
     */
    public static int indexOfFirst1FromLeft(int num) {
        for (int i = 0; i < Integer.SIZE; i++) {
            int i1 = Integer.SIZE - i - 1;
            if ((num & (1 << i1)) != 0) {
                return i;
            }
        }
        throw new RuntimeException("数字" + num + "中没有为1的位");
    }

    /**
     * 找到给定整数的二进制中从右往左的第一个为1(包含符号位)的下标；
     * @param num
     * @return 索引从0开始
     */
    public static int indexOfFirst1FromRight(int num) {
        for (int i = 0; i < Integer.SIZE; i++) {
            if ((num & (1 << i)) != 0) {
                return i;
            }
        }
        throw new RuntimeException("数字" + num + "中没有为1的位");
    }

    /**
     * {@link #put1(int, int)}
     */
    @Test
    public void testPut1() {
        int index = 30;
        for (Integer num : Arrays.asList(1, 4, 256, 109)) {
            System.out.println(Integer.toBinaryString(num) + ": " + Integer.toBinaryString(put1(num, index)));
        }
    }

    /**
     * 将数字num的二进制表示中的第index位处的值置为1
     * 如0000 0100（4）的第2位置为1变成0000 0110(5)
     * @param num
     * @param index 从0开始，
     * @return
     */
    public static int put1(int num, int index) {
        int indexFromToLeft = Integer.SIZE - index - 1;
        // (1 << indexFromToLeft)则第indexFromToLeft位上必然为1，再与num求或运算，则num的第indexFromToLeft必然为1
        return num | (1 << indexFromToLeft);
    }

    /**
     * {@link #put0(int, int)} (int, int)}
     */
    @Test
    public void testPut0() {
        int index = 30;
        for (Integer num : Arrays.asList(1, 6, 255, 110)) {
            System.out.println(Integer.toBinaryString(num) + ": " + Integer.toBinaryString(put0(num, index)));
        }
    }

    /**
     * 将数字num的二进制表示中的第index位处的值置为0
     * 如0000 0110（6）的第2位置为0变成0000 0100(4    )
     * @param num
     * @param index 从0开始，
     * @return
     */
    public static int put0(int num, int index) {
        int indexFromToLeft = Integer.SIZE - index - 1;
        // (1 << indexFromToLeft)则第indexFromToLeft位上必然为1，后取反(位上必然为0，其它位为1)，再与num求与运算，则num的第indexFromToLeft必然为0
        return num & ~(1 << indexFromToLeft);
    }

}
