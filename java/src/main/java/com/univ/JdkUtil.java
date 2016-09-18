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
public class JdkUtil {

    /**
     * System.arraycopy()
     * 参数:
     *      src - the source array.
     *      srcPos - starting position in the source array.
     *      dest - the destination array.
     *      destPos - starting position in the destination data.
     *      length - 想从原数组中复制元素的个数
     *
     * 注意和Arrays.copyOf()方法的区别(见ArrayTest.java):
     *  1. System.arraycopy():新旧数组都是方法的参数;
     *  2. Arrays.copy():新数组是方法的返回值;
     *  3. Arrays.copy()底层调用的是System.arraycopy()方法;
     *
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

    /**
     * 基本数据类型数组的排序。
     *
     * Arrays中提供了基本数据类型数组排序:Arrays.sort(arr1,arr2)
     */
    @Test
    public void test2(){
        //int 类型数组排序
        int[] arrInt = {3, 5, 1, 39, 34, -3};
        Arrays.sort(arrInt);
        System.out.println(Arrays.toString(arrInt));

        //double类型数组排序
        double[] arrDouble = {2.4, 1.2, 5, 36.2, 34.24, -3.5};
        Arrays.sort(arrDouble);
        System.out.println(Arrays.toString(arrDouble));

        //String类型数组排序
        String[] arrStr = {"abd", "aefa", "feaf", "fjeifa", "iu", "aa", "iy", "etf"};
        Arrays.sort(arrStr);
        System.out.println(Arrays.toString(arrStr));
    }

    /**
     * 基本类型数组的比较判断。
     * Arrays中提供了基本数据类型数组排序:Arrays.equals(arr1,arr2)
     */
    @Test
    public void test3(){
        //int 类型数组相等比较
        int[] arrInt1 = {3, 5, 1, 39, 34, -3};
        int[] arrInt2 = {3, 39, 34, -3};
        System.out.println(Arrays.equals(arrInt1,arrInt2));


        //String类型数组排序
        String[] arrStr1 = {"abd", "fjeifa", "iu", "aa", "iy", "etf"};
        String[] arrSt2 = {"abd", "aefa", "feaf", "fjeifa", "iu", "aa", "iy", "etf"};
        System.out.println(Arrays.equals(arrStr1,arrSt2));
    }


}
