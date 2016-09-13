package com.univ;

/**
 * Univ
 * 16/9/13 11:16
 */

import org.junit.Test;

import java.util.Arrays;

/**
 * 用以存放jdk中常用的工具类方法
 */
public class systemUtil {

    /**
     * System.arraycopy()的参数:
     *      src - the source array.
     *      srcPos - starting position in the source array.
     *      dest - the destination array.
     *      destPos - starting position in the destination data.
     *      length - 想从原数组中复制元素的个数
     */
    @Test
    public void test1(){
        int[] a = {1, 2, 3, 4};
        int[] b = {6, 7, 8};
        /**
         * 将b数组中的[0,0+2)区间的元素用a数组中的[1,1+2)元素替换(都是左开右闭区间);
         */
        System.arraycopy(a,1,b,0,2);
        System.out.println(Arrays.toString(b));//2, 3, 8
    }


}
